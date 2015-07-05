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


import com.ly.cms.vo.Ad;
import com.ly.cms.service.AdService;


@IocBean
@At("/ad")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class AdAction {

	private static final Log log = Logs.getLog(AdAction.class);
	
	@Inject
	private AdService adService;

    @At("/")
    @Ok("beetl:/WEB-INF/cms/ad_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Ad ad,
                      HttpServletRequest request){

        Cnd c = new ParseObj(ad).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(adService.listCount(c));
            request.setAttribute("list_obj", adService.queryCache(c,p));
        }else{
            p.setRecordCount(adService.count(c));
            request.setAttribute("list_obj", adService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("ad", ad);
    }

    @At
    @Ok("beetl:/WEB-INF/cms/ad.html")
    public void edit(@Param("action")int action,
                     @Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("ad", null);
        }else{

            Ad ad = adService.fetch(id);
            if (action == 3)
            {
                //ad.setName(null);
            }
            request.setAttribute("ad", ad);
        }
        request.setAttribute("action", action);
    }

    @At
    @Ok("json")
    public Map<String,String> save(@Param("action")int action,
                                @Param("..")Ad ad){
        Object rtnObject;
        if (ad.getId() == null || ad.getId() == 0) {
            rtnObject = adService.dao().insert(ad);
        }else{
            if (action == 3) {
                ad.setId(null);
                rtnObject = adService.dao().insert(ad);
            }else{
                rtnObject = adService.dao().updateIgnoreNull(ad);
            }
        }
        CacheManager.getInstance().getCache(AdService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_ad", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  adService.delete(id);
        CacheManager.getInstance().getCache(AdService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_ad",false);
    }

}
