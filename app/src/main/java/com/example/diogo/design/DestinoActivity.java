package com.example.diogo.design;

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

import java.util.ArrayList;
import java.util.List;

public class DestinoActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle dtoggle;
    private RecyclerView recycler;
    private RecyclerView.Adapter mAdapter;
    private List<ListaFav> listaFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destino);

        // Toolbar_searh
        toolbar = (Toolbar) findViewById(R.id.searchMenu);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout)findViewById(R.id.drawerSearch);
        dtoggle = new ActionBarDrawerToggle(this, drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(dtoggle);
        dtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        // Reclycer View
         recycler = (RecyclerView) findViewById(R.id.recyclerView);
         recycler.setHasFixedSize(true);
         recycler.setLayoutManager(new LinearLayoutManager(this));
         listaFavoritos = new ArrayList<>( );

         for (int i = 0; i <= 10;i++){
             ListaFav listaFav =  new ListaFav("Sala de Estudo 0" + (i+1));

             listaFavoritos.add(listaFav);
         }

         mAdapter = new AdapterFav(listaFavoritos, this);
         recycler.setAdapter(mAdapter);


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
                    public boolean onQueryTextChange(String s) {
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

}
