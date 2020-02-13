package com.example.atlanai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class DisplayImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        ImageView imageView = findViewById(R.id.imageView);

        String s = getIntent().getStringExtra("image_path");
        Bitmap bitmap = BitmapFactory.decodeFile(s);
        imageView.setImageBitmap(bitmap);

    }
}
