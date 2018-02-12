package com.example.administrator.imovie.comand;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class RotateScreenCommand {
    private boolean b = true ;
    private String classname ;
    public RotateScreenCommand(boolean b , String classname){
        this.b = b ;
        this.classname = classname ;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }
}
