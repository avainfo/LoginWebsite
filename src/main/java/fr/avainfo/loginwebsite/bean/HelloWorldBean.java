package fr.avainfo.loginwebsite.bean;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;

@ManagedBean()
@RequestScoped
public class HelloWorldBean {
    private String msg;

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    @PostConstruct
    private void init() {

        msg = "Hello World!! JFS example.. ";
    }

}