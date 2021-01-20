package com.imooc.enums;

public enum SexEnum {
    women(0,"女"),
    mon(1,"男"),
    secret(2,"保密");
    public final String sex;
    public final int type;

    SexEnum(int type,String sex){
        this.sex = sex;
        this.type = type;
    }

}
