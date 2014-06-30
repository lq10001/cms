package com.ly.sys.service;

import com.ly.sys.vo.Menu;
import com.ly.sys.vo.User;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdEntityService;


@IocBean(fields = { "dao" })
public class UserService extends IdEntityService<User> {
}

