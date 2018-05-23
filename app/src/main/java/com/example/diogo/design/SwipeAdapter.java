package com.example.diogo.design;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by diogo on 22-04-2018.
 */

public class SwipeAdapter extends PagerAdapter {
    private ArrayList<String>imagens ;

    private Context ctx ;
    private LayoutInflater layoutinflater;

    public SwipeAdapter (Context ctx , ArrayList<String>imagens){

        this.ctx = ctx ;
        this.imagens = imagens;
    }
    @Override
    public int getCount() {
        return imagens.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutinflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v  = layoutinflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageview = (ImageView) v.findViewById(R.id.imageview);

        Log.d("URL", imagens.get(position));

        try {
            Picasso.get().load(imagens.get(position)).resize(Screen.getScreenWidth(),Screen.getScreenHeight()).into(imageview);
        }
        catch (IllegalArgumentException e){
            Toast.makeText(ctx,"Erro, a rede a que esta ligado não foi reconhecida.",Toast.LENGTH_LONG).show();
            Intent i=new Intent(ctx, FullscreenActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(ctx,i,null);
        }
        container.addView(v);


        return v;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
