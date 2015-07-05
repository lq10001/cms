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


import com.ly.cms.vo.News;
import com.ly.cms.service.NewsService;


@IocBean
@At("/news")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class NewsAction {

	private static final Log log = Logs.getLog(NewsAction.class);
	
	@Inject
	private NewsService newsService;

    @At("/")
    @Ok("beetl:/WEB-INF/cms/news_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")News news,
                      HttpServletRequest request){

        Cnd c = new ParseObj(news).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(newsService.listCount(c));
            request.setAttribute("list_obj", newsService.queryCache(c,p));
        }else{
            p.setRecordCount(newsService.count(c));
            request.setAttribute("list_obj", newsService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("news", news);
    }

    @At
    @Ok("beetl:/WEB-INF/cms/news.html")
    public void edit(@Param("action")int action,
                     @Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("news", null);
        }else{

            News news = newsService.fetch(id);
            if (action == 3)
            {
                //news.setName(null);
            }
            request.setAttribute("news", news);
        }
        request.setAttribute("action", action);
    }

    @At
    @Ok("json")
    public Map<String,String> save(@Param("action")int action,
                                @Param("..")News news){
        Object rtnObject;
        if (news.getId() == null || news.getId() == 0) {
            rtnObject = newsService.dao().insert(news);
        }else{
            if (action == 3) {
                news.setId(null);
                rtnObject = newsService.dao().insert(news);
            }else{
                rtnObject = newsService.dao().updateIgnoreNull(news);
            }
        }
        CacheManager.getInstance().getCache(NewsService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_news", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  newsService.delete(id);
        CacheManager.getInstance().getCache(NewsService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_news",false);
    }

}
