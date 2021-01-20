package com.imooc.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class IMOOCJSONResult {


    //响应业务状态
    private Integer status;

    //响应消息
    private String msg;

    //响应中的数据
    private Object data;

    @JsonIgnore
    private String ok;


    public static IMOOCJSONResult build(Integer status,String msg,Object data){
        return new IMOOCJSONResult();
    }

    public static IMOOCJSONResult build(Integer status,String msg,Object data,String ok){
        return new IMOOCJSONResult(status,msg,data,ok);
    }

    public static IMOOCJSONResult ok(Object data){
        return new IMOOCJSONResult(data);
    }

    public static IMOOCJSONResult ok(){
        return new IMOOCJSONResult(null);
    }

    public static IMOOCJSONResult errorMsg(String msg){
        return new IMOOCJSONResult(500,msg,null);
    }

    public IMOOCJSONResult(Object data) {
        this.data = data;
        this.status = 200;
        this.msg = "OK";
    }

    public  IMOOCJSONResult(Integer status, String msg, Object data, String ok) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.ok = ok;
    }

    public  IMOOCJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


    public IMOOCJSONResult() {
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
