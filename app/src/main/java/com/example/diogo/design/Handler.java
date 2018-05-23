package com.example.diogo.design;

/**
 * Created by diogo on 17-05-2018.
 */

public class Handler {
    private static boolean inicia= false;


    public static boolean isInicia() {
        return inicia;
    }

    public static void setInicia(boolean inicia) {
        Handler.inicia = inicia;
    }

}
