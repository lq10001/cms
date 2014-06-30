package com.ly;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.view.JspView;

import javax.servlet.http.HttpServletRequest;

@IocBean
@At("/")
@Fail("json")
public class IndexAction {
	
	private static final Log log = Logs.getLog(IndexAction.class);
	
	@At("")
    @Ok("beetl:/WEB-INF/login.html")
	public void index(HttpServletRequest request)
	{
    }
}
