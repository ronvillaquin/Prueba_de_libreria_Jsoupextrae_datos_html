package com.rrvq.pruebadelibreriajsoupextraedatoshtml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdaptesCripto adaptesCripto;
    ArrayList<DatosCripto> dataCripto = new ArrayList<>();
    Button btn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        btn = findViewById(R.id.button);
        progressBar = findViewById(R.id.progress_circular);


        progressBar.setVisibility(View.VISIBLE);
        progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
        cargarDatosJsoup();
//        libreriaJSOUP();
        /*setRecyclerView();
        Content content = new Content();
        content.execute();*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.setEnabled(false);
                dataCripto.clear();
                cargarDatosJsoup();
                /*Content content = new Content();
                content.execute();*/
            }
        });

        btn.setEnabled(false);

    }

    /*private class Content extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            progressBar.setVisibility(View.GONE);
            progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
            adaptesCripto.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc = Jsoup.connect(Constantes.URLDOLARTODAY).timeout(6000).get();

                Elements data = doc.select("tbody tr");

                int size = data.size();

                for (int i=0; i<10; i++){
                    String imgurl = data.select("div[class=sc-16r8icm-0 sc-1teo54s-0 jDQqmt]")
                            .select("img.coin-logo")
                            .eq(i)
                            .attr("src");

                    String nombre = data.select("div[class=sc-16r8icm-0 sc-1teo54s-0 jDQqmt]")
                            .select("div[class=sc-16r8icm-0 sc-1teo54s-1 cPNAgw]")
                            .eq(i)
                            .text();

                    String precio = data.select("td")
                            .select("div[class=price___3rj7O] a")
                            .eq(i)
                            .text();

                    dataCripto.add(new DatosCripto(imgurl,nombre, precio));
                    Log.d("items", "img: " +imgurl+" . nombre: "+ nombre);
                }
                setRecyclerView();

                btn.setEnabled(true);

            } catch (IOException e) {
                e.printStackTrace();
                Log.d("items", "No sirve");
            }

            return null;
        }
    }*/

    //prueba de pagina por pagina de la moneda q quiero
    public void cargarDatosJsoup(){

        new Thread(new Runnable() {
            final DatosDolar datosDolar = new DatosDolar();
            @Override
            public void run() {

                try {
                    Document doc = Jsoup.connect(Constantes.URLDOLARTODAY).timeout(6000).get();

                    Elements data = doc.select("tbody tr");

                    int size = data.size();

                    for (int i=0; i<10; i++){
                        String imgurl = data.select("div[class=sc-16r8icm-0 sc-1teo54s-0 jDQqmt]")
                                .select("img.coin-logo")
                                .eq(i)
                                .attr("src");

                        String nombre = data.select("div[class=sc-16r8icm-0 sc-1teo54s-0 jDQqmt]")
                                .select("div[class=sc-16r8icm-0 sc-1teo54s-1 cPNAgw]")
                                .eq(i)
                                .text();

                        String precio = data.select("td")
                                .select("div[class=price___3rj7O] a")
                                .eq(i)
                                .text();

                        dataCripto.add(new DatosCripto(imgurl,nombre, precio));
                        Log.d("items", "img: " +imgurl+" . nombre: "+ nombre);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        setRecyclerView();
                        btn.setEnabled(true);
                        progressBar.setVisibility(View.GONE);
                        progressBar.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
                        adaptesCripto.notifyDataSetChanged();

                    }
                });

            }
        }).start();

    }

    //prueba con una tabla en web principal de coinmarket sirve para 10arrays
    /*public void cargarDatosJsoup(){

        new Thread(new Runnable() {
            final DatosDolar datosDolar = new DatosDolar();
            @Override
            public void run() {

                try {
                    Document doc = Jsoup.connect(Constantes.URLDOLARTODAY).timeout(6000).get();

                    Elements data = doc.select("tbody tr");

                    int size = data.size();

                    for (int i=0; i<10; i++){
                        String imgurl = data.select("div[class=sc-16r8icm-0 sc-1teo54s-0 jDQqmt]")
                                .select("img.coin-logo")
                                .eq(i)
                                .attr("src");

                        String nombre = data.select("div[class=sc-16r8icm-0 sc-1teo54s-0 jDQqmt]")
                                .select("div[class=sc-16r8icm-0 sc-1teo54s-1 cPNAgw]")
                                .eq(i)
                                .text();

                        String precio = data.select("td")
                                .select("div[class=price___3rj7O] a")
                                .eq(i)
                                .text();

                        dataCripto.add(new DatosCripto(imgurl,nombre, precio));
                        Log.d("items", "img: " +imgurl+" . nombre: "+ nombre);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        setRecyclerView();
                        btn.setEnabled(true);

                    }
                });

            }
        }).start();

    }*/

    public void setRecyclerView(){

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptesCripto = new AdaptesCripto(this, dataCripto);


        adaptesCripto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //para usar solo listas y referenciasr con get listas.getIdLista() recibo de listas
                DatosCripto datosCripto = dataCripto.get(recyclerView.getChildAdapterPosition(v));

                /*Toast.makeText(getApplicationContext(), "Seleccion: "+
                        data.get(recyclerView.getChildAdapterPosition(v)).getNombre_usu()+
                        " ID: "+data.get(recyclerView.getChildAdapterPosition(v)).getId_usuario(), Toast.LENGTH_SHORT).show();*/

            }
        });


        recyclerView.setAdapter(adaptesCripto);

    }

    // prueba d euna sola we sin reciycler
//    public void libreriaJSOUP(){
//        new Thread(new Runnable() {
//            DatosDolar datosDolar = new DatosDolar();
//            @Override
//            public void run() {
//
//                try {
//                    Document doc = Jsoup.connect(Constantes.URLDOLARTODAY).timeout(6000).get();
//
////                    Elements image = doc.select("#logo img");
//                    //dolar today
////                    Element nombre = doc.select("div.post-title a").first();
////                    String badynombre = bodynombre.attr("value");
////                    Element precio = doc.select("div.section-title-1 h1").first();
//
//                    //info dolar
//                    Element image = doc.select("tr img[class=coin-logo]").first();
//                    Element nombre = doc.select("tr p[font-weight=semibold]").first();
//                    Element precio = doc.select("tr a[href=/currencies/bitcoin/markets/]").first();
//                    String p = precio.text();
//
//
//                    datosDolar.setImgurl(image.attr("src"));
////                    datosDolar.setNombreDivisa(nombre.text());
//                    datosDolar.setNombreDivisa(nombre.text());
//                    datosDolar.setPrecioDivisa(precio.text());
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        // para colocar la imagen desde url con picaso
//                        Picasso.get()
//                                .load(datosDolar.getImgurl())
//                                .into(img);
//                        tvnombreMoneda.setText(datosDolar.getNombreDivisa());
//                        tvprecioMoneda.setText(datosDolar.getPrecioDivisa());
//
//                    }
//                });
//
//            }
//        }).start();
//    }
}