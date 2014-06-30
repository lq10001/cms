package com.ly.comm;

import org.nutz.dao.Condition;
import org.nutz.dao.Sqls;
import org.nutz.dao.entity.Entity;
import org.nutz.dao.sql.Sql;

import java.util.List;


public abstract class BaseSrv<T> extends MyEntityService<T> {
	
	public T insert(T t) {
		return this.dao().insert(t);
	}
	
	public int  update(T t) {
		return this.dao().updateIgnoreNull(t);
	}
	
	public int del(String id) {
		return this.delete(id);
	}
	
	public int del(Long id) {
		return this.delete(id);
	}
	
	public int dels(Condition c){
		return this.dao().clear(getEntityClass(),c);
	}
	
	public T queryObj(String id) {
		return this.fetch(id);
	}
	
	public T queryObj(Long id) {
		return this.fetch(id);
	}
	
	public T queryObj(Condition c) {
		return this.dao().fetch(getEntityClass(),c);
	}
	

	public int cnt() {
		return this.dao().count(getEntityClass());
	}
	
	public int cnt(Condition c) {
		return this.dao().count(getEntityClass(),c);
	}

	
	public List<T> queryObjs() {
		return this.dao().query(getEntityClass(),null, null);
	}
	
	public List<T> queryObjs(Condition c) {
		return this.dao().query(getEntityClass(),c, null);
	}
	
	
	public List<T>  queryObjs(Condition c,Page p) {
		List<T> objs =  this.dao().query(getEntityClass(), c, p);
		return objs;
	}
	
	/**
	 * 自定义SQL查询
	 * @param sqls
	 * @param c
	 * @return
	 */
	public List<T> queryObjs(String sqls, Condition c) {
		Sql sql = Sqls.create(sqls);
		sql.setCallback(Sqls.callback.entities());
		Entity<T> entity = this.dao().getEntity(getEntityClass());
		sql.setEntity(entity).setCondition(c);
		this.dao().execute(sql);
		return sql.getList(getEntityClass());
	}
	
}

