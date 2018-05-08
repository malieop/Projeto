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
    private static final String coluna_nome = "pontos";
    private static final String coluna_link_imagem = "imagens";
    private static final String coluna_id_aparelho = "Id_aparelho";

    // Tabela Favoritos
    private static final String NOME_TABELA_FAVORITOS = "favoritos";
    // COlunas da tabela favoritos
    private static final String coluna_id_fav = "id";
    private static final String coluna_nome_fav = "sitios";


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
                        "  " + coluna_nome + " TEXT ," +
                        "  " + coluna_link_imagem+" TEXT,"+
                        "  " + coluna_id_aparelho + " INTENGER)";


        String criaDB1 = "CREATE TABLE " + NOME_TABELA_FAVORITOS + " (" +
                "   " + coluna_id_fav + " INTEGER PRIMARY KEY," +
                "   " + coluna_nome_fav + " TEXT )";
        //Log.i("hbbjh",criaDB);
        db.execSQL(criaDB);
        db.execSQL(criaDB1);
    }
    public synchronized boolean insereFavoritos(Context ctx){
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
                            +coluna_nome_fav+")" +
                            "values ( NULL,'Sala de estudo 3')";
            Log.i("QUERY INSERT", sql);
            stm = db.compileStatement(sql);

            // Iterate over the array, extracting values as necessary

               /* stm.bindNull(1);
                stm.bindString(2, descricao);
                stm.bindString(3, redeInfo.getId(ctx));
                stm.bindLong(4,redeInfo.getFreq(ctx));
                stm.bindString(5,redeInfo.getDateTime());*/
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

    public synchronized boolean insereFavoritosTabela(Context ctx, int id, String nome){
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
                            +coluna_nome_fav+")" +
                            "values ("+id+",'"+nome+"')";
            Log.i("QUERY INSERT", sql);
            stm = db.compileStatement(sql);

            // Iterate over the array, extracting values as necessary

               /* stm.bindNull(1);
                stm.bindString(2, descricao);
                stm.bindString(3, redeInfo.getId(ctx));
                stm.bindLong(4,redeInfo.getFreq(ctx));
                stm.bindString(5,redeInfo.getDateTime());*/
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
    public static synchronized boolean inserePontos(Context ctx){
        boolean res = false;
        SQLiteStatement stm = null;
        Log.i("Teste INSERT", "bosta");
        db.beginTransaction();
        try {
            // Create the insert statement
            String sql =
                    "insert into " +
                            "	" + NOME_TABELA_PONTOS
                            + " ( "+coluna_id+", "
                            +coluna_nome+","
                            +coluna_link_imagem+","
                            +coluna_id_aparelho+")" +
                            "values ( NULL,'Sala de estudo 0' ,'aaaaaaa','12122121') +" +
                            "values ( NULL,'Sala de estudo 2' ,'cccccc','12122121') +" +
                            "values ( NULL,'Biblioteca' ,'aaaaaaa','12122121') +" +
                            "values ( NULL,'Sala de estudo 3' ,'aaaaaaa','12122121') +" +
                            "values ( NULL,'Sala de estudo de Engenharia' ,'aaaaaaa','12122121') +" +
                            "values ( NULL,'Departamento de Engenharia' ,'aaaaaaa','12122121') +" +
                            "values(NULL, 'Sala de estudo 1', 'bbbbbb', '333333')";
            Log.i("QUERY INSERT", sql);
            stm = db.compileStatement(sql);

            // Iterate over the array, extracting values as necessary

               /* stm.bindNull(1);
                stm.bindString(2, descricao);
                stm.bindString(3, redeInfo.getId(ctx));
                stm.bindLong(4,redeInfo.getFreq(ctx));
                stm.bindString(5,redeInfo.getDateTime());*/
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
            //stm.close();
            db.endTransaction();

            //Log.d(MODULE, "new event Data inserted");
        }

        return res;
    }

    public static synchronized Local getLocalizacao(String mac){
        Cursor c;
        db.beginTransaction();
        String mac_addr= "";
        String url= "";
        String nome= "";
        String[] Colunas = new String[] {""+coluna_nome+"",coluna_link_imagem+""};
        String where = new String (""+coluna_id_aparelho+"=="+mac);

        c = db.query("pontosReferencia",Colunas,where,null,null,null,null);
        ;
        while(c.moveToNext()){
            nome = c.getString(0);
            url = c.getString(1);

        }
        Local l = new Local(url , nome);
        Log.d("local", nome);
        Log.d("url", url);
        db.close();
        return l;
    }


}
