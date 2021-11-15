package com.example.text2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Cast_adapter extends BaseAdapter {

    ArrayList<Book> bookList;
    LayoutInflater inflater;

    public Cast_adapter(ArrayList<Book> bookList, Context context) {
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
        TextView textView = (TextView) convertView.findViewById(R.id.name);
        textView.setText( bookList.get(position).getTitle() );

        return convertView;
    }
}
