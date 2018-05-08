package com.example.diogo.design;

/**
 * Created by diogo on 08-05-2018.
 */

public class Local {
    private String url;
    private String localizazao;


    public Local(String url, String localizacao) {
        this.url= url;
        this.localizazao= localizacao;
    }

    public String getUrl() {
        return url;
    }

    public String getLocalizazao() {
        return localizazao;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLocalizazao(String localizazao) {
        this.localizazao = localizazao;
    }
}
