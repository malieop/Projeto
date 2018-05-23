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

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by diogo on 17-05-2018.
 */

public class AdapterFavoritos extends RecyclerView.Adapter<AdapterFavoritos.ViewHolder>{
    private static List<Local> listaFavoritos;
    private static Context ctx;


    public AdapterFavoritos(List<Local> listaFavoritos, Context ctx) {
        this.listaFavoritos = listaFavoritos;
        this.ctx = ctx;
    }



    @Override
    public AdapterFavoritos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista,parent,false);
        return new AdapterFavoritos.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Local listaFavorito = listaFavoritos.get(position);
        Log.i("MERDA", listaFavorito.getLocalizacao());
        holder.testviewSitio.setText(listaFavorito.getLocalizacao());
        ImageView i = holder.imageviewsitio;
        Log.d("URL",listaFavorito.getUrl());
        Picasso.get().load(listaFavorito.getUrl()).resize(200,200).into(i);
    }

    @Override
    public int getItemCount() {
        return listaFavoritos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView testviewSitio;
        public ImageView imageviewsitio;


        public ViewHolder(View itemView) {
            super(itemView);
            testviewSitio = (TextView) itemView.findViewById(R.id.nome_sitio1);
            imageviewsitio =(ImageView) itemView.findViewById(R.id.imagem_fav);
            itemView.setOnClickListener(this);

        }
        @Override
        public void onClick(View view) {
           /* Log.d("Click", "cliquei "+ getLayoutPosition());
            Local l = listaFavoritos.get(getLayoutPosition());
            Log.d("Click", l.getLocalizacao());
            Log.d("Click", l.getUrl());
            Log.d("Click", Integer.toString(l.getId()));
            DBmanager.insereFavoritosTabela(ctx,l.getId(),l.getLocalizacao(),l.getUrl());
            Toast.makeText(ctx,"Inseriu um novo local nos Favoritos.",Toast.LENGTH_LONG);
            Intent i=new Intent(ctx, FullscreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(ctx,i,null);*/
        }
    }

}
