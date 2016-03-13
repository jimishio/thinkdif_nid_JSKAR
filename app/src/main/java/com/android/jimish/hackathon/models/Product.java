package com.android.jimish.hackathon.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jimish on 13/3/16.
 */
public class Product {
    private String name;
    private String price;
    private String imgUrl;
    private String description;

    public Product(){}

    public Product(String name, String price, String imgUrl, String description) {
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public JSONObject getJson(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("name",this.getName());
            jsonObject.put("price",this.getPrice());
            jsonObject.put("url", this.getImgUrl());
            jsonObject.put("desc",this.getDescription());
        }catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    public Product getObj(JSONObject jsonObject){
        Product product;
        try {
            product = new Product(jsonObject.getString("name"),jsonObject.getString("price"), jsonObject.getString("url"), jsonObject.getString("desc"));
        } catch (JSONException e) {
            product = new Product();
            e.printStackTrace();
        }
        return product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
