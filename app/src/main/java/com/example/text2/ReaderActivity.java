package com.example.text2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class ReaderActivity extends AppCompatActivity {


    Document document;
    String doc = "";
    String text;
    Runnable runeb;
    Thread thread2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        SubsamplingScaleImageView imageView = findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            text = extras.get("Text").toString();
            //init();


            /*Bitmap bitmap = ((BitmapDrawable)((LayerDrawable)(Target)imageView.getDrawable()).getDrawable(0)).getBitmap();
            imageView.setImage(ImageSource.bitmap(bitmap));*/



        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Mylog" , "!!!!!!!!!!!!!!!!!!!!!!         "+doc);
    }

    private void  init (){
        runeb = new Runnable() {
            @Override
            public void run() {
                WedRead(text);
            }
        };
        thread2 = new Thread(runeb);
        thread2.start();


    }

    private void WedRead (String text ){
                    try {
                        document = Jsoup.connect(("https://openlibrary.org" + text)).get();
                        Elements table = document.children();
                        Element bok_pag = table.get(0);
                        doc = bok_pag.toString();
                        Log.d("Mylog" , "sddddddddddddasdddddddddd "+String.valueOf(bok_pag.toString()) + " sdsdas  " + String.valueOf(bok_pag.text())  + "    sdfdsf"  );
                        /* Element booktable = table.get(0);*//*
                            Log.d("Mylog" , booktable.toString());*/
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("Mylog" , "Error3");
                    }
    }
}