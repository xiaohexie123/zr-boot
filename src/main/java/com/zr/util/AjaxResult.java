package com.zr.util;

/**
 * 操作结果类
 */
public class AjaxResult {

    private boolean success;

    private String msg;

    private Object obj;

    public AjaxResult() {
    }

    public AjaxResult(boolean success,  String msg) {
        this.success = success;
        this.msg = msg;
    }

    public AjaxResult(boolean success, String msg, Object obj) {
        this.success = success;
        this.msg = msg;
        this.obj = obj;
    }

    /**
     * 操作成功方法，一般用于新增、修改、删除方法
     * @param msg
     * @param msg
     * @return
     */
    public static AjaxResult success(String msg){
        return new AjaxResult(true, msg);
    }

    /**
     * 操作成功方法，一般用于查询列表
     * @param msg
     * @param obj
     * @return
     */
    public static AjaxResult success(String msg, Object obj){
        return new AjaxResult(true, msg, obj);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
