package com.ly.web;

import com.ly.sys.service.InfoService;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import javax.servlet.http.HttpServletRequest;

@IocBean
@At("/")
@Fail("json")
public class WebAction {
	
	private static final Log log = Logs.getLog(WebAction.class);

    @Inject
    private InfoService infoService;
	
	@At("")
    @Ok("beetl:/WEB-INF/index.html")
	public void index(HttpServletRequest request)
	{
        request.setAttribute("info", infoService.fetch(1L));
    }


    @At
    @Ok("beetl:/WEB-INF/product.html")
    public void product(HttpServletRequest request)
    {
        request.setAttribute("info", infoService.fetch(1L));
    }
}
