package com.hibi.www.tools;


import java.util.ArrayList;
import java.util.List;

/**
 * json数据实体，返回类型JSON，VIEW
 * @author penglei
 * @datetime 2018年8月5日
 */

public class Pages {

    private int page;
    private int rows;
    private String msg;
    private int code;
    private long total;
    private List data = new ArrayList<>();
    private String dts;

    public String getDts() {
        return dts;
    }

    public void setDts(String dts) {
        this.dts = dts;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "\"code\":" + code +
                ", \"msg\":" +"\""+ msg +"\"" +
                ", \"count\":" + total +
                ", \"data\":" + (getData().size()>0?getData().toString():getDts())+
                "}";
    }
}
