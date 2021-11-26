package com.example.text2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    Button but , but1;
    EditText text1;
    TextView text2;
    Document document;
    ArrayList<Book> books_list;
    ListView listView;
    Cast_adapter adapter;
    EditText ed ;


    String a= "a";
    private Thread secThread;
    private Runnable runnable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         ed   = (EditText) findViewById(R.id.file_name);

        but1 = (Button) findViewById(R.id.but1);
        books_list = new ArrayList<Book>();
        init();


        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed.getText() != null){
                    books_list.clear();
                    a = ed.getText().toString().replace(" " , "+");
                    init();
                }

            }
        });



    }





    private void  init (){

        listView = (ListView) findViewById(R.id.list_item);

        runnable = new Runnable() {
            @Override
            public void run() {
                 WebDok();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
        try {
            secThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        adapter = new Cast_adapter( this , 10 , books_list );
        listView.setAdapter(adapter);


    }
    public void WebDok (){
        try {
            document = Jsoup.connect("https://openlibrary.org/search?q="+a+"&mode=everything&has_fulltext=true").get();
            Elements table = document.getElementsByClass("list-books");
            Element booktable = table.get(0);
            Elements book_element = booktable.children();
            System.out.println("Titile : " + document.title());

            for (Element book : book_element) {

                Log.d("Mylog" , "   ---------------------------" );
                String str_book_element_text = book.text();
                String str_book_element_str = book.toString();
                String url = str_book_element_str.substring(str_book_element_str.indexOf("/works")).
                        substring(0, str_book_element_str.substring(str_book_element_str.indexOf("/works") + 2).indexOf("W")+3);
                String name = str_book_element_text.substring(0, str_book_element_text.indexOf("by")) ;
                String image = null;
                try {
                if (str_book_element_str.contains("src=\"//covers")){
                    image = str_book_element_str.substring( str_book_element_str.indexOf("src=\"//covers") + 5 ,
                            str_book_element_str.indexOf("jpg\"")+ 3 ) ;
                }
                else if (str_book_element_str.contains("src=\"/images")){
                    image = str_book_element_str.substring( str_book_element_str.indexOf("src=\"/images") + 5 ,
                            str_book_element_str.indexOf("png\"")+ 3 ) ;
                }
                else if (str_book_element_str.contains("src=\"//archive")){
                    image = str_book_element_str.substring( str_book_element_str.indexOf("src=\"//archive") + 5)
                            .substring(0 ,str_book_element_str.substring(str_book_element_str.indexOf("src=\"//archive")+7).indexOf("pist")+6 )  ;
                }
                }catch (Exception e){
                }
                String avtor =  str_book_element_text.substring(str_book_element_text.indexOf("by")+3,
                        str_book_element_text.indexOf(" published"));
                avtor = avtor.replace("First" , "");

                String data = str_book_element_text.substring(str_book_element_text.indexOf("First published") + 15 ,
                        str_book_element_text.indexOf("published") + 17);

                String text_url = str_book_element_str.substring(str_book_element_str.indexOf("<a href=\"/borrow")+ 9 ).substring(0,
                                                    str_book_element_str.substring(str_book_element_str.indexOf("<a href=\"/borrow")+9).indexOf("=ol")+3) ;

                Log.d("Mylog"  , text_url ) ;
                /*Log.d("Mylog" , "----------    "+str_book_element_str+"    ---------------" );
                Log.d("Mylog" , "-----------------------" );
                Log.d("Mylog" , "Titile url: " + url+ " name: " +name+" image: "+image+" avtor: "+avtor+" data: "+data );*/


                books_list.add(new Book( url , name , avtor , image , data , text_url));
                Log.d("Mylog" , String.valueOf((books_list.size())));

            }

        } catch (Exception e) {
            Log.d("Mylog" , "Eroooorrrrooo");

            e.printStackTrace();
        }
    }
}

