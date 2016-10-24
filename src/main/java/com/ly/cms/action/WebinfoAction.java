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


import com.ly.cms.vo.Webinfo;
import com.ly.cms.service.WebinfoService;


@IocBean
@At("/webinfo")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class WebinfoAction {

	private static final Log log = Logs.getLog(WebinfoAction.class);
	
	@Inject
	private WebinfoService webinfoService;

    @At("/")
    @Ok("beetl:/WEB-INF/cms/webinfo_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Webinfo webinfo,
                      HttpServletRequest request){

        Cnd c = new ParseObj(webinfo).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(webinfoService.listCount(c));
            request.setAttribute("list_obj", webinfoService.queryCache(c,p));
        }else{
            p.setRecordCount(webinfoService.count(c));
            request.setAttribute("list_obj", webinfoService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("webinfo", webinfo);
    }

    @At
    @Ok("beetl:/WEB-INF/cms/webinfo.html")
    public void edit(@Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("webinfo", null);
        }else{
            request.setAttribute("webinfo", webinfoService.fetch(id));
        }
    }

    @At
    @Ok("json")
    public Map<String,String> save( @Param("..")Webinfo webinfo){
        Object rtnObject;
        if (webinfo.getId() == null || webinfo.getId() == 0) {
            rtnObject = webinfoService.dao().insert(webinfo);
        }else{
            rtnObject = webinfoService.dao().updateIgnoreNull(webinfo);
        }
        CacheManager.getInstance().getCache(WebinfoService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_webinfo", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  webinfoService.delete(id);
        CacheManager.getInstance().getCache(WebinfoService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_webinfo",false);
    }

}
