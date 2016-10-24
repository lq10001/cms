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
	private String useurl;

	@Column
	private String iosurl;

	@Column
	private String iosqrcodeurl;

	@Column
	private String iosqrcodeimg;

	@Column
	private String androidurl;

	@Column
	private String androidqrcodeurl;

	@Column
	private String androidqrcodeimg;

	@Column
	private String dataurl;

	@Column
	private String descript;


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

	public String getUseurl() {
		return useurl;
	}

	public void setUseurl(String useurl) {
		this.useurl = useurl;
	}

	public String getIosurl() {
		return iosurl;
	}

	public void setIosurl(String iosurl) {
		this.iosurl = iosurl;
	}

	public String getIosqrcodeurl() {
		return iosqrcodeurl;
	}

	public void setIosqrcodeurl(String iosqrcodeurl) {
		this.iosqrcodeurl = iosqrcodeurl;
	}

	public String getIosqrcodeimg() {
		return iosqrcodeimg;
	}

	public void setIosqrcodeimg(String iosqrcodeimg) {
		this.iosqrcodeimg = iosqrcodeimg;
	}

	public String getAndroidurl() {
		return androidurl;
	}

	public void setAndroidurl(String androidurl) {
		this.androidurl = androidurl;
	}

	public String getAndroidqrcodeurl() {
		return androidqrcodeurl;
	}

	public void setAndroidqrcodeurl(String androidqrcodeurl) {
		this.androidqrcodeurl = androidqrcodeurl;
	}

	public String getAndroidqrcodeimg() {
		return androidqrcodeimg;
	}

	public void setAndroidqrcodeimg(String androidqrcodeimg) {
		this.androidqrcodeimg = androidqrcodeimg;
	}

	public String getDataurl() {
		return dataurl;
	}

	public void setDataurl(String dataurl) {
		this.dataurl = dataurl;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}
}
