package com.example.diogo.design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by diogo on 20-04-2018.
 */

public class AdapterFav extends RecyclerView.Adapter<AdapterFav.ViewHolder> implements Filterable {
    private static ArrayList<Local> listaFavoritos;
    private static Context ctx;
    private MyFilter filter;


    public AdapterFav(ArrayList<Local> listaFavoritos, Context ctx) {
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

    @Override
    public Filter getFilter() {

        if (filter == null) {
            filter = new AdapterFav.MyFilter(this, listaFavoritos);
        }
        return filter;
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
            DBmanager.insereFavoritosTabela(ctx,l.getId(),l.getTipo(),l.getLocalizacao(),l.getUrl(),l.getLugar());
            Toast.makeText(ctx,"Inseriu um novo local nos Favoritos.",Toast.LENGTH_LONG);
            Intent i=new Intent(ctx, FullscreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(ctx,i,null);
        }
    }
    private static class MyFilter extends Filter {
        private final AdapterFav adapter;
        private final List<Local> originalList;
        private final List<Local> filteredList;

        private MyFilter(AdapterFav adapter, ArrayList<Local> originalList) {
            super();
            this.adapter = adapter;
            this.originalList = new LinkedList<>(originalList);
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence){
            filteredList.clear();
            final FilterResults results = new FilterResults();

            if (charSequence.length() == 0) {
                filteredList.addAll(originalList);
            } else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Local item : originalList) {
                    if (item.getLocalizacao().toLowerCase().contains(filterPattern) ){
                        filteredList.add(item);
                    }
                }
            }

            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listaFavoritos.clear();
            listaFavoritos.addAll(filteredList);
            adapter.notifyDataSetChanged();
        }

    }

}
