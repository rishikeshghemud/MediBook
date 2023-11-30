package com.cambotutorial.sovary.qrscanner;

public class PlantInfo {
    String name, species, info, image_link;


    public PlantInfo() {}


    public PlantInfo(String name, String species, String info, String image_link) {
        this.name = name;
        this.species = species;
        this.info = info;
        this.image_link = image_link;
    }

    public String plant_toString(){
        return "Name: "+name;
    }
}
