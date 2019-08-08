package com.notes.zk.newnotes.bean;

/**
 * Created by ZK on 2018/1/11.
 */

public class Cabinet {

    private String name;

    private int code;

    private int proportion;

    private boolean open;

    private int state; //1启用 2不启用

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
