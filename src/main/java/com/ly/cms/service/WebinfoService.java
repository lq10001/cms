package  com.ly.cms.service;

import com.ly.cms.vo.Webinfo;
import org.nutz.dao.Condition;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nutz.dao.Cnd;
import com.ly.comm.Page;

import java.util.List;


@IocBean(fields = { "dao" })
public class WebinfoService extends IdEntityService<Webinfo> {

	public static String CACHE_NAME = "webinfo";
    public static String CACHE_COUNT_KEY = "webinfo_count";

    public List<Webinfo> queryCache(Condition c,Page p)
    {
        List<Webinfo> list_webinfo = null;
        String cacheKey = "webinfo_list_" + p.getPageCurrent();

        Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
        if(cache.get(cacheKey) == null)
        {
            list_webinfo = this.query(c, p);
            cache.put(new Element(cacheKey, list_webinfo));
        }else{
            list_webinfo = (List<Webinfo>)cache.get(cacheKey).getObjectValue();
        }
        return list_webinfo;
    }

    public int listCount(Condition c)
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


