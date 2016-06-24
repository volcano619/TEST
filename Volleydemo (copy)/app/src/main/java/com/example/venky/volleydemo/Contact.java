package com.example.venky.volleydemo;

/**
 * Created by venky on 14/6/16.
 */
public class Contact {
   private String rtrname,ctgname,rtrphoneno;

    public Contact (String rtrname,String ctgname,String rtrphoneno){
        this.setRtrname(rtrname);
        this.setCtgname(ctgname);
        this.setRtrphoneno(rtrphoneno);

    }

    public String getRtrname() {
        return rtrname;
    }

    public void setRtrname(String rtrname) {
        this.rtrname = rtrname;
    }

    public String getRtrphoneno (){
        return rtrphoneno;
    }

    public void setRtrphoneno(String rtrphoneno){
        this.rtrphoneno = rtrphoneno;
    }

    public String getCtgname() {
        return ctgname;
    }

    public void setCtgname(String ctgname) {
        this.ctgname = ctgname;
    }


}