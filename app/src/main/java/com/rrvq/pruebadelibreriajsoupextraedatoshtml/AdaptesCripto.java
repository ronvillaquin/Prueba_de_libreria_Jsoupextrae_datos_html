package com.rrvq.pruebadelibreriajsoupextraedatoshtml;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptesCripto extends RecyclerView.Adapter<AdaptesCripto.ViewHolder> implements View.OnClickListener {

    private final LayoutInflater layoutInflater;
    private final List<DatosCripto> dataCripto;
    private final Context context;


    private View.OnClickListener listener;

    public AdaptesCripto(Context context, List<DatosCripto> dataCripto) {
        this.layoutInflater = LayoutInflater.from(context);
        this.dataCripto = dataCripto;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recycler_cripto, parent, false);
        //con este escucha los envento de la lista
        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {


        DatosCripto datosCripto = dataCripto.get(position);

        //para no reciclar las vistas y que no se cambien de posicion los elementos
        //l desabilito
        holder.setIsRecyclable(false);

        Picasso.get()
                .load(datosCripto.getImgurl())
                .into(holder.img);

        holder.tvnombre.setText(datosCripto.getNombre());
        holder.tvprecio.setText(datosCripto.getPrecio());


    }


    @Override
    public int getItemCount() {
        return dataCripto.size();
    }

    public void setOnClickListener(View.OnClickListener listener){

        this.listener = listener;

    }

    @Override
    public void onClick(View v) {

        if (listener != null){
            listener.onClick(v);
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvnombre, tvprecio;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img);
            tvnombre = itemView.findViewById(R.id.tvnombre);
            tvprecio = itemView.findViewById(R.id.tvprecio);


        }
    }



}
