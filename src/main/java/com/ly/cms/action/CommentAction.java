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


import com.ly.cms.vo.Comment;
import com.ly.cms.service.CommentService;


@IocBean
@At("/comment")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class CommentAction {

	private static final Log log = Logs.getLog(CommentAction.class);
	
	@Inject
	private CommentService commentService;

    @At("/")
    @Ok("beetl:/WEB-INF/cms/comment_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Comment comment,
                      HttpServletRequest request){

        Cnd c = new ParseObj(comment).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(commentService.listCount(c));
            request.setAttribute("list_obj", commentService.queryCache(c,p));
        }else{
            p.setRecordCount(commentService.count(c));
            request.setAttribute("list_obj", commentService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("comment", comment);
    }

    @At
    @Ok("beetl:/WEB-INF/cms/comment.html")
    public void edit(@Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("comment", null);
        }else{
            request.setAttribute("comment", commentService.fetch(id));
        }
    }

    @At
    @Ok("json")
    public Map<String,String> save( @Param("..")Comment comment){
        Object rtnObject;
        if (comment.getId() == null || comment.getId() == 0) {
            rtnObject = commentService.dao().insert(comment);
        }else{
            rtnObject = commentService.dao().updateIgnoreNull(comment);
        }
        CacheManager.getInstance().getCache(CommentService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_comment", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  commentService.delete(id);
        CacheManager.getInstance().getCache(CommentService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_comment",false);
    }

}
