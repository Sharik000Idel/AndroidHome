package com.example.text2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BookActivity extends AppCompatActivity  {

    TextView tittle_name ;
    TextView auchor;
    TextView Data;
    ImageView tittle_image;
    Button butten_open_txt;

    String doc = "";
    String text;
    Document document;
    Runnable runeb;
    Thread thread2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        tittle_name = (TextView ) findViewById(R.id.title_name);
        auchor = (TextView) findViewById(R.id.auchor_name);
        Data = (TextView) findViewById(R.id.data_name);
        tittle_image = (ImageView) findViewById(R.id.book_image);
        butten_open_txt = (Button) findViewById(R.id.buttonOpened);


        Bundle extras = getIntent().getExtras();

        if (extras != null){
            String Url = extras.get("Url").toString();
            String title = extras.get("Tittle").toString();
            String Auchor = extras.get("Auchor").toString();
            String image = extras.get("Image").toString();
            String data = extras.get("Data").toString();
            text = extras.get("Text").toString();

            tittle_name.append(title);
            tittle_name.setTextSize(30);

            auchor.append(Auchor);
            auchor.setTextSize(30);

            Data.append(data);
            auchor.setTextSize(25);
            Picasso.get().load("https:"+image).into(tittle_image);
            tittle_image.setScaleType(ImageView.ScaleType.FIT_XY);

            butten_open_txt.setOnClickListener(v -> {


                Intent intent = new Intent( BookActivity.this  , ReaderActivity.class );
                intent.putExtra("Text" , text) ;
                startActivity(intent);
                //init();

            });
        }



    }




    private void  init (){
        Handler firshandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);

                Intent intent = new Intent( BookActivity.this  , ReaderActivity.class );
                intent.putExtra("Text" , text) ;
                startActivity(intent);
            }
        };

        runeb = new Runnable() {
            @Override
            public void run() {
                WedRead();
            }
        };
        thread2 = new Thread(runeb);
        thread2.start();

        firshandler.sendEmptyMessage(0);


    }

    private void WedRead (){
        try {

            document = Jsoup.connect(("https://openlibrary.org" + text)).get();
            try {
                thread2.sleep( 100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Element bok_pag = document.getElementById("BookReader");

            doc = bok_pag.toString();
            Log.d("Mylog" , "sddddddddddddasdddddddddd "+String.valueOf(bok_pag.toString()) + " sdsdas  " + String.valueOf(bok_pag.text())  + "    sdfdsf"  );
            Log.d("Mylog" , bok_pag.toString() );
            /* Element booktable = table.get(0);*//*
                            Log.d("Mylog" , booktable.toString());*/
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Mylog" , "Error3");
        }
    }

}