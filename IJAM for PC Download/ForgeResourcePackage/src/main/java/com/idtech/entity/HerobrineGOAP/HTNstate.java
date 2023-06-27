package com.idtech.entity.HerobrineGOAP;

import java.util.Dictionary;

public class HTNstate {
    public String name;
    public Dictionary<String, Object> items;

    public HTNstate(String name){
        this.name = name;
        items.put("example", 30);
    }

    public void test(){
        //if(getClass(items.get("example")) == int)
    }
}
