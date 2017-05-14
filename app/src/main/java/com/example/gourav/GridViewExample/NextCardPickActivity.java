package com.example.gourav.GridViewExample;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class NextCardPickActivity extends AppCompatActivity {
    GridView cardGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Unpack bundle from intent to get passed image arrays
        Bundle extras = getIntent().getExtras();
        final int[] cardBackImgArray = extras.getIntArray("cardBackImgArray");
        final int[] cardFrontImgArray = extras.getIntArray("cardFrontImgArray");

        cardGridView = (GridView) findViewById(R.id.simpleGridView); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), cardBackImgArray);
        cardGridView.setAdapter(customAdapter);
        // implement setOnItemClickListener event on GridView
        cardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NextCardPickActivity.this, "You picked a " + cardFrontImgArray[position], Toast.LENGTH_SHORT).show();
                // set an Intent to Another Activity
                Intent intent = new Intent(NextCardPickActivity.this, SecondActivity.class);
                intent.putExtra("image", cardFrontImgArray[position]); // put image data in Intent

                // TODO Put in code to remove picked card befor passing to Second Activity
                Log.i("TAG", "setOnItemClickListener — get item number " + position);
                int cardBackUpdatedImgArray[] = removeItemFromArray(cardBackImgArray, position);
                int cardFrontUpdatedImgArray[] = removeItemFromArray(cardFrontImgArray, position);

                // Pass back image arrays
                intent.putExtra("cardBackImgArray", cardBackUpdatedImgArray);
                intent.putExtra("cardFrontImgArray", cardFrontUpdatedImgArray);

                startActivity(intent); // start Intent
            }
        });
    }

    public static int[] removeItemFromArray(int[] arrayIn, int index) {
        int[] arrayOut = new int[arrayIn.length - 1];
        for(int i=0, j=0; i<arrayIn.length; i++) {
            Log.i("TAG", "arrayIn: " + i + ", arrayOut: " + j + ", position: " + index);
            if(i != index) {
                arrayOut[j] = arrayIn[i];
                j++;
            }
        }
        return arrayOut;
    }

}