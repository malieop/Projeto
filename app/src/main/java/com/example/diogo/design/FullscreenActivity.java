package com.example.diogo.design;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/*import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;*/
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle dtoggle;
    private String wifi_mac;
    private final int PERMISSION_ALL = 1;
    private static final String url_data = "http://malieop.x10host.com/teste.php";
    private static boolean hasLoaded = false;
    private NavigationView navigation;
    private static Local local_inicial;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        menus();

        permissoes();


// Verificar se as mudanças estão todas a funcionar, adicionar exemplo de sala na base de dados a ver se funcion, mudar a lista de destino para a lista de salas e a lista dos favoritos também.









        if (!DBmanager.databaseExists()) {
            DBmanager.initDatabase();
            Log.d("DB", "Cheguei aqui ");
        }
        if(!com.example.diogo.design.Handler.isInicia()){
        DBmanager.getDBManager().loadPontos(url_data,this);
        com.example.diogo.design.Handler.setInicia(true);
        }

        //DBmanager.getDBManager().inserePontos(this);
        imgFundo();
        // obtém mac do wifi




        // teste do local


        /*Picasso.get().load("https://i.imgur.com/DM29g0M.jpg").into(new Target() {
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
        });*/

        //drawer.setBackgroundResource(R.drawable.foto_corredor);

        // Botões
        final FloatingActionButton favoritos = findViewById(R.id.favoritos);
        favoritos.setOnClickListener(this::onClick);

        final FloatingActionButton destino = findViewById(R.id.direcoes);
        destino.setOnClickListener(this::onClick);
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

   public static void completeLoad() {
       hasLoaded = true; }

   public static boolean isLoaded() { return hasLoaded; }


   //toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(dtoggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
 // este metodo é chamado automáticamente para verificar quais as permissões que foram garantidas
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_ALL: {
                for (int i : grantResults) {
                    Log.i("permissoes", permissions[i]);
                    Log.i("resultado", String.valueOf(grantResults[i]));
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.

                    } else {

                        String[] permissao = {permissions[i]};
                        Toast.makeText(this,permissions[i],Toast.LENGTH_LONG);
                        ActivityCompat.requestPermissions(this, permissao, PERMISSION_ALL);
                        // permission denied, boo! Disable the
                        // functionality that depends on this permission.
                    }
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }}

        // permissoes necessárias para a aplicação
        public void permissoes(){
            String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.INTERNET};
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);


        }

        // função para ir buscar o MAC address do wifi que o utilizador está ligado
        public String wifi(){
            WifiConnection wifiConnection = new WifiConnection();
            wifi_mac = wifiConnection.getId(this);
            return wifi_mac;
        }

        // função que define a imagem de fundo consoante o WIfi a que o aparelho está ligado
        public void imgFundo(){
            //Local l = DBmanager.getLocalizacao(wifi()); // daqui vem um local com o URL e a localização do ponto de referencia
            new Thread(()->
            {
                while(!isLoaded()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Local l = DBmanager.getLocalizacao(wifi());
                //Local l = DBmanager.getLocalizacao("111:111:111:111");
                setLocal_inicial(l);
                Log.i("local", local_inicial.getUrl());
                runOnUiThread(()->
                {
                    ImageView imageView = (ImageView) findViewById(R.id.local_img);
                    Log.e("paneleirada", l.getUrl());
                    try{
                    Picasso.get().load(l.getUrl()).into(imageView);// url de teste "https://i.imgur.com/DM29g0M.jpg"

                    //aqui defino tambem a localização que vai aparecer na textview
                    TextView localizacao = (TextView) findViewById(R.id.localização);
                    localizacao.setText(l.getLocalizacao());}
                    catch (IllegalArgumentException e){
                        Toast.makeText(getApplicationContext(),"Não reconhece o Wifi.",Toast.LENGTH_LONG);

                    }
                });

            }).start();


        }

    @Override
    public void onClick(View view) {
        int id  = view.getId();
        Intent intent;

        switch (id){
            case R.id.favoritos:
                intent = new Intent(getApplicationContext(), FavoritosActivity.class);
                startActivity(intent);
                break ;
            case R.id.direcoes:
                intent = new Intent(getApplicationContext(), DestinoActivity.class);
                startActivity(intent);
                break ;

        }

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

    public static void setLocal_inicial(Local local_inicial) {
        FullscreenActivity.local_inicial = local_inicial;
    }

    public static Local getLocal_inicial() {
        return local_inicial;
    }
}

