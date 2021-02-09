/***
 * Ajax 调用服务器端返回的JSON实体类。 一般应该和@ResponseBody一起使用。
 * 后台所有的 AJAX 调用都应该返回此类
 * @author
 */
package com.dormitory.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Data
public class AjaxResponse implements Serializable {

    public static final int SUCCESS_TYPE_SUCCESS = 1;   // success
    public static final int SUCCESS_TYPE_FAIL = 0;      // fail;

    private int success = SUCCESS_TYPE_SUCCESS;

    private String errorMsg = "";

    /**
     * 装载数据信息的域，键是域名称，值可以是任何对象
     */
    private Map<String, Object> dataMap = new HashMap<>();

    public static AjaxResponse buildSuccessResponse() {
        return new AjaxResponse();
    }

    public static AjaxResponse buildFailResponse() {
        AjaxResponse response = new AjaxResponse();
        response.setSuccess(SUCCESS_TYPE_FAIL);
        return response;
    }

    /**
     * 放置要返回的数据项列表
     *
     * @param dataMap
     */
    public void putData(Map<String, String> dataMap) {
        if (dataMap == null) return;

        for (Iterator<String> iterator = dataMap.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            putData(key, dataMap.get(key));
        }
    }

    /**
     * 放置默认的数据Key
     * @param data
     */
    public void putData(Object data) {
        this.putData("data", data);
    }

    public void putData(String key, Object data) {
        if (!this.dataMap.containsKey(key)) {
            this.dataMap.put(key, data);
        }
    }

    /**
     * 放置要返回的数据项列表
     *
     * @param dataMap
     */
    public void putDataAll(Map<String, Object> dataMap) {
        this.dataMap.putAll(dataMap);
    }

    public void putError(String errorMsg) {
        this.errorMsg = errorMsg;
        this.setSuccess(SUCCESS_TYPE_FAIL);
        return;
    }

    public void removeError() {
        this.errorMsg = "";
        //如果没有错误项，则重设成功标识
        this.setSuccess(SUCCESS_TYPE_SUCCESS);
    }
}
