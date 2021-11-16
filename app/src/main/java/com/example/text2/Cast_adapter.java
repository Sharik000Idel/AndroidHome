package com.example.text2;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Cast_adapter extends BaseAdapter {

    ArrayList<Book> bookList;
    LayoutInflater inflater;

    public Cast_adapter( Context context, ArrayList<Book> bookList) {
        this.bookList = bookList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_icon , parent , false);
        TextView title = (TextView) convertView.findViewById(R.id.name);
        TextView auchor = (TextView) convertView.findViewById(R.id.avtor);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        title.setText( bookList.get(position).getTitle());
        auchor.setText( bookList.get(position).getAuthor());
        Picasso.get().load("https:" + bookList.get(position).getImage()).into(imageView);



        return convertView;
    }
}
