package com.example.harshupatel.inviko.Responese;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoleResponse {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}