package com.example.marvelapimvp.Interface;

import com.example.marvelapimvp.Models.Item;

import java.util.ArrayList;

public interface MarvelInteractor  {

    void obtenerListaPersonajes(int offset);
    void obtenerListaComics( int offset);
    void obtenerCarrousel(ArrayList<Item> item);

}
