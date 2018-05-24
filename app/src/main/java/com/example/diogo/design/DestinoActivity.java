package com.example.diogo.design;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class DestinoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle dtoggle;
    private RecyclerView recycler;
    private AdapterPontos mAdapter;
    private ArrayList<Local> listaPontos;
    private NavigationView navigation;
    private ArrayAdapter<Local>lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destino);

        menus();


        // Reclycer View
         recycler = (RecyclerView) findViewById(R.id.recyclerView);
         recycler.setHasFixedSize(true);
         recycler.setLayoutManager(new LinearLayoutManager(this));
         listaPontos = new ArrayList<Local>( );

         listaPontos = DBmanager.getDBManager().getPontos();

         mAdapter = new AdapterPontos(listaPontos, this);
         recycler.setAdapter(mAdapter);

         /*final ImageView addFav = findViewById(R.id.addfav);
         addFav.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });

         ;*/


    }


    @Override
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
                    public boolean onQueryTextChange(String s)
                    {

                        mAdapter.getFilter().filter(s);
                        return false;
                    }
                }
        );
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(dtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void menus(){
        // Toolbar_searh
        toolbar = (Toolbar) findViewById(R.id.searchMenu);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout)findViewById(R.id.drawerSearch);
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
