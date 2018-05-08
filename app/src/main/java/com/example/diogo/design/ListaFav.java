package com.example.diogo.design;

/**
 * Created by diogo on 20-04-2018.
 */

public class ListaFav {

    public String sitio;
    public String image_url;

    public ListaFav(String sitio , String image_url) {
        this.sitio = sitio;
        this.image_url= image_url;
    }
    // para eliminar depois quanto tiver o url pronto
    public ListaFav(String sitio){
        this.sitio= sitio;
    }

    public String getSitio() {
        return sitio;
    }

    public String getImage_url() {
        return image_url;
    }
}
