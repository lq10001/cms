package com.ly.cms.action;

import com.ly.comm.Bjui;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.CacheManager;


import com.ly.cms.vo.Newstype;
import com.ly.cms.service.NewstypeService;


@IocBean
@At("/newstype")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class NewstypeAction {

	private static final Log log = Logs.getLog(NewstypeAction.class);
	
	@Inject
	private NewstypeService newstypeService;

    @At("/")
    @Ok("beetl:/WEB-INF/cms/newstype_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Newstype newstype,
                      HttpServletRequest request){

        Cnd c = new ParseObj(newstype).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(newstypeService.listCount(c));
            request.setAttribute("list_obj", newstypeService.queryCache(c,p));
        }else{
            p.setRecordCount(newstypeService.count(c));
            request.setAttribute("list_obj", newstypeService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("newstype", newstype);
    }

    @At
    @Ok("beetl:/WEB-INF/cms/newstype.html")
    public void edit(@Param("action")int action,
                     @Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("newstype", null);
        }else{

            Newstype newstype = newstypeService.fetch(id);
            if (action == 3)
            {
                //newstype.setName(null);
            }
            request.setAttribute("newstype", newstype);
        }
        request.setAttribute("action", action);
    }

    @At
    @Ok("json")
    public Map<String,String> save(@Param("action")int action,
                                @Param("..")Newstype newstype){
        Object rtnObject;
        if (newstype.getId() == null || newstype.getId() == 0) {
            rtnObject = newstypeService.dao().insert(newstype);
        }else{
            if (action == 3) {
                newstype.setId(null);
                rtnObject = newstypeService.dao().insert(newstype);
            }else{
                rtnObject = newstypeService.dao().updateIgnoreNull(newstype);
            }
        }
        CacheManager.getInstance().getCache(NewstypeService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_newstype", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  newstypeService.delete(id);
        CacheManager.getInstance().getCache(NewstypeService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_newstype",false);
    }

}
