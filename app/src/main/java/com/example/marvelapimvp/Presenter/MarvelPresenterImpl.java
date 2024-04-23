package com.example.marvelapimvp.Presenter;

import android.content.Context;

import com.example.marvelapimvp.Interface.MarvelInteractor;
import com.example.marvelapimvp.Interface.MarvelPresenter;
import com.example.marvelapimvp.Interface.MarvelView;
import com.example.marvelapimvp.Models.Item;
import com.example.marvelapimvp.Models.MarvelInteractorImpl;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class MarvelPresenterImpl implements MarvelPresenter {
    MarvelView view;
    Context context;
    MarvelInteractorImpl marvel;

    MarvelInteractor marvelInteractor = new MarvelInteractorImpl(this,context);


    public MarvelPresenterImpl(MarvelView view, Context context) {
        this.view = view;
        this.context = context;
    }




    @Override
    public void obtenerPersonajes(int offset) {
        marvelInteractor.obtenerListaPersonajes(offset);

    }

    @Override
    public void obtenerComic(int offset) {
        marvelInteractor.obtenerListaComics(offset);
    }


    @Override
    public void mostrarCarrousel(List<CarouselItem> list) {
        view.mostrarCarrousel(list);
    }

    @Override
    public void mostrarPersonajes(ArrayList<Item> items) {
        if (!items.isEmpty()){
            view.mostrarItems(items);

        }
    }

    public void mostrarItems(ArrayList<Item> items) {
        if (!items.isEmpty()){
            view.mostrarItems(items);

        }
    }

    @Override
    public void agregarItemsACarrousel(ArrayList<Item> items) {
        marvelInteractor.obtenerCarrousel(items);
    }

    @Override
    public void mostrarError(String error) {

    }


}
