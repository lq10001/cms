package com.ly.comm;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Dwz {
    public static Map<String,String> rtnMap(boolean isOk,String tabId,String callBack)
    {
        Map<String, String> map = new HashMap<String, String>();
        map.put("statusCode", isOk ? "200":"300");
        map.put("message", isOk ? "操作成功":"操作失败");
        map.put("navTabId", tabId);
        map.put("callbackType", callBack);
        map.put("forwardUrl", "");
        return map;
    }
}
