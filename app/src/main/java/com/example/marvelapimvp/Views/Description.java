package com.example.marvelapimvp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.marvelapimvp.R;

public class Description extends AppCompatActivity {
    TextView description, nombre;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        description = findViewById(R.id.description);
        nombre = findViewById(R.id.nombre);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();

        if (intent != null){
            String names = intent.getStringExtra("name");
            String description1 = intent.getStringExtra("description");
            String imagenes = intent.getStringExtra("image");
            description.setText(description1);
            nombre.setText(names);


            Glide.with(this).load(imagenes).into(imageView);


        }

    }
}