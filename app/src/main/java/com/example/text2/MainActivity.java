package com.example.text2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.DataSetObserver;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
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


    private Thread secThread;
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




       /* but = (Button) findViewById(R.id.buttom);
        but1 = (Button) findViewById(R.id.buttom1);
        text1 = (EditText) findViewById(R.id.text);
        text2 = (TextView) findViewById(R.id.text1);*/


        init();



        /*but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                init();
            }
        });*/


    }

    private void  init (){

        listView = (ListView) findViewById(R.id.list_item);
        books_list = new ArrayList<>();
        adapter = new Cast_adapter(books_list , this);
        listView.setAdapter(adapter);
        runnable = new Runnable() {
            @Override
            public void run() {
                WebDok();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();
    }
    public  void  WebDok (){
        try {
            document = Jsoup.connect("https://openlibrary.org/search?q=title%3A+%22lessen%22&mode=everything").get();
            Elements table = document.getElementsByClass("list-books");
            Element booktable = table.get(0);
            Elements book_element = booktable.children();
            System.out.println("Titile : " + document.title());
            int i =0;
            for (Element book : book_element) {
                i ++;
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

                String data = str_book_element_text.substring(str_book_element_text.indexOf("First published") + 15 ,
                        str_book_element_text.indexOf("published") + 17);

                Log.d("Mylog" , "Titile url: " + url+ " name: " +name+" image: "+image+" avtor: "+avtor+" data: "+data );

                books_list.add(new Book( url , name , avtor , image , data ));
                Log.d("Mylog" , String.valueOf((books_list.size())));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

