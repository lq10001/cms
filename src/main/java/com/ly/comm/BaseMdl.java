package com.ly.comm;

import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;

import javax.servlet.http.HttpServletRequest;

public class BaseMdl {
	

	public String getEid(HttpServletRequest request){
//		Employee e = (Employee)request.getSession().getAttribute("user");
//		return e.getEmployeeid();
		return "201009121550138481";
	}
	
	
	/**
	 * get Hospitalid
	 * @param request
	 * @return
	 */
	public  String getHid(HttpServletRequest request){
//		Employee e = (Employee)request.getSession().getAttribute("user");
//		Employee e = new Employee();
//		e.setEmployeeid("201009121550138481");
//		e.setEmployeename("qq");
//		e.setHospitalid("201009042206118841");
		return "201009042206118841";
	}
	
	public Cnd getCnd(HttpServletRequest request){
		return  Cnd.where("state", "=", 1).and("hospitalid","=",getHid(request));
	}
	
	public Cnd getCndWithoutState(HttpServletRequest request){
		return Cnd.where("hospitalid", "=", getHid(request));
	}
	
	public Condition getCnd(HttpServletRequest request,String os){
		Condition c = Cnd.where("state", "=", 1).and("hospitalid","=",getHid(request)).asc(os);
		return c;
	}
	
	
}
