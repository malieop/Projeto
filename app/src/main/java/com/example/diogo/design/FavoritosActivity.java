package com.example.diogo.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private RecyclerView.Adapter mAdapter;
    private List<Local> listaPontos;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle dtoggle;
    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favoritos_menu);

        menu();


        // Reclycer View
        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        listaPontos = new ArrayList<Local>( );

        listaPontos = DBmanager.getDBManager().getPontosFav();

        mAdapter = new AdapterFavoritos(listaPontos, this);
        recycler.setAdapter(mAdapter);


        ImageView fab = (ImageView) findViewById(R.id.addfav);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent ;
                intent = new Intent(getApplicationContext(),AddFavoritosActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(dtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void menu(){
        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.barra_menu);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout)findViewById(R.id.drawer);
        dtoggle = new ActionBarDrawerToggle(this, drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(dtoggle);
        dtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigation = (NavigationView) findViewById(R.id.navigation);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Intent intent;
                switch (id){
                    case R.id.Home:
                        intent = new Intent(getApplicationContext(), FullscreenActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Destino:
                        intent = new Intent(getApplicationContext(), DestinoActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.Favoritos:
                        intent = new Intent(getApplicationContext(), FavoritosActivity.class);
                        startActivity(intent);
                        break;

                }
                return false;
            }
        });

    }


}
