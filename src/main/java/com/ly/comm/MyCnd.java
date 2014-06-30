package com.ly.comm;

import org.nutz.dao.entity.Entity;

public  class MyCnd  {
	
	private  String s;
	
	public  MyCnd(String s){
		this.s = s;
	}

	public void render(StringBuilder sb, Entity<?> arg1) {
		sb.append(s);
	}

}
