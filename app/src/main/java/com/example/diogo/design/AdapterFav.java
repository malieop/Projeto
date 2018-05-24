package com.example.diogo.design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by diogo on 20-04-2018.
 */

public class AdapterFav extends RecyclerView.Adapter<AdapterFav.ViewHolder> {
    private static List<Local> listaFavoritos;
    private static Context ctx;


    public AdapterFav(List<Local> listaFavoritos, Context ctx) {
        this.listaFavoritos = listaFavoritos;
        this.ctx = ctx;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_normal,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Local listaFavorito = listaFavoritos.get(position);
        Log.i("MERDA", listaFavorito.getLocalizacao());
        holder.testviewSitio.setText(listaFavorito.getLocalizacao());
    }

    @Override
    public int getItemCount() {
        return listaFavoritos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView testviewSitio;



        public ViewHolder(View itemView) {
            super(itemView);
            testviewSitio = (TextView) itemView.findViewById(R.id.nome_sitio);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
            Log.d("Click", "cliquei "+ getLayoutPosition());
            Local l = listaFavoritos.get(getLayoutPosition());
            Log.d("Click", l.getLocalizacao());
            Log.d("Click", l.getUrl());
            Log.d("Click", Integer.toString(l.getId()));
            DBmanager.insereFavoritosTabela(ctx,l.getId(),l.getLocalizacao(),l.getUrl());
            Toast.makeText(ctx,"Inseriu um novo local nos Favoritos.",Toast.LENGTH_LONG);
            Intent i=new Intent(ctx, FullscreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(ctx,i,null);
        }
    }

}
