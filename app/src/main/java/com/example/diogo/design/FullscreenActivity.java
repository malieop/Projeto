package com.example.diogo.design;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import android.support.v7.widget.SearchView;

/*import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;*/
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle dtoggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);



        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.barra_menu);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout)findViewById(R.id.drawer);
        dtoggle = new ActionBarDrawerToggle(this, drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(dtoggle);
        dtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        /*Glide.with(this).asBitmap().load("https://i.imgur.com/DM29g0M.jpg").into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    drawer.setBackground(new BitmapDrawable(resource));
                }
            }
        });*/


        if (!DBmanager.databaseExists()) {
            DBmanager.initDatabase();
        }
        DBmanager.getDBManager().inserePontos(this);


        // teste do local
        //Local l = DBmanager.getLocalizacao("333333");


        Picasso.get().load("https://i.imgur.com/DM29g0M.jpg").into(new Target() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)


            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("TAG", "cheguei bitch");
               drawer.setBackground(new BitmapDrawable(bitmap));
                Log.d("TAG", "bora imagem");
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.d("TAG", "Failes");

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.d("TAG1","bosta");
            }
        });

        //drawer.setBackgroundResource(R.drawable.foto_corredor);

        // Botões
        final FloatingActionButton favoritos = findViewById(R.id.favoritos);
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(),DestinoActivity.class);
                startActivity(intent);
            }
        });

        final FloatingActionButton destino = findViewById(R.id.direcoes);
        destino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getApplicationContext(),SwipeActivity.class);
                startActivity(intent);
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return true;

                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        return false;
                    }
                }
        );
        return true;

    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(dtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
