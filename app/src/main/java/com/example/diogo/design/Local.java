package com.example.diogo.design;

/**
 * Created by diogo on 08-05-2018.
 */

public class Local {
    private String url;
    private String localizacao;
    private String idaparelho;
    private int id;
    private int andar;
    private String tipo;
    private String swipe_helper;
    private int lugar;



    public Local(String url, String localizacao) {
        this.url= url;
        this.localizacao= localizacao;
    }
    public Local (String url, String localizacao, String idaparelho) {
        this.url= url;
        this.localizacao= localizacao;
        this.idaparelho= idaparelho;
    }
    public Local (int id,String url, String localizacao, int lugar) {
        this.url= url;
        this.localizacao= localizacao;
        this.id= id;
        this.lugar= lugar;
    }
    public Local(int id, String url, String localizacao, String idaparelho) {
        this.id= id;
        this.url= url;
        this.localizacao= localizacao;
        this.idaparelho= idaparelho;
    }
    public Local (int id, String url, String localizacao, String idaparelho, int andar){
        this.id= id;
        this.url= url;
        this.localizacao= localizacao;
        this.idaparelho= idaparelho;
        this.andar = andar ;
    }
    public Local(String url, String localizacao, int id,String idaparelho, int andar, String tipo) {
        this.url = url;
        this.localizacao = localizacao;
        this.idaparelho = idaparelho;
        this.id = id;
        this.andar = andar;
        this.tipo = tipo;
    }
    public Local(String url, String localizacao, String idaparelho, int id, int andar, String tipo, String swipe_helper) {
        this.url = url;
        this.localizacao = localizacao;
        this.idaparelho = idaparelho;
        this.id = id;
        this.andar = andar;
        this.tipo = tipo;
        this.swipe_helper = swipe_helper;
    }
    public Local(String url, String localizacao, String idaparelho, int id, int andar, String swipe_helper, int lugar) {
        this.url = url;
        this.localizacao = localizacao;
        this.idaparelho = idaparelho;
        this.id = id;
        this.andar = andar;
        this.swipe_helper = swipe_helper;
        this.lugar = lugar;
    }
    public Local(int id, String url, String localizacao, int andar, String swipe_helper){
        this.url = url;
        this.localizacao = localizacao;
        this.id = id;
        this.andar = andar;
        this.swipe_helper = swipe_helper;
    }
    public Local(String tipo,String url, String localizacao, String idaparelho, int id, int andar, String swipe_helper) {
        this.tipo = tipo;
        this.url = url;
        this.localizacao = localizacao;
        this.idaparelho = idaparelho;
        this.id = id;
        this.andar = andar;
        this.swipe_helper = swipe_helper;
    }
    public Local(String url, String localizacao, String idaparelho, int id, int andar, String tipo ,String swipe_helper, int lugar) {
        this.url = url;
        this.localizacao = localizacao;
        this.idaparelho = idaparelho;
        this.id = id;
        this.andar = andar;
        this.swipe_helper = swipe_helper;
        this.lugar = lugar;
        this.tipo = tipo;
    }


    public String getUrl() {
        return url;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdaparelho() {
        return idaparelho;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdaparelho(String idaparelho) {
        this.idaparelho = idaparelho;
    }
    public int getAndar() {
        return andar;
    }


    public void setAndar(int andar) {
        this.andar = andar;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSwipe_helper() {
        return swipe_helper;
    }

    public void setSwipe_helper(String swipe_helper) {
        this.swipe_helper = swipe_helper;
    }

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }
}

