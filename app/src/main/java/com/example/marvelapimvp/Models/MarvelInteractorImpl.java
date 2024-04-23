package com.example.marvelapimvp.Models;

import android.content.Context;
import android.util.Log;

import com.example.marvelapimvp.Interface.MarvelApiService;
import com.example.marvelapimvp.Interface.MarvelInteractor;

import com.example.marvelapimvp.Interface.MarvelPresenter;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MarvelInteractorImpl implements MarvelInteractor {
    private Retrofit retrofit;
    private boolean aptoParaCargar;
    final String apikey = "2e7b336e68e0fab8c40dcca28139dde6";
    final String hash = "4eb45b4fdd3bd92029057d403cebe315";
    final int ts = 1;
    MarvelPresenter presenter;
    Context context;


    public MarvelInteractorImpl(MarvelPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }


    @Override
    public void obtenerListaPersonajes(int offset) {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MarvelApiService service = retrofit.create(MarvelApiService.class);
        Call<Root> rootCall = service.obtenerListaMarvel(20, offset, apikey, hash, ts);
        rootCall.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Root root = response.body();
                    ArrayList<MarvelCharacter> heroes = root.getData().getResults();
                    ArrayList<Item> items = new ArrayList<>();

                    for (MarvelCharacter heroe : heroes) {
                        String imageUrl = heroe.getThumbnail().getPath() + "." + heroe.getThumbnail().getExtension();
                        if (!imageUrl.toLowerCase().contains("image_not_available")) {
                            items.add(new Item(heroe.getName(), heroe.getDescription(), imageUrl));
                        }
                    }
                    presenter.mostrarItems(items);
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.i("IMPORTANTE", "error en la solicitud" + t.getMessage());

            }
        });
    }

    @Override
    public void obtenerListaComics(int offset) {
        MarvelApiService service = retrofit.create(MarvelApiService.class);
        Call<Root2> rootCall2 = service.obtenerListaComics(20, offset, apikey, hash, ts);

        rootCall2.enqueue(new Callback<Root2>() {
            @Override
            public void onResponse(Call<Root2> call, Response<Root2> response) {
                if (response.isSuccessful()) {
                    Root2 root2 = response.body();
                    ArrayList<Comics> comics = root2.getData().getResults();
                    ArrayList<Item> items = new ArrayList<>();

                    for (Comics c : comics) {
                        String imageUrl = c.getThumbnail().getPath() + "." + c.getThumbnail().getExtension();
                        if (!imageUrl.toLowerCase().contains("image_not_available")) {
                            items.add(new Item(c.getTitle(), c.getUpc(), imageUrl));
                        }
                    }
                    presenter.mostrarPersonajes(items);
                }
            }

            @Override
            public void onFailure(Call<Root2> call, Throwable t) {
                Log.i("IMPORTANTE", "error en la solicitud" + t.getMessage());

            }
        });
    }

    @Override
    public void obtenerCarrousel(ArrayList<Item> items) {
        List<CarouselItem> list = new ArrayList<>();

        for (Item i : items) {
            String imageUrl = i.getUrl();
            CarouselItem carouselItem = new CarouselItem(imageUrl);
            list.add(carouselItem);

        }

        presenter.mostrarCarrousel(list);
    }

}
