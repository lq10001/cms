package com.ly.comm;

import java.util.HashMap;
import java.util.Map;

public class CommAction {
	
	public Map<String,String> tabMap(String rtnNum,String tabId,String callBackType){
		Map<String, String> map = new HashMap<String, String>();  
		map.put("statusCode", rtnNum);
		map.put("message", rtnNum.equals("200")? "操作成功":"操作失败");
		map.put("navTabId", tabId);
		map.put("callbackType", callBackType);
		map.put("forwardUrl", "");
		return map;
	}
}
