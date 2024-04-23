package com.example.marvelapimvp.Views;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.marvelapimvp.Models.Item;
import com.example.marvelapimvp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaMarvelAdapter extends RecyclerView.Adapter<ListaMarvelAdapter.ViewHolder> {

    private ArrayList<Item> items;

    final ListaMarvelAdapter.OnItemClickListener listener;
    ArrayList<Item> filtrado;

    private Context context;
    private int lastPosition = -1;

    public ListaMarvelAdapter(Context context, ListaMarvelAdapter.OnItemClickListener listener) {
        this.context = context;
        items = new ArrayList<>();
        this.listener = listener;
        filtrado = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_list, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (!items.isEmpty()) {
            Item i = items.get(position);
            holder.nameCharacter.setText(i.getTitle());
            holder.binData(items.get(position));
            setAnimation(holder.itemView, position);
            String imageUrl = i.getUrl();
            Glide.with(context).load(imageUrl).centerCrop().circleCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transition(withCrossFade()).into(holder.imageCharacter);

        }
    }
    public void filtrado(String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            items.clear();
            items.addAll(filtrado);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                items.clear();
                List<Item> collecion = filtrado.stream()
                        .filter(i -> i.getTitle().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                items.addAll(collecion);
            } else {
                for (Item itemms : filtrado) {
                    if (itemms.getTitle().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        items.add(itemms);
                    }

                }
            }
        }
        notifyDataSetChanged();

    }

    public void limpiarFiltro() {
        filtrado.clear();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void adicionarListaMarvel(ArrayList<Item> items) {
        this.items.addAll(items);
        filtrado.addAll(items);
        notifyDataSetChanged();
    }

    public void eliminarItems() {
        items.clear();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCharacter;
        TextView nameCharacter;

        public ViewHolder(View itemView) {
            super(itemView);

            imageCharacter = itemView.findViewById(R.id.imagemarvel);
            nameCharacter = itemView.findViewById(R.id.textname);

        }

        public void binData(Item item) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(slideIn);
            lastPosition = position;
        }
    }

}