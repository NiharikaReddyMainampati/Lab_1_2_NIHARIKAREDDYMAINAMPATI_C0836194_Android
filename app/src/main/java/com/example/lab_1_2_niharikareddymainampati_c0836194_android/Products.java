package com.example.lab_1_2_niharikareddymainampati_c0836194_android;

public class Products {
    int prodId, prodPrice, lat, lon;
    String prodName, prodDesc;

    public Products(int prodId,String prodName, String prodDesc, int prodPrice, int lat, int lon) {
        this.prodId = prodId;
        this.prodPrice = prodPrice;
        this.lat = lat;
        this.lon = lon;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
    }

    public int getProdId() {
        return prodId;
    }

    public void setProdId(int prodId) {
        this.prodId = prodId;
    }

    public int getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }
}
