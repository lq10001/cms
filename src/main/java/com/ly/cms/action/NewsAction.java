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
        List<News> list_m = newsService.query(c, p);
        p.setRecordCount(newsService.count(c));

        request.setAttribute("list_obj", list_m);
        request.setAttribute("page", p);
        request.setAttribute("news", news);
    }

    @At
    @Ok("beetl:/WEB-INF/cms/news.html")
    public void edit(@Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("news", null);
        }else{
            request.setAttribute("news", newsService.fetch(id));
        }
    }

    @At
    @Ok("json")
    public Map<String,String> save( @Param("..")News news){
        Object rtnObject;
        if (news.getId() == null || news.getId() == 0) {
            rtnObject = newsService.dao().insert(news);
        }else{
            rtnObject = newsService.dao().updateIgnoreNull(news);
        }
        return Dwz.rtnMap((rtnObject == null) ? false : true, "news", "closeCurrent");
    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  newsService.delete(id);
        return Dwz.rtnMap((num > 0) ? true : false , "news", "");
    }

}
