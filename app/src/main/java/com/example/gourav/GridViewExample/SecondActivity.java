package com.example.gourav.GridViewExample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    ImageView selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        selectedImage = (ImageView) findViewById(R.id.selectedImage); // init a ImageView
        Intent intent = getIntent(); // get Intent which we set from Previous Activity
        selectedImage.setImageResource(intent.getIntExtra("image", 0)); // get image from Intent and set it in ImageView

        selectedImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(SecondActivity.this, "You clicked on your selected card", Toast.LENGTH_SHORT).show();
                // set an Intent to Another Activity

                // Unpack bundle from intent to get passed image arrays
                Bundle extras = getIntent().getExtras();
                int[] cardBackImgArray = extras.getIntArray("cardBackImgArray");
                int[] cardFrontImgArray = extras.getIntArray("cardFrontImgArray");

                Intent intent = new Intent(SecondActivity.this, NextCardPickActivity.class);

                // Add passed image arrays back to next activity
                intent.putExtra("cardBackImgArray", cardBackImgArray);
                intent.putExtra("cardFrontImgArray", cardFrontImgArray);

                startActivity(intent); // start Intent

            }
        });
    }
}