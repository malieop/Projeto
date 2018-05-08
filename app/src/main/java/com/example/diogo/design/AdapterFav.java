package com.example.diogo.design;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by diogo on 20-04-2018.
 */

public class AdapterFav extends RecyclerView.Adapter<AdapterFav.ViewHolder> {
    private List<ListaFav> listaFavoritos;
    private Context ctx;


    public AdapterFav(List<ListaFav> listaFavoritos, Context ctx) {
        this.listaFavoritos = listaFavoritos;
        this.ctx = ctx;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListaFav listaFavorito = listaFavoritos.get(position);
        Log.i("MERDA", listaFavorito.sitio);
        holder.testviewSitio.setText(listaFavorito.sitio);
    }

    @Override
    public int getItemCount() {
        return listaFavoritos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView testviewSitio;

        public ViewHolder(View itemView) {
            super(itemView);
            testviewSitio = (TextView) itemView.findViewById(R.id.nome_sitio1);
        }
    }

}
