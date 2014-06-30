package com.ly.comm;

import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.EntityField;
import org.nutz.service.EntityService;

public class MyEntityService<T> extends EntityService<T> {

	public MyEntityService() {
		super();
	}

	public MyEntityService(Dao dao) {
		super(dao);
	}

	public MyEntityService(Dao dao, Class<T> entityType) {
		super(dao, entityType);
	}

	public T fetch(long id) {
		return dao().fetch(getEntityClass(), id);
	}

	public T fetch(String name) {
		return dao().fetch(getEntityClass(), name);
	}

	public T smartFetch(String str) {
		try {
			long id = Long.parseLong(str);
			return fetch(id);
		} catch (Exception e) {
		}
		return fetch(str);
	}

	public int delete(String name) {
		return dao().delete(getEntityClass(), name);
	}

	public int delete(long id) {
		return dao().delete(getEntityClass(), id);
	}

	public int getMaxId() {
		return dao().getMaxId(getEntityClass());
	}

	public boolean exists(long id) {
		EntityField ef = getEntity().getIdField();
		if (null == ef)
			return false;
		return dao().count(getEntityClass(), Cnd.where(ef.getName(), "=", id)) > 0;
	}

	public boolean exists(String name) {
		EntityField ef = getEntity().getNameField();
		if (null == ef)
			return false;
		return dao()
				.count(getEntityClass(), Cnd.where(ef.getName(), "=", name)) > 0;
	}

}
