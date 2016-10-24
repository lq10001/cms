package  com.ly.cms.service;

import com.ly.cms.vo.Ad;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.nutz.dao.Cnd;
import com.ly.comm.Page;

import java.util.List;


@IocBean(fields = { "dao" })
public class AdService extends IdEntityService<Ad> {

	public static String CACHE_NAME = "ad";
    public static String CACHE_COUNT_KEY = "ad_count";

    public List<Ad> queryCache(Cnd c,Page p)
    {
        List<Ad> list_ad = null;
        String cacheKey = "ad_list_" + p.getPageCurrent();

        Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
        if(cache.get(cacheKey) == null)
        {
            list_ad = this.query(c, p);
            cache.put(new Element(cacheKey, list_ad));
        }else{
            list_ad = (List<Ad>)cache.get(cacheKey).getObjectValue();
        }
        return list_ad;
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

