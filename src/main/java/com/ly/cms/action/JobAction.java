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


import com.ly.cms.vo.Job;
import com.ly.cms.service.JobService;


@IocBean
@At("/job")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class JobAction {

	private static final Log log = Logs.getLog(JobAction.class);
	
	@Inject
	private JobService jobService;

    @At("/")
    @Ok("beetl:/WEB-INF/cms/job_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")Job job,
                      HttpServletRequest request){

        Cnd c = new ParseObj(job).getCnd();
        if (c == null || c.equals(""))
        {
            p.setRecordCount(jobService.listCount(c));
            request.setAttribute("list_obj", jobService.queryCache(c,p));
        }else{
            p.setRecordCount(jobService.count(c));
            request.setAttribute("list_obj", jobService.query(c,p));
        }

        request.setAttribute("page", p);
        request.setAttribute("job", job);
    }

    @At
    @Ok("beetl:/WEB-INF/cms/job.html")
    public void edit(@Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("job", null);
        }else{
            request.setAttribute("job", jobService.fetch(id));
        }
    }

    @At
    @Ok("json")
    public Map<String,String> save( @Param("..")Job job){
        Object rtnObject;
        if (job.getId() == null || job.getId() == 0) {
            rtnObject = jobService.dao().insert(job);
        }else{
            rtnObject = jobService.dao().updateIgnoreNull(job);
        }
        CacheManager.getInstance().getCache(JobService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((rtnObject == null) ? false : true, "tab_job", true);

    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  jobService.delete(id);
        CacheManager.getInstance().getCache(JobService.CACHE_NAME).removeAll();
        return Bjui.rtnMap((num > 0) ? true : false , "tab_job",false);
    }

}
