package com.example.diogo.design;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by diogo on 22-04-2018.
 */

public class SwipeAdapter extends PagerAdapter {
    private int[]imagens = {R.drawable.nature,R.drawable.indice,R.drawable.images};

    private Context ctx ;
    private LayoutInflater layoutinflater;

    public SwipeAdapter (Context ctx ){

        this.ctx = ctx ;

    }
    @Override
    public int getCount() {
        return imagens.length;
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

        imageview.setImageResource(imagens[position]);

        container.addView(v);


        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
