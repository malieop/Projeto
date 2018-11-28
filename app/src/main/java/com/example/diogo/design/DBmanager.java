package com.example.diogo.design;

/**
 * Created by diogo on 19-04-2018.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by diogo on 23-02-2018.
 */

public final class DBmanager {
    // Variáveis estáticas
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME = "redes.db";
    //Tabela Pontos
    private static final String NOME_TABELA_PONTOS = "pontosReferencia";

    //Colunas da tabela PONTOS

    private static final String coluna_id = "id";
    private static final String coluna_tipos = "tipo";
    private static final String coluna_nome = "pontos";
    private static final String coluna_link_imagem = "imagens";
    private static final String coluna_id_aparelho = "Id_aparelho";
    private static final String coluna_andar = "andar";
    private static final String coluna_swipe_helper = "swipe";

    // Tabela Favoritos
    private static final String NOME_TABELA_FAVORITOS = "favoritos";
    // COlunas da tabela favoritos
    private static final String coluna_id_fav = "id";
    private static final String coluna_nome_fav = "sitios";
    private static final String coluna_fav_lugares = "lugares";
    //Tabela Escadas
    private static final String NOME_TABELA_ESCADAS = "escadas";
    // Colunas da tabela escadas
    private static final String coluna_escadas_id = "id";
    private static final String coluna_escadas_andar = "andar";
    private static final String coluna_escadas_sitio = "sitio"; // inicio ou fim
    private static final String coluna_link_escadas= "imagem";
    private static final String coluna_escadas_posicao = "posicao";


    private static SQLiteDatabase db;

    public DBmanager() {


        //		File test  = new File(Environment.getExternalStorageDirectory() + "/documents/");
        //		String[] test2 = test.list();

        try {
            if (db == null)
                db = SQLiteDatabase.openDatabase(
                        Environment.getExternalStorageDirectory() + "/GUMa/" + DB_NAME,
                        null,
                        0);
            //Log.i(MODULE,"Db openned");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class DBHolder {
        public static final DBmanager INSTANCE = new DBmanager();
    }

    public static DBmanager getDBManager() {

        return DBHolder.INSTANCE;
    }

    public static void initDatabase(){
        try{
            File db_storage_file = new File(Environment.getExternalStorageDirectory(), "/GUMa");
            if(!db_storage_file.exists())
                if(! db_storage_file.mkdir()){
                    //Log.e(MODULE,"error creating /touchCloud directory");
                    return;
                }
            db_storage_file = new File(db_storage_file, DB_NAME);

            if(! db_storage_file.exists()){
                db_storage_file.createNewFile();
                Log.i("merda", "apass");
                createDatabase(db_storage_file.getAbsolutePath());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static boolean databaseExists()	{

        File storage_file = new File(Environment.getExternalStorageDirectory(), "/GUMa/"+DB_NAME);

        return storage_file.exists();
    }
    public static void createDatabase(String path) {
        db = SQLiteDatabase.openDatabase(path,
                null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
        String criaDB =
                "CREATE TABLE `" + NOME_TABELA_PONTOS + "` (" +
                        "  " + coluna_id + " INTEGER PRIMARY KEY," +
                        "  " + coluna_tipos+ " TEXT,"+
                        "  " + coluna_nome + " TEXT ," +
                        "  " + coluna_link_imagem+" TEXT,"+
                        "  " + coluna_id_aparelho + " INTENGER,"+
                        "  " + coluna_andar + " INTENGER," +
                        "  " + coluna_swipe_helper+ " TEXT," +
                        "   " + coluna_fav_lugares+ " INTEGER)";


        String criaDB1 = "CREATE TABLE " + NOME_TABELA_FAVORITOS + " (" +
                "   " + coluna_id_fav + " INTEGER PRIMARY KEY," +
                "   " + coluna_tipos + " TEXT,"+
                "   " + coluna_nome_fav + " TEXT," +
                "   " + coluna_link_imagem+" TEXT," +
                "   " + coluna_fav_lugares+ " INTEGER)";
        String criaDB2 = "CREATE TABLE " + NOME_TABELA_ESCADAS + " (" +
                "   " + coluna_escadas_id+ " INTEGER PRIMARY KEY," +
                "   " + coluna_escadas_sitio + " TEXT," +
                "   " + coluna_link_escadas + " TEXT,"+
                "   " + coluna_escadas_andar+" INTEGER,"+
                "   " +coluna_escadas_posicao +" TEXT)";
        String criaDB3 = "CREATE TABLE salas (" +
                "   salas_id INTEGER PRIMARY KEY," +
                "   salas_nome TEXT," +
                "   salas_link  TEXT," +
                "   salas_andar INTEGER)";

        //Log.i("hbbjh",criaDB);
        db.execSQL(criaDB);
        db.execSQL(criaDB1);
        db.execSQL(criaDB2);
        db.execSQL(criaDB3);


    }



    //INSERES


    public static synchronized boolean insereFavoritosTabela(Context ctx, int id, String tipo, String nome,String url, int lugares ){
        boolean res = false;
        SQLiteStatement stm = null;
        Log.i("Teste INSERT", "bosta");
        db.beginTransaction();
        try {
            // Create the insert statement
            String sql =
                    "insert into " +
                            "	" + NOME_TABELA_FAVORITOS
                            + " ( "+coluna_id_fav+", "
                            + coluna_tipos+ ","
                            +coluna_nome_fav+", "
                            +coluna_link_imagem+"," +
                            ""+coluna_fav_lugares+")" +
                            "Select '"+id+"','"+tipo+"','"+nome+"','"+url+"','"+lugares+"'"+
                            "WHERE NOT EXISTS(SELECT * FROM "+NOME_TABELA_FAVORITOS+" WHERE "+coluna_id_fav+" = "+id+" AND "+coluna_nome_fav+" = '"+nome+"' AND "+coluna_link_imagem+" = '"+url+"' AND "+coluna_fav_lugares+" = '"+lugares+"')";
            Log.i("QUERY INSERT", sql);
            stm = db.compileStatement(sql);

            Log.i("STM", String.valueOf(stm));

            if (stm.executeInsert() <= 0){
                Log.d("Insert", "Failed insertion of event into database");
            }


            // Signal success and update result value
            db.setTransactionSuccessful();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally	{
            stm.close();
            db.endTransaction();

            //Log.d(MODULE, "new event Data inserted");
        }

        return res;
    }
    public static synchronized boolean inserePonto(Local local){
        boolean res = false;
        SQLiteStatement stm = null;
        Log.i("Teste INSERT", "bosta");
        //db.beginTransaction();
        try {

            String sql = "insert into " +
                    "	" + NOME_TABELA_PONTOS
                    + " ( "+coluna_id+", "
                    +coluna_tipos+","
                    +coluna_nome+","
                    +coluna_link_imagem+","
                    +coluna_id_aparelho+","
                    +coluna_andar+","
                    +coluna_swipe_helper+","
                    +coluna_fav_lugares+")"+
                    "Select '"+local.getId()+"','"+local.getTipo()+"','"+local.getLocalizacao()+"','"+local.getUrl()+"','"+local.getIdaparelho()+"','"+local.getAndar()+"','"+local.getSwipe_helper()+"','"+local.getLugar()+"'"+
                    "WHERE NOT EXISTS(SELECT * FROM "+NOME_TABELA_PONTOS+" WHERE "+coluna_id+" = "+local.getId()+" AND "+coluna_nome+" = '"+local.getLocalizacao()+"' AND "+coluna_link_imagem+" = '"+local.getUrl()+"' AND "+coluna_id_aparelho+" = '"+local.getIdaparelho()+"' AND "+coluna_andar+" = '"+local.getAndar()+"' AND "+coluna_swipe_helper+" = '"+local.getSwipe_helper()+"' AND "+coluna_fav_lugares+" = '"+local.getLugar()+"')";




            Log.i("QUERY INSERT", sql);
            stm = db.compileStatement(sql);

            Log.i("STM", String.valueOf(stm));

            if (stm.executeInsert() <= 0){
                Log.d("Insert", "Failed insertion of event into database");
            }


            // Signal success and update result value
            //db.setTransactionSuccessful();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally	{
            //stm.close();
            //db.endTransaction();
            //db.close();
            //Log.d(MODULE, "new event Data inserted");
        }

        return res;
    }
    public static synchronized boolean insereEscada(Local local){
        boolean res = false;
        SQLiteStatement stm = null;
        Log.i("Teste INSERT", "bosta");
        //db.beginTransaction();
        try {


            String sql = "insert into " +
                    "	" + NOME_TABELA_ESCADAS
                    + " ( "+coluna_escadas_id+", "+
                    ""+coluna_escadas_sitio+","+
                    ""+ coluna_link_escadas +","+
                    ""+coluna_escadas_andar+","+
                    ""+coluna_escadas_posicao+")"+
                    "Select '"+local.getId()+"','"+local.getLocalizacao()+"','"+local.getUrl()+"','"+local.getAndar()+"','"+local.getTipo()+"'"+
                    "WHERE NOT EXISTS(SELECT * FROM "+NOME_TABELA_ESCADAS+" WHERE "+coluna_escadas_id+" = "+local.getId()+" AND "+coluna_escadas_sitio+" = '"+local.getLocalizacao()+"' AND "+coluna_link_escadas+" = '"+local.getUrl()+"' AND "+coluna_escadas_andar+" = '"+local.getAndar()+"' AND "+coluna_escadas_posicao+"= '"+local.getTipo()+"')";




            Log.i("QUERY INSERT", sql);
            stm = db.compileStatement(sql);

            Log.i("STM", String.valueOf(stm));

            if (stm.executeInsert() <= 0){
                Log.d("Insert", "Failed insertion of event into database");
            }


            // Signal success and update result value
            //db.setTransactionSuccessful();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally	{
            //stm.close();
            //db.endTransaction();
            //db.close();
            //Log.d(MODULE, "new event Data inserted");
        }

        return res;
    }
    public static synchronized boolean insereSalas(Local l){
        boolean res = false;
        SQLiteStatement stm = null;
        Log.i("Teste INSERT", "bosta");
        db.beginTransaction();
        try {
            // Create the insert statement
            String sql =
                    "insert into salas (salas_id, salas_nome, salas_link, salas_andar)" +
                            "Select '"+l.getId()+"','"+l.getLocalizacao()+"','"+l.getUrl()+"','"+l.getAndar()+"'"+
                            "WHERE NOT EXISTS(SELECT * FROM salas WHERE salas_id = "+l.getId()+" AND salas_nome = '"+l.getLocalizacao()+"' AND salas_link = '"+l.getUrl()+"' AND salas_andar = "+l.getAndar()+")";
            Log.i("QUERY INSERT", sql);
            stm = db.compileStatement(sql);

            Log.i("STM", String.valueOf(stm));

            if (stm.executeInsert() <= 0){
                Log.d("Insert", "Failed insertion of event into database");
            }


            // Signal success and update result value
            db.setTransactionSuccessful();
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally	{
            stm.close();
            db.endTransaction();

            //Log.d(MODULE, "new event Data inserted");
        }

        return res;
    }


    // GETERS

    public static synchronized Local getLocalizacao(String mac){
        Cursor c;
        db.beginTransaction();
        int id = 0;
        String tipo = "";
        String nome = "";
        String urlponto = "";
        String MAC = "";
        int andar = 0;
        String helper = "";
        String[] colunas = new String[] {"*"};
        String where = new String (""+coluna_id_aparelho+" =?");
        String[] macAddr = {mac};

        c = db.query("pontosReferencia",colunas,where,macAddr,null,null,null);
        ;
        while(c.moveToNext()){
             id = c.getInt(0);
             tipo = c.getString(1);
             nome = c.getString(2);
             urlponto = c.getString(3);
             MAC =c.getString(4);
             andar = c.getInt(5);
             helper = c.getString(6);

        }
        Local l = new Local( tipo,urlponto , nome,MAC,id,andar, helper);
        Log.d("local", nome);
        Log.d("url", urlponto);
        Log.d("MAC",MAC);

        db.endTransaction();

        return l;
    }

    public void loadPontos(String url, Context ctx){

        new Thread(()->
        {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,
                    url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONArray arraypontos = new JSONArray(response);

                        for (int i = 0; i < arraypontos.length(); i++ ){
                            JSONObject ponto = arraypontos.getJSONObject(i);
                            int id = ponto.getInt("idPontosDeReferencia");
                            String tipo = ponto.getString("tipos");
                            String nome = ponto.getString("nome");
                            String urlponto = ponto.getString("url");
                            String MAC = ponto.getString("mac");
                            int andar = ponto.getInt("andar");
                            String helper = ponto.getString("swipe_helper");
                            int lugar = ponto.getInt("lugares");

                            Local pontoReferencia = new Local(urlponto,nome,MAC,id,andar,tipo,helper,lugar);
                            if(tipo.equals("escadas")) {
                                insereEscada(pontoReferencia);
                            }
                            /*else if(tipo.equals("salas")){
                                // fazer tabela para as salas
                            }*/
                            else{
                                inserePonto(pontoReferencia);
                            }

                        }

                        FullscreenActivity.completeLoad();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("JSONerror", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(ctx);
            requestQueue.add(stringRequest);
        }).start();

    }
    public static ArrayList<Local> getPontos(){
        Cursor c;
        ArrayList<Local> pontos = new ArrayList<Local>();
        String[] Colunas = new String[] {"*"};


        c= db.query("pontosReferencia", Colunas,null,null,null,null,null);

        while(c.moveToNext()){
            int id = c.getInt(0);
            //String tipo = c.getString(1); não é necessário neste caso
            String nome = c.getString(2);
            String urlponto = c.getString(3);
            String MAC =c.getString(4);
            int andar = c.getInt(5);
            String helper = c.getString(6);
            int lugar = c.getInt(7);

            Local l = new Local( urlponto , nome,MAC,id,andar, helper,lugar);
            pontos.add(l);
        }

        return pontos;
    }
    public static ArrayList<Local> getPontosFav(){
        Cursor c;
        ArrayList<Local> pontos = new ArrayList<Local>();
        String[] Colunas = new String[] {"*"};


        c= db.query(NOME_TABELA_FAVORITOS, Colunas,null,null,null,null,null);

        while(c.moveToNext()){
            int id = c.getInt(0);
            String localizacao = c.getString(2);
            String url = c.getString(3);
            int lugar = c.getInt(4);

            Local l = new Local(id,url, localizacao,lugar);
            pontos.add(l);
        }
        return pontos;
    }
    public static Local getEscada(Local l){
        Cursor c;
        Local aux ;
        Local escada;
        String[] Colunas = new String[] {"*"};
        String where = new String (""+coluna_escadas_andar+" =? AND "+coluna_escadas_posicao+"= ?");
        String []whereArgs = new String[]{String.valueOf(l.getAndar()), l.getSwipe_helper()};

        c= db.query(NOME_TABELA_ESCADAS, Colunas,null,null,null,null,null);
            c.moveToNext();
            int id = c.getInt(0);
            String localizacao = c.getString(1);
            String url = c.getString(2);
            int andar = c.getInt(3);
            String posicao = c.getString(4);

            escada = new Local(id,url, localizacao, andar, posicao);




        return escada;
    }



}
