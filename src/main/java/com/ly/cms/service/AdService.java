package  com.ly.cms.service;

import com.ly.cms.vo.Ad;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import java.util.List;


@IocBean(fields = { "dao" })
public class AdService extends IdEntityService<Ad> {
}


