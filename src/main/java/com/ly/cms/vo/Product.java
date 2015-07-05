package com.ly.cms.vo;

import java.util.Date;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.View;

@Table("product")
public class Product{

	@Id
	@Column
	private Long id;

	@Column
	private String name;

	@Column
	private String smallimage;

	@Column
	private String maximage;

	@Column
	private String descript;

	@Column
	private String dataurl;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSmallimage() {
		return smallimage;
	}

	public void setSmallimage(String smallimage) {
		this.smallimage = smallimage;
	}

	public String getMaximage() {
		return maximage;
	}

	public void setMaximage(String maximage) {
		this.maximage = maximage;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getDataurl() {
		return dataurl;
	}

	public void setDataurl(String dataurl) {
		this.dataurl = dataurl;
	}
}
