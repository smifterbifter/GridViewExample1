package com.example.gourav.GridViewExample;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Random;

public class InitialCardPickActivity extends AppCompatActivity {
    GridView cardGridView;
    int[] cardBackInitialImgArray = {
            R.drawable.cardback1, R.drawable.cardback2, R.drawable.cardback3, R.drawable.cardback4, R.drawable.cardback5, R.drawable.cardback6,
            R.drawable.cardback1, R.drawable.cardback2, R.drawable.cardback3, R.drawable.cardback4, R.drawable.cardback5, R.drawable.cardback6,
            R.drawable.cardback1, R.drawable.cardback2, R.drawable.cardback3, R.drawable.cardback4, R.drawable.cardback5, R.drawable.cardback6,
            R.drawable.cardback1, R.drawable.cardback2, R.drawable.cardback3, R.drawable.cardback4, R.drawable.cardback5, R.drawable.cardback6
            };
    int[] cardFrontInitialImgArray = {
            R.drawable.cardfront0, R.drawable.cardfront0, R.drawable.cardfront0, R.drawable.cardfront0, R.drawable.cardfront0, R.drawable.cardfront0,
            R.drawable.cardfront1, R.drawable.cardfront1, R.drawable.cardfront1, R.drawable.cardfront1, R.drawable.cardfront1, R.drawable.cardfront1,
            R.drawable.cardfront2, R.drawable.cardfront2, R.drawable.cardfront2, R.drawable.cardfront2, R.drawable.cardfront2, R.drawable.cardfront2,
            R.drawable.cardfront3, R.drawable.cardfront3, R.drawable.cardfront3, R.drawable.cardfront3, R.drawable.cardfront3, R.drawable.cardfront3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardGridView = (GridView) findViewById(R.id.simpleGridView); // init GridView
        // Create an object of CustomAdapter and set Adapter to GirdView
        cardBackInitialImgArray = randomiseArray(cardBackInitialImgArray);
        cardFrontInitialImgArray = randomiseArray(cardFrontInitialImgArray);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), cardBackInitialImgArray);
        cardGridView.setAdapter(customAdapter);
        // implement setOnItemClickListener event on GridView
        cardGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(InitialCardPickActivity.this, "You picked a " + cardFrontInitialImgArray[position], Toast.LENGTH_SHORT).show();
                // set an Intent to Another Activity
                Intent intent = new Intent(InitialCardPickActivity.this, SecondActivity.class);
                intent.putExtra("image", cardFrontInitialImgArray[position]); // put image data in Intent

                // TODO Put in code to remove picked card befor passing to Second Activity
                Log.i("TAG", "setOnItemClickListener — get item number " + position);
                int[] cardBackUpdatedImgArray = removeItemFromArray(cardBackInitialImgArray, position);
                int[] cardFrontUpdatedImgArray = removeItemFromArray(cardFrontInitialImgArray, position);

                // Pass intial image arrays for use in next activities
                intent.putExtra("cardBackImgArray", cardBackUpdatedImgArray);
                intent.putExtra("cardFrontImgArray", cardFrontUpdatedImgArray);

                startActivity(intent); // start Intent
            }
        });
    }

    //Generic Method: Randomise order of 1D array values
    //	        Args: Integer Array (array).
    //       Returns: Shuffled int array
    public static int[] randomiseArray(int[] array){

        Random rgen = new Random();  // Random number generator
        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }
        return array;
    }

    // Generic Method: Remove item at position index from arrayIn
    //           Args: Integer Array (arrayIn), Int (index)
    //        Returns: Integer array (arrayOut)
    public static int[] removeItemFromArray(int[] arrayIn, int index) {
        int[] arrayOut = new int[arrayIn.length];
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