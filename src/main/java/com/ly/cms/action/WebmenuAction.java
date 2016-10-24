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


import com.ly.cms.vo.Webmenu;
import com.ly.cms.service.WebmenuService;


@IocBean
@At("/webmenu")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class WebmenuAction {

	private static final Log log = Logs.getLog(WebmenuAction.class);
	
	@Inject
	private WebmenuService webmenuService;

    @At("/")
    @Ok("beetl:/WEB-INF/cms/webmenu_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Webmenu webmenu,
                      HttpServletRequest request){

        Cnd c = new ParseObj(webmenu).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(webmenuService.listCount(c));
            request.setAttribute("list_obj", webmenuService.queryCache(c,p));
        }else{
            p.setRecordCount(webmenuService.count(c));
            request.setAttribute("list_obj", webmenuService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("webmenu", webmenu);
    }

    @At
    @Ok("beetl:/WEB-INF/cms/webmenu.html")
    public void edit(@Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("webmenu", null);
        }else{
            request.setAttribute("webmenu", webmenuService.fetch(id));
        }
    }

    @At
    @Ok("json")
    public Map<String,String> save( @Param("..")Webmenu webmenu){
        Object rtnObject;
        if (webmenu.getId() == null || webmenu.getId() == 0) {
            rtnObject = webmenuService.dao().insert(webmenu);
        }else{
            rtnObject = webmenuService.dao().updateIgnoreNull(webmenu);
        }
        CacheManager.getInstance().getCache(WebmenuService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_webmenu", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  webmenuService.delete(id);
        CacheManager.getInstance().getCache(WebmenuService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_webmenu",false);
    }

}
