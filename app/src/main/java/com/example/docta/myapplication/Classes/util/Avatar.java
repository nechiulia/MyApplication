package com.example.docta.myapplication.Classes.util;

import java.io.Serializable;
import java.util.Arrays;

public class Avatar implements Serializable {
    private Long id;
    String name;
    Double price;
    byte[] image;
    long appAvatar;
    public Avatar(byte [] image){
        this.id=Long.parseLong("-3");
        this.name="Avatar propriu";
        price=500.0;
        this.image=image;
        appAvatar=0;
    }
    public Avatar(Long id, String name, Double price, byte[] image, long appAvatar) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.appAvatar = appAvatar;
    }

    public Avatar(String name, Double price, byte[] image, long appAvatar) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.appAvatar = appAvatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public long getAppAvatar() {
        return appAvatar;
    }

    public void setAppAvatar(long appAvatar) {
        this.appAvatar = appAvatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Avatar{" +
              //  "id=" + id +
                "  name='" + name + '\'' +
                ", price=" + price +
                ", image=" + Arrays.toString(image) +
                ", appAvatar=" + appAvatar +
                '}';
    }
}
