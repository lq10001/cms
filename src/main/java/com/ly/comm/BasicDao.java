package com.ly.comm;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;

import java.util.List;

/**
 * 基本数据库操作类
 * @author Administrator
 *
 */
@IocBean
public class BasicDao  {
	
	@Inject
	protected Dao dao;
	
	public <T> T query(Class<T> t,long id) {
		return dao.fetch(t, id);
	}
	
	public <T> T query(Class<T> t,String id) {
		return dao.fetch(t, id);
	}
	
	public <T> T save(T t){
		return dao.insert(t);
	}
	
	public <T> int update(T t){
		return dao.update(t);
	}
	
	public <T> int updateList(T t,Cnd c){
		return dao.update(t.getClass(), Chain.make("dead", true), c);
	}
	
	public <T> int del(Class<T> t,long id) {
		return dao.delete(t,id);
	}
	
	public <T> int del(Class<T> t,String id) {
		return dao.delete(t,id);
	}
	
	public <T> int Count(Class<T> t){
		return dao.count( t);
	}
	
	public <T> int Count(Class<T> t,Cnd c){
		return dao.count( t,c);
	}
	
	public <T> List<T>  query(Class<T> t)
	{
		return dao.query(t, null);
	}
	
	public <T> List<T>  query(Class<T> t,Cnd c)
	{
		return dao.query(t, c);
	}
}
