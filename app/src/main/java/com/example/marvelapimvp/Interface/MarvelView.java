package com.example.marvelapimvp.Interface;

import com.example.marvelapimvp.Models.Item;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public interface MarvelView  {
    void mostrarItems(ArrayList<Item> items);
    void mostrarCarrousel(List<CarouselItem> list);
    void mostrarError(String error);


}