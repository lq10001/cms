package com.ly.cms.vo;

import java.util.Date;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.View;

@Table("webinfo")
public class Webinfo{

	@Id
	@Column
	private Long id;

	@Column
	private String webname;

	@Column
	private String weburl;

	@Column
	private String logourl;

	@Column
	private String slogourl;

	@Column
	private String mlogourl;

	@Column
	private String recordno;

	@Column
	private String phone;

	@Column
	private String qq;

	@Column
	private String email;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWebname() {
		return webname;
	}

	public void setWebname(String webname) {
		this.webname = webname;
	}

	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public String getLogourl() {
		return logourl;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}

	public String getSlogourl() {
		return slogourl;
	}

	public void setSlogourl(String slogourl) {
		this.slogourl = slogourl;
	}

	public String getMlogourl() {
		return mlogourl;
	}

	public void setMlogourl(String mlogourl) {
		this.mlogourl = mlogourl;
	}

	public String getRecordno() {
		return recordno;
	}

	public void setRecordno(String recordno) {
		this.recordno = recordno;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
