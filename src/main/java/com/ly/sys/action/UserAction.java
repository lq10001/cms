package com.ly.sys.action;

import com.ly.comm.Dwz;
import com.ly.comm.Page;
import com.ly.comm.ParseObj;
import com.ly.sys.service.UserService;
import com.ly.sys.vo.User;
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

@IocBean
@At("/user")
@Fail("json")
@Filters(@By(type=CheckSession.class, args={"username", "/WEB-INF/login.html"}))
public class UserAction {

	private static final Log log = Logs.getLog(UserAction.class);

	@Inject
	private Dao dao;
	
	@Inject
	private UserService userService;

    @At("/")
    @Ok("beetl:/WEB-INF/sys/user_list.html")
    public void index(@Param("..")Page p,
                      @Param("..")User user,
                      HttpServletRequest request){
        Cnd c = new ParseObj(user).getCnd();
        List<User> list_m = userService.query(c, p);
        p.setRecordCount(userService.count(c));

        request.setAttribute("list_obj", list_m);
        request.setAttribute("page", p);
        request.setAttribute("user", user);
    }

    @At
    @Ok("beetl:/WEB-INF/sys/user.html")
    public void edit(@Param("id")Long id,
                      HttpServletRequest request){
        if(id == null || id == 0){
            request.setAttribute("user", null);
        }else{
            request.setAttribute("user", userService.fetch(id));
        }
    }

    @At
    @Ok("json")
    public Map<String,String> save( @Param("..")User user){
        Object rtnObject;
        if (user.getId() == null || user.getId() == 0) {
            rtnObject = userService.dao().insert(user);
        }else{
            rtnObject = userService.dao().updateIgnoreNull(user);
        }
        return Dwz.rtnMap((rtnObject == null) ? false : true, "user", "closeCurrent");
    }

    @At
    @Ok("json")
    public Map<String,String> del(@Param("id")Long id)
    {
        int num =  userService.delete(id);
        return Dwz.rtnMap((num > 0) ? true : false , "user", "");
    }

}
