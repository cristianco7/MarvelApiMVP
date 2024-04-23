package com.example.marvelapimvp.Views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;


import com.example.marvelapimvp.Interface.MarvelPresenter;
import com.example.marvelapimvp.Interface.MarvelView;
import com.example.marvelapimvp.Models.Item;

import com.example.marvelapimvp.Presenter.MarvelPresenterImpl;
import com.example.marvelapimvp.R;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MarvelView, SearchView.OnQueryTextListener {
    Button buttonComic, buttonPersonajes;
    final String TAG = "MARVEL";
    SearchView search;
    private boolean aptoParaCargar;
    private RecyclerView recyclerView;
    private ListaMarvelAdapter listaMarvelAdapter;
    private int offset;
    ImageCarousel carousel;
    boolean isPersonajes = true;
    MarvelPresenter mi = new MarvelPresenterImpl(MainActivity.this, this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPersonajes = findViewById(R.id.button2);
        buttonComic = findViewById(R.id.button);
        search = findViewById(R.id.search);
        carousel = findViewById(R.id.carousel);
        recyclerView = findViewById(R.id.recyclerView);
        listaMarvelAdapter = new ListaMarvelAdapter(this, new ListaMarvelAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                moveToDescription(item);

            }
        });
        recyclerView.setAdapter(listaMarvelAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, "llegamos al final");
                            aptoParaCargar = false;
                            offset += 20;
                            if (!listaMarvelAdapter.getItems().isEmpty()) {
                                if (isPersonajes) {
                                    mi.obtenerPersonajes(offset);
                                } else {
                                    mi.obtenerComic(offset);
                                }
                            }
                        }
                    }
                }
            }
        });
        aptoParaCargar = true;
        offset = 0;
        mi.obtenerPersonajes(offset);


        buttonPersonajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offset = 0;
                isPersonajes = true;
                recyclerView.scrollToPosition(0);
                aptoParaCargar = true;
                offset = 0;
                listaMarvelAdapter.eliminarItems();
                carousel.setData(new ArrayList<CarouselItem>());
                mi.obtenerPersonajes(offset);
                listaMarvelAdapter.limpiarFiltro();

            }
        });
        buttonComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offset = 0;
                isPersonajes = false;
                recyclerView.scrollToPosition(0);
                aptoParaCargar = true;
                listaMarvelAdapter.eliminarItems();
                carousel.setData(new ArrayList<CarouselItem>());
                mi.obtenerComic(offset);
                listaMarvelAdapter.limpiarFiltro();


            }
        });

        search.setOnQueryTextListener(this);
        search.clearFocus();
        carousel.registerLifecycle(getLifecycle());
        carousel.setData(new ArrayList<CarouselItem>());
        carousel.setAutoPlay(true);
        carousel.setCarouselListener(new CarouselListener() {
            @Nullable
            @Override
            public ViewBinding onCreateViewHolder(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull ViewBinding viewBinding, @NonNull CarouselItem carouselItem, int i) {

            }

            @Override
            public void onClick(int position, @NonNull CarouselItem carouselItem) {
                if (!listaMarvelAdapter.getItems().isEmpty()) {
                    Item i = listaMarvelAdapter.getItems().get(position);
                    moveToDescription(i);
                }
            }

            @Override
            public void onLongClick(int i, @NonNull CarouselItem carouselItem) {

            }
        });
    }


    @Override
    public void mostrarItems(ArrayList<Item> items) {
        aptoParaCargar = true;
        listaMarvelAdapter.adicionarListaMarvel(items);
        mi.agregarItemsACarrousel(items);

    }

    @Override
    public void mostrarCarrousel(List<CarouselItem> list) {
        carousel.addData(list);
    }

    @Override
    public void mostrarError(String error) {

    }


    private void moveToDescription(Item item) {

        Intent intent = new Intent(this, Description.class);
        intent.putExtra("name", item.getTitle());
        intent.putExtra("description", item.getId());
        intent.putExtra("image", item.getUrl());

        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        listaMarvelAdapter.filtrado(s);
        return false;
    }

}

