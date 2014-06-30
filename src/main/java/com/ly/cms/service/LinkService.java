package  com.ly.cms.service;

import com.ly.cms.vo.Link;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;

import java.util.List;


@IocBean(fields = { "dao" })
public class LinkService extends IdEntityService<Link> {
}


