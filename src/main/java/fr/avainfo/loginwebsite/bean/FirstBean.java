package fr.avainfo.loginwebsite.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serializable;


@RequestScoped
@Named
public class FirstBean implements Serializable {
    private String name;
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void sayHello() {
        message = "Coucou";
    }

}
