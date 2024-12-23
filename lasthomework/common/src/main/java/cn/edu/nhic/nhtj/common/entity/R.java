package cn.edu.nhic.nhtj.common.entity;
import lombok.Data;

@Data
public class R {
    private int code;
    private String msg;
    private Object data;


    public static R success(Object data) {
        R r= new R();
        r.setCode(200);
        r.setData(data);


        return r;
    }
    public static R error(String msg) {
        R r = new R();
        r.setCode(500);
        r.setMsg(msg);
        return r;
    }
}
