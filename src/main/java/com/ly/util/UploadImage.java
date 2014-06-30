package com.ly.util;

import com.ly.comm.PKID;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.upload.UploadAdaptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@IocBean
@InjectName("uploadImage")
@At("/util/image")
@Fail("json")
public class UploadImage {
	
	@At
	@Ok("json")
	@Fail("json")
	@AdaptBy(type = UploadAdaptor.class, args = { "ioc:uploadFile" })
	public Map<String,Object> upload(@Param("imgFile") File file,
			HttpServletRequest request,
			ServletContext context)
	{
		Map<String, Object> map =new HashMap<String, Object>();
		if (file != null) {
			
			String ext = Files.getSuffixName(file);
			String url = "/uploadimg/" + PKID.getId() + "." + ext;
			File photo = new File(context.getRealPath("/") + url);
			try {
				Files.move(file, photo);
				map.put("error",0);
				map.put("url", request.getContextPath()+url);
			} catch (IOException e) {
				map.put("error", 1);
				map.put("message", "上传文件失败");
			}
		}else{
			map.put("error", 1);
			map.put("message", "上传文件失败");
		}
		return map;
	}

}
