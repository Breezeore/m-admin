package com.spring.app.utils.DataProcessing;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

@Component
public class JsonUtil {
    /**
     * 对象转换成JSON字符串
     *
     * @param obj
     *            需要转换的对象
     * @return 对象的string字符
     */
    public  String objToJson(Object obj) {
        JSONObject jSONObject = JSONObject.fromObject(obj);
        return jSONObject.toString();
    }

    /**
     * JSON字符串转换成对象
     *
     * @param jsonString
     *            需要转换的字符串
     * @param type
     *            需要转换的对象类型
     * @return 对象
     */
    public  <T> T jsonToBean(String jsonString, Class<T> type) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        return jsonToBean(jsonObject, type);
    }


    /**
     * jsonObject 转换为javabean
     *
     * @param jsonObject
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToBean(JSONObject jsonObject, Class<T> type) {
        return (T) JSONObject.toBean(jsonObject, type);
    }

    /**
     * json 转换为 beanList
     *
     * @param jsonArray
     * @param type
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public  List jsonToBeanList(JSONArray jsonArray, Class type) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : jsonArray) {
            if (obj instanceof JSONArray) {
                list.add(jsonToBeanList((JSONArray) obj, type));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToBean((JSONObject) obj, type));
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 将JSONArray对象转换成list集合
     *
     * @param jsonArr
     * @return
     */
    public List<Object> jsonToMapList(JSONArray jsonArr) {
        List<Object> list = new ArrayList<Object>();
        for (Object obj : jsonArr) {
            if (obj instanceof JSONArray) {
                list.add(jsonToMapList((JSONArray) obj));
            } else if (obj instanceof JSONObject) {
                list.add(jsonToMap((JSONObject) obj));
            } else {
                list.add(obj);
            }
        }
        return list;
    }

    /**
     * 将json字符串转换成map对象
     *
     * @param json
     * @return
     */
    public Map<String, Object> jsonToMap(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        return jsonToMap(obj);
    }

    /**
     * 将JSONObject转换成map对象
     *
     * @param json
     * @return
     */
    public Map<String, Object> jsonToMap(JSONObject obj) {
        Set<?> set = obj.keySet();
        Map<String, Object> map = new HashMap<String, Object>(set.size());
        for (Object key : obj.keySet()) {
            Object value = obj.get(key);
            if (value instanceof JSONArray) {
                map.put(key.toString(), jsonToMapList((JSONArray) value));
            } else if (value instanceof JSONObject) {
                map.put(key.toString(), jsonToMap((JSONObject) value));
            } else {
                map.put(key.toString(), obj.get(key));
            }
        }
        return map;
    }


    public String getIpAddr(HttpServletRequest request) {
        String[] headersToCheck = {"x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP"};
        String ipAddress = null;

        for (String header : headersToCheck) {
            ipAddress = request.getHeader(header);
            if (ipAddress != null && ipAddress.length() > 0 && !"unknown".equalsIgnoreCase(ipAddress)) {
                break;
            }
        }

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                try {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }

        // 通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null) {
            int commaIndex = ipAddress.indexOf(",");
            if (commaIndex != -1) {
                return ipAddress.substring(0, commaIndex);
            } else {
                return ipAddress;
            }
        } else {
            return "";
        }
    }
}