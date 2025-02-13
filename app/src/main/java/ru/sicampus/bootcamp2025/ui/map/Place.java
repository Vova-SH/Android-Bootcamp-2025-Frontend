package ru.sicampus.bootcamp2025.ui.map;

import com.google.android.gms.maps.model.LatLng;

public class Place {
    //private final String photo;
    //private final String addres;
    private final LatLng LatLng;
    private final Integer id;

    /*public String getPhoto() {
        return photo;
    }*/

    /*public String getAddres() {
        return addres;
    }*/

    public com.google.android.gms.maps.model.LatLng getLatLng() {
        return LatLng;
    }

    public Integer getId() {
        return id;
    }

    /*public String getInfo() {
        return info;
    }*/

    //private final String info;


    public Place(Integer id, com.google.android.gms.maps.model.LatLng latLng) {
        //this.photo = photo;
        //this.addres = addres;
        LatLng = latLng;
        this.id = id;
        //this.info = info;
    }
}
