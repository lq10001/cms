package com.ly.cms.action;

import com.ly.comm.Dwz;
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
        List<Newstype> list_m = newstypeService.query(c, p);
        p.setRecordCount(newstypeService.count(c));

        request.setAttribute("list_obj", list_m);
        request.setAttribute("page", p);
        request.setAttribute("newstype", newstype);
    }

    @At
    @Ok("beetl:/WEB-INF/cms/newstype.html")
    public void edit(@Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("newstype", null);
        }else{
            request.setAttribute("newstype", newstypeService.fetch(id));
        }
    }

    @At
    @Ok("json")
    public Map<String,String> save( @Param("..")Newstype newstype){
        Object rtnObject;
        if (newstype.getId() == null || newstype.getId() == 0) {
            rtnObject = newstypeService.dao().insert(newstype);
        }else{
            rtnObject = newstypeService.dao().updateIgnoreNull(newstype);
        }
        return Dwz.rtnMap((rtnObject == null) ? false : true, "newstype", "closeCurrent");
    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  newstypeService.delete(id);
        return Dwz.rtnMap((num > 0) ? true : false , "newstype", "");
    }

}
