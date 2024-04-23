package com.example.marvelapimvp.Interface;

import com.example.marvelapimvp.Models.Comics;
import com.example.marvelapimvp.Models.Item;
import com.example.marvelapimvp.Models.MarvelCharacter;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public interface MarvelPresenter  {
    void obtenerPersonajes(int offset);
    void obtenerComic(int offset);
    void mostrarCarrousel(List<CarouselItem> list);
    void mostrarPersonajes(ArrayList<Item> items);
    void mostrarItems(ArrayList<Item> comics);

    void agregarItemsACarrousel(ArrayList<Item> items);

    void mostrarError(String error);
}

