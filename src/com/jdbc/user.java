package com.jdbc;

import java.util.Map;

public class user {
    public int id;
    public String name;
    public String password;
    public String email;
    public int is_super;

    public void init(Map<String,String[]> map){
        name=map.get("name")[0];
        password=map.get("password")[0];
        email=map.get("email")[0];
    }
}
