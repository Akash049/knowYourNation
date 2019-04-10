package com.project.kyn.knowyournation.adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.project.kyn.knowyournation.R;
import com.project.kyn.knowyournation.models.Country;
import com.project.kyn.knowyournation.util.SvgDecoder;
import com.project.kyn.knowyournation.util.SvgDrawableTranscoder;
import com.project.kyn.knowyournation.util.SvgSoftwareLayerSetter;

import java.io.InputStream;
import java.util.List;

/**
 * Created by akashchandra on 11/23/17.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder>{

    private List<Country> countryList;
    private Context context;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView flag;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.country_name);
            flag = (ImageView)view.findViewById(R.id.flag);

        }
    }

    public CountryAdapter(List<Country> countries , Context context) {
        this.countryList = countries;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.name.setText(country.getName());

        if(country.getFlag()!=null){
            try{

                requestBuilder = Glide.with(context)
                        .using(Glide.buildStreamModelLoader(Uri.class, context), InputStream.class)
                        .from(Uri.class)
                        .as(SVG.class)
                        .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                        .sourceEncoder(new StreamEncoder())
                        .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                        .decoder(new SvgDecoder())
                        .placeholder(R.drawable.ic_flag_black_24dp)
                        .error(R.drawable.ic_flag_black_24dp)
                        .animate(android.R.anim.fade_in)
                        .listener(new SvgSoftwareLayerSetter<Uri>());

                Uri uri = Uri.parse(country.getFlag());

                requestBuilder
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        // SVG cannot be serialized so it's not worth to cache it
                        .load(uri)
                        .into(holder.flag);
            }catch (Exception e){

            }
        }

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

}
