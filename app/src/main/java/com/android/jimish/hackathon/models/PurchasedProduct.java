package com.android.jimish.hackathon.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jimish on 13/3/16.
 */
public class PurchasedProduct {
    private String title;
    private String price;
    private String imgUrl;
    private String umUrl;
    private String billUrl;
    private String warrantyUrl;

    public PurchasedProduct() {
    }

    public JSONObject getJson(){
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("title", this.getTitle());
            jsonObject.put("price", this.getPrice());
            jsonObject.put("imgUrl", this.getImgUrl());
            jsonObject.put("umUrl", this.umUrl);
            jsonObject.put("bill", this.billUrl);
            jsonObject.put("warranty", this.warrantyUrl);
        } catch (JSONException e){
            e.printStackTrace();
        }
        return jsonObject;
    }

    public PurchasedProduct getObj(JSONObject jsonObject){
        PurchasedProduct product;
        try {
            product = new PurchasedProduct(jsonObject.getString("title"),
                    jsonObject.getString("price"),
                    jsonObject.getString("imgUrl"),
                    jsonObject.getString("umUrl"),
                    jsonObject.getString("bill"),
                    jsonObject.getString("warranty"));
        } catch (JSONException e) {
            product = new PurchasedProduct();
            e.printStackTrace();
        }
        return product;
    }

    public PurchasedProduct(String title, String price, String imgUrl, String umUrl, String billUrl, String warrantyUrl) {
        this.title = title;
        this.price = price;
        this.imgUrl = imgUrl;
        this.umUrl = umUrl;
        this.billUrl = billUrl;
        this.warrantyUrl = warrantyUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getUmUrl() {
        return umUrl;
    }

    public void setUmUrl(String umUrl) {
        this.umUrl = umUrl;
    }

    public String getBillUrl() {
        return billUrl;
    }

    public void setBillUrl(String billUrl) {
        this.billUrl = billUrl;
    }

    public String getWarrantyUrl() {
        return warrantyUrl;
    }

    public void setWarrantyUrl(String warrantyUrl) {
        this.warrantyUrl = warrantyUrl;
    }
}
