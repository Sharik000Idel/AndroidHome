package com.example.text2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class ReaderActivity extends AppCompatActivity {


    TextView textView;
    Document document;
    String doc = "";
    String text;
    Runnable runeb;
    Thread thread2;
    String imageurl = " Я пуст";

    ImageView imageView_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        //SubsamplingScaleImageView imageView = findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();
        imageView_path = findViewById(R.id.page_iv);

        if (extras != null) {

            text = extras.get("Text").toString();
            textView = findViewById(R.id.test_checkbox_android_button_tint);
            init();



            /*Bitmap bitmap = ((BitmapDrawable)((LayerDrawable)(Target)imageView.getDrawable()).getDrawable(0)).getBitmap();
            imageView.setImage(ImageSource.bitmap(bitmap));*/

            Log.d("Mylog" ,"TEeeexzt   "+ doc) ;
            firshandler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                    textView.append("  im finished   ");
                    textView.append(doc);
                    //Log.d("Mylog" ,"TEeeexzt222   "+ doc) ;
                    textView.setTextSize(20);
                    textView.setText(imageurl);
                    Picasso.get().load("https://ia800306.us.archive.org/BookReader/BookReaderImages.php?zip=/18/items/" +
                            "christmascarol00dick/christmascarol00dick_jp2.zip&file=christmascarol00dick_jp2/" +
                            "christmascarol00dick_0012.jp2&id=christmascarol00dick").into(imageView_path);
                }
            };

        }

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
    Handler firshandler;
    private void WedRead (String text ){
                    try {
                        document = Jsoup.connect(("https://openlibrary.org" + text)).get();
                        Elements table = document.getAllElements();
                        for (Element a : table  ){
                             doc = doc + a.toString();
                        }
                        Log.d("Mylog" , "SIze!!!  "+table.size()) ;
                        Log.d("Mylog" , "Boolea @@@ "  + doc.indexOf("BRpageimage") )   ;

                        if ( doc.contains("us.archive.org/BookReader/BookReader") ){
                            Log.d("Mylog" , " "+ doc.indexOf("us.archive.org/BookReader/BookReader")+1);
                            imageurl = doc.substring(doc.indexOf("us.archive.org/BookReader/BookReader")-11).substring(0 ,
                                    (doc.substring(doc.indexOf("us.archive.org/BookReader/BookReader")).indexOf("'")+11))  ;
                            Log.d("Mylog" , " "+ doc.indexOf("us.archive.org/BookReader/BookReader")+1);


                            Log.d("Mylog" , "fd  "+imageurl);



                            //https://ia800306.us.archive.org/BookReader/BookReaderImages.php?zip=/18/items/christmascarol00dick/christmascarol00dick_jp2.zip&file=christmascarol00dick_jp2/christmascarol00dick_0001.jp2&id=christmascarol00dick

                        }

                        //imageurl = doc.substring(doc.indexOf("BRpageimage")+10 , doc.indexOf("BRpageimage")+70 );
                        //Log.d("Mylog" , "sddddddddddddasdddddddddd "  + table.size() + "    sdfdsf  "   + String.valueOf(bok_pag.toString()) + " sdsdas  "    );
                        /* Element booktable = table.get(0);*//*
                            Log.d("Mylog" , booktable.toString());*/
                        firshandler.sendEmptyMessage(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d("Mylog" , "Error3");
                    }
    }
}