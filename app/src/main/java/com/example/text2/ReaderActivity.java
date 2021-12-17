package com.example.text2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
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


    //TextView textView;
    Document document;
    String doc = "";
    String text;
    Runnable runeb;
    Thread thread2;
    String imageurl = " Я пуст";
    Button next ;

    ImageView imageView_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        next = findViewById(R.id.next);

        //SubsamplingScaleImageView imageView = findViewById(R.id.imageView);
        Bundle extras = getIntent().getExtras();
        imageView_path = findViewById(R.id.page_iv);

        if (extras != null) {

            text = extras.get("Text").toString();
            //textView = findViewById(R.id.test_checkbox_android_button_tint);
            init();



            /*Bitmap bitmap = ((BitmapDrawable)((LayerDrawable)(Target)imageView.getDrawable()).getDrawable(0)).getBitmap();
            imageView.setImage(ImageSource.bitmap(bitmap));*/

            Log.d("Mylog" ,"TEeeexzt   "+ doc) ;

            final String[] text1 = new String[4];


            firshandler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);

                    String f_url = ".us.archive.org/BookReader";
                    //itemPath=/
                    String ia = imageurl.substring(2 , imageurl.indexOf(f_url) );
                    text1[0] = ia;
                    System.out.println("Ia" + ia);
                    String zip = imageurl.substring(imageurl.indexOf("&itemPath=/") + 11, imageurl.indexOf("/items/") );
                    text1[1] =zip;
                    System.out.println("zip" + zip);
                    String id = imageurl.substring(imageurl.indexOf("id=") +3, imageurl.indexOf("&itemPath"));
                    text1[2] =id;
                    System.out.println("ID = "+id);
                    String url_image = "  ";
                    String item = "0001";
                    text1[3] = item;
                    //textView.append("  im finished   ");
                    //textView.append(doc);
                    //Log.d("Mylog" ,"TEeeexzt222   "+ doc) ;
                    //textView.setTextSize(20);
                    //textView.setText(imageurl);
                    System.out.println(imageurl);
                    //"https://ia800306.us.archive.org/BookReader/BookReaderImages.php?zip=/18/items/" +
                    //                            "christmascarol00dick/christmascarol00dick_jp2.zip&file=christmascarol00dick_jp2/" +
                    //                            "christmascarol00dick_0012.jp2&id=christmascarol00dick"


                    Picasso_imagessss(ia , zip , id  );

                    System.out.println("https://"+ia+".us.archive.org/BookReader/BookReaderImages.php?zip=/"+ zip +"/items/" +
                            id+"/"+ id+"_jp2.zip&file="+ id+"_jp2/" +
                            id+"_0012.jp2&id="+ id);
                    Picasso.get().load("https://"+ia+".us.archive.org/BookReader/BookReaderImages.php?zip=/"+ zip +"/items/" +
                            id+"/"+ id+"_jp2.zip&file="+ id+"_jp2/" +
                            id+"_0001.jp2&id="+ id).into(imageView_path);
                }
            };

            final int[] i = {1};


            next.setOnClickListener(v -> {
                i[0] = i[0] + 1;
                String pages = "0000";
                pages = pages.substring(String.valueOf(i[0]).length()) + String.valueOf(i[0]) ;
                System.out.println("oooooooooo11111  "+pages);
                //pages = (pages+ String.valueOf(i[0])).substring( pages.length() - 3 );
                System.out.println("oooooooooo  "+pages);
                Picasso.get().load("https://"+text1[0]+".us.archive.org/BookReader/BookReaderImages.php?zip=/"+ text1[1] +"/items/" +
                        text1[2]+"/"+ text1[2]+"_jp2.zip&file="+ text1[2]+"_jp2/" +
                        text1[2]+"_"+ pages +".jp2&id="+ text1[2]).into(imageView_path);
                System.out.println("https://"+text1[0]+".us.archive.org/BookReader/BookReaderImages.php?zip=/"+ text1[1] +"/items/" +
                        text1[2]+"/"+ text1[2]+"_jp2.zip&file="+ text1[2]+"_jp2/" +
                        text1[2]+"_"+ pages +".jp2&id="+ text1[2]);

            });
        }

    }
    private void inetiel(){

        String f_url = ".us.archive.org/BookReader";
        //itemPath=/
        String ia = imageurl.substring(2 , imageurl.indexOf(f_url) );
        System.out.println("Ia" + ia);
        String zip = imageurl.substring(imageurl.indexOf("&itemPath=/") + 11, imageurl.indexOf("/items/") );
        System.out.println("zip" + zip);
        String id = imageurl.substring(imageurl.indexOf("id=") +3, imageurl.indexOf("&itemPath"));
        System.out.println("ID = "+id);
        String url_image = "  ";
        String item = "0001";
        //textView.append("  im finished   ");
        //textView.append(doc);
        //Log.d("Mylog" ,"TEeeexzt222   "+ doc) ;
        //textView.setTextSize(20);
       // textView.setText(imageurl);
        System.out.println(imageurl);
        //"https://ia800306.us.archive.org/BookReader/BookReaderImages.php?zip=/18/items/" +
        //                            "christmascarol00dick/christmascarol00dick_jp2.zip&file=christmascarol00dick_jp2/" +
        //                            "christmascarol00dick_0012.jp2&id=christmascarol00dick"


        Picasso_imagessss(ia , zip , id  );

        System.out.println("https://"+ia+".us.archive.org/BookReader/BookReaderImages.php?zip=/"+ zip +"/items/" +
                id+"/"+ id+"_jp2.zip&file="+ id+"_jp2/" +
                id+"_0012.jp2&id="+ id);
        Picasso.get().load("https://"+ia+".us.archive.org/BookReader/BookReaderImages.php?zip=/"+ zip +"/items/" +
                id+"/"+ id+"_jp2.zip&file="+ id+"_jp2/" +
                id+"_0001.jp2&id="+ id).into(imageView_path);
    }

    private void Picasso_imagessss(String ia ,String zip , String id  ){

        for (int a = 0; a< 50 ; a++){
            String pages = "0000";
            //pages = (pages+ String.valueOf(a)).substring( pages.length()-3 );
            pages = pages.substring(String.valueOf(a).length()) + String.valueOf(a);
            System.out.println("oooooooooo  "+pages);
            Picasso.get().load("https://"+ia+".us.archive.org/BookReader/BookReaderImages.php?zip=/"+ zip +"/items/" +
                    id+"/"+ id+"_jp2.zip&file="+ id+"_jp2/" +
                    id+"_" +pages+".jp2&id="+ id);
            System.out.println("https://"+ia+".us.archive.org/BookReader/BookReaderImages.php?zip=/"+ zip +"/items/" +
                    id+"/"+ id+"_jp2.zip&file="+ id+"_jp2/" +
                    id +  "_"+pages+".jp2&id="+ id);
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

                        Log.d("Mylog" , "Boolea @@@ "  + doc )   ;

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