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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;
import static android.util.Log.println;

/**
 * Created by diogo on 12-05-2018.
 */

public class AdapterPontos extends RecyclerView.Adapter<AdapterPontos.ViewHolder> implements Filterable {
    private static ArrayList<Local> listaPontos;
    private Context ctx;
    private static Local destino;
    private MyFilter filter;


    public AdapterPontos(ArrayList<Local> listaPontos, Context ctx) {
        this.listaPontos = listaPontos;
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
            Local listaPonto = listaPontos.get(position);
            Log.i("MERDA", listaPonto.getLocalizacao());
            holder.testviewSitio.setText(listaPonto.getLocalizacao());
        }

        @Override
        public int getItemCount() {
            return listaPontos.size();
        }

    @Override
    public Filter getFilter() {
        if (filter == null) {
            filter = new MyFilter(this, listaPontos);
        }
        return filter;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public TextView testviewSitio;


            public ViewHolder(View itemView) {
                super(itemView);
                testviewSitio = (TextView) itemView.findViewById(R.id.nome_sitio);
                itemView.setOnClickListener(this);
            }
            @Override
            public void onClick(View view) {

                Local l = listaPontos.get(getLayoutPosition());
                destino = l;
                Intent i=new Intent(ctx, SwipeActivity.class);
                startActivity(ctx,i,null);
            }
        }

    public static Local getDestino() {
        return destino;
    }

    public static void setDestino(Local destino) {
        AdapterPontos.destino = destino;
    }

    private static class MyFilter extends Filter{
        private final AdapterPontos adapter;
        private final List<Local> originalList;
        private final List<Local> filteredList;

        private MyFilter(AdapterPontos adapter, List<Local> originalList) {
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
            listaPontos.clear();
            listaPontos.addAll(filteredList);
            adapter.notifyDataSetChanged();
        }

    }

}


