package com.example.diogo.design;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SwipeActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private SwipeAdapter swipeadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caminho);
        viewpager = (ViewPager)findViewById(R.id.viewpager);
        swipeadapter = new SwipeAdapter(this);
        viewpager.setAdapter(swipeadapter);


    }


}
