package com.example.text2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class Cast_adapter extends ArrayAdapter<Book> {

    ArrayList<Book> bookList;
    LayoutInflater inflater;

    public Cast_adapter( Context context, int resourses ,  ArrayList<Book> bookList) {
        super(context , resourses ,bookList);
        this.bookList = bookList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Book getItem(int position) {
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
        auchor.append(bookList.get(position).getAuthor());
        //auchor.setText( bookList.get(position).getAuthor());


        if (bookList.get(position).getImage().equals("//arc")) {
            bookList.get(position).setImage("//img.wallpapersafari.com/tablet/800/1280/96/18/xjUMlk.jpg");}

        Picasso.get().load("https:" + bookList.get(position).getImage()).into(imageView);

        View finalConvertView = convertView;
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( finalConvertView.getContext() , BookActivity.class);
                intent.putExtra( "Url"    , getItem(position).getUrl()  );
                intent.putExtra( "Tittle" , getItem(position).getTitle() );
                intent.putExtra( "Auchor" , getItem(position).getAuthor()  );
                intent.putExtra( "Image"  , getItem(position).getImage()  );
                intent.putExtra( "Data"   , getItem(position).getData_publishing() );
                intent.putExtra( "Text"   , getItem(position).getText_url()) ;
                finalConvertView.getContext().startActivity(intent);


            }
        });

        return convertView;
    }
}
