package  com.ly.cms.service;

import com.ly.cms.vo.Job;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nutz.dao.Cnd;
import com.ly.comm.Page;

import java.util.List;


@IocBean(fields = { "dao" })
public class JobService extends IdEntityService<Job> {

	public static String CACHE_NAME = "job";
    public static String CACHE_COUNT_KEY = "job_count";

    public List<Job> queryCache(Cnd c,Page p)
    {
        List<Job> list_job = null;
        String cacheKey = "job_list_" + p.getPageCurrent();

        Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
        if(cache.get(cacheKey) == null)
        {
            list_job = this.query(c, p);
            cache.put(new Element(cacheKey, list_job));
        }else{
            list_job = (List<Job>)cache.get(cacheKey).getObjectValue();
        }
        return list_job;
    }

    public int listCount(Cnd c)
    {
        Long num = 0L;
        Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
        if(cache.get(CACHE_COUNT_KEY) == null)
        {
            num = Long.valueOf(this.count(c));
            cache.put(new Element(CACHE_COUNT_KEY, num));
        }else{
            num = (Long)cache.get(CACHE_COUNT_KEY).getObjectValue();
        }
        return num.intValue();
    }
}

