package com.ly.web;

import com.ly.cms.service.ProductService;
import com.ly.cms.service.WebmenuService;
import com.ly.comm.Page;
import com.ly.sys.service.InfoService;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

@IocBean
@At("/")
@Fail("json")
public class WebAction {
	
	private static final Log log = Logs.getLog(WebAction.class);

    @Inject
    private InfoService infoService;

    @Inject
    private WebmenuService webmenuService;

    @Inject
    private ProductService productService;
	
	@At("")
    @Ok("beetl:/WEB-INF/index.html")
	public void index(HttpServletRequest request)
	{
        Page p = new Page();
        p.setPageSize(8);

        request.setAttribute("action_url","#");
        request.setAttribute("webmenu_list",webmenuService.queryCache(null,new Page()));
        request.setAttribute("product_list",productService.queryCache(null,p));

        request.setAttribute("info", infoService.fetch(1L));
    }


    @At
    @Ok("beetl:/WEB-INF/index.html")
    public void newProduct(HttpServletRequest request)
    {
        String action_url =  request.getServletPath();

        System.out.println(action_url);

        request.setAttribute("action_url",action_url);
        request.setAttribute("webmenu_list",webmenuService.queryCache(null,new Page()));
        request.setAttribute("info", infoService.fetch(1L));
    }

    @At
    @Ok("beetl:/WEB-INF/linkme.html")
    public void linkme(HttpServletRequest request)
    {
        String action_url =  request.getServletPath();

        request.setAttribute("action_url",action_url);
        request.setAttribute("webmenu_list",webmenuService.queryCache(null,new Page()));
        request.setAttribute("info", infoService.fetch(1L));
    }


}
