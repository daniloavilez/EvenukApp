package com.evenuk.evenukapp;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.evenuk.business.Feed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danilo on 18/11/2016.
 */

public class AdapterFeedListView extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Feed> itens;

    public AdapterFeedListView(Context context, ArrayList<Feed> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itens.size();
    }

    @Override
    public Object getItem(int i) {
        return itens.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Feed item = itens.get(i);

        view = mInflater.inflate(R.layout.feed_listview, viewGroup, false);

        //((RatingBar) view.findViewById(R.id.ratingBar)).setRating(item.getRating());
        //((ImageView) view.findViewById(R.id.imageViewEvent)).setImageURI(item.getEventImage());
        TextView textViewEventTitle = (TextView) view.findViewById(R.id.textViewEventTitle);
        textViewEventTitle.setText(item.getTituloEvento());
        ((TextView) view.findViewById(R.id.textViewEventDate)).setText(new SimpleDateFormat("dd/MM/yyyy").format(item.getDataEvento()));

        if (item.getComentarios().size() > 1)
            ((TextView) view.findViewById(R.id.textViewComments)).setText(item.getComentarios().size() + " comentários");
        else
            ((TextView) view.findViewById(R.id.textViewComments)).setText(item.getComentarios().size() + " comentário");

        return view;
    }
}
