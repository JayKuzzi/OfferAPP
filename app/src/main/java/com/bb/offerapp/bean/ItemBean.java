package com.bb.offerapp.bean;

/**
 * Created by bb on 2017/4/18.
 */

public class ItemBean {
    private int imageId;
    private String name;

    public ItemBean(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
