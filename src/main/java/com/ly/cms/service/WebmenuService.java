package  com.ly.cms.service;

import com.ly.cms.vo.Webmenu;
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
public class WebmenuService extends IdEntityService<Webmenu> {

	public static String CACHE_NAME = "webmenu";
    public static String CACHE_COUNT_KEY = "webmenu_count";

    public List<Webmenu> queryCache(Condition c,Page p)
    {
        List<Webmenu> list_webmenu = null;
        String cacheKey = "webmenu_list_" + p.getPageCurrent();

        Cache cache = CacheManager.getInstance().getCache(CACHE_NAME);
        if(cache.get(cacheKey) == null)
        {
            list_webmenu = this.query(c, p);
            cache.put(new Element(cacheKey, list_webmenu));
        }else{
            list_webmenu = (List<Webmenu>)cache.get(cacheKey).getObjectValue();
        }
        return list_webmenu;
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


