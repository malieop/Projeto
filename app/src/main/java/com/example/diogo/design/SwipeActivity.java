package com.example.diogo.design;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class SwipeActivity extends AppCompatActivity {
    private ViewPager viewpager;
    private SwipeAdapter swipeadapter;
    private ArrayList<String> imagens_url;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle dtoggle;
    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caminho);
        menus();
        urlImagens();



        viewpager = (ViewPager)findViewById(R.id.viewpager);
        swipeadapter = new SwipeAdapter(this, imagens_url);
        viewpager.setAdapter(swipeadapter);


    }
    public void urlImagens(){
            imagens_url = new ArrayList<String>();
            Local inicial = FullscreenActivity.getLocal_inicial();
            Log.i("local", inicial.getUrl());
            Local destino = AdapterPontos.getDestino();
            Local escada =  DBmanager.getEscada(inicial);
            imagens_url.add(inicial.getUrl());

            // mensagem intercalada com a imagem,
        //  mensagem antes da imagem das escadas ou da imagem de destino dependendo do caso
            if(inicial.getAndar() == destino.getAndar() ){
                if(inicial.getTipo().equals("corredor")){
                    imagens_url.add("Você já se encontra no corredor correto para o sitío que pretende ir.");
                }
                else{
                    imagens_url.add("Dirija-se até ao corredor deste andar.");
                }
            }
            else{
                imagens_url.add("Dirija-se até as escadas mais próximas.");
            }

            // Adiciona escada e a mensagem com o andar a que se deve dirijir
            if(inicial.getAndar() != destino.getAndar()) {
                imagens_url.add(escada.getUrl());
                imagens_url.add("Vá até ao andar "+destino.getAndar()+".");
            }
            // imagem de destino e a mensagem a finalizar
            imagens_url.add(destino.getUrl());
            imagens_url.add("A seta corresponde ao destino pretendido.");
    }
    public void menus(){
        // Toolbar
        toolbar = (Toolbar) findViewById(R.id.barra_menu);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout)findViewById(R.id.drawer);
        dtoggle = new ActionBarDrawerToggle(this, drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(dtoggle);
        dtoggle.syncState();
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //navigation

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
