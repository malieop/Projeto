package com.example.diogo.design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by diogo on 12-05-2018.
 */

public class AdapterPontos extends RecyclerView.Adapter<AdapterPontos.ViewHolder> {
    private List<Local> listaPontos;
    private Context ctx;
    private static Local destino;


    public AdapterPontos(List<Local> listaPontos, Context ctx) {
        this.listaPontos = listaPontos;
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
            Local listaPonto = listaPontos.get(position);
            Log.i("MERDA", listaPonto.getLocalizacao());
            holder.testviewSitio.setText(listaPonto.getLocalizacao());
        }

        @Override
        public int getItemCount() {
            return listaPontos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public TextView testviewSitio;


            public ViewHolder(View itemView) {
                super(itemView);
                testviewSitio = (TextView) itemView.findViewById(R.id.nome_sitio1);
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
}


