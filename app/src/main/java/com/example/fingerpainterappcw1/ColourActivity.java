package com.example.fingerpainterappcw1;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.graphics.Color;
import android.widget.EditText;

public class ColourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);
        // Gets the current colour used in the main activity
        Bundle bundle = getIntent().getExtras();
        @ColorInt
        int theColour = bundle.getInt("ColourOfBrush");
        // Convert the int value of the colour to a string for display
        String strColour = "BLACK";
        switch(theColour){
            case Color.GREEN:
                strColour = "GREEN";
                break;
            case Color.BLUE:
                strColour = "BLUE";
                break;
            case Color.YELLOW:
                strColour = "YELLOW";
                break;
            case Color.RED:
                strColour = "RED";
                break;
            case Color.BLACK:
                strColour = "BLACK";
                break;
            case Color.WHITE:
                strColour = "WHITE";
                break;
        }
        // finds the field and displays the current colour
        final EditText colourField = (EditText) findViewById(R.id.editText1);
        colourField.setText(strColour);
    }

    public void ColourSelected(View v){
        // Initialise the variables
        String selectedColour = "BLACK";
        int colourCode = 0;
        // Find out the colour based on which colour button is clicked
        switch(v.getId()){
            case R.id.button1:
                selectedColour = "GREEN";
                colourCode = 1;
                break;
            case R.id.button2:
                selectedColour = "BLUE";
                colourCode = 2;
                break;
            case R.id.button3:
                selectedColour = "YELLOW";
                colourCode = 3;
                break;
            case R.id.button4:
                selectedColour = "RED";
                colourCode = 4;
                break;
            case R.id.button5:
                selectedColour = "BLACK";
                colourCode = 5;
                break;
            case R.id.button6:
                selectedColour = "WHITE";
                colourCode = 6;
                break;
        }
        // Set the current colour field as the colour clicked by user
        final EditText colourField = (EditText) findViewById(R.id.editText1);
        colourField.setText(selectedColour);
        // Send the selected colour back to the main activity
        Bundle bundle = new Bundle();
        bundle.putInt("colourChosen",colourCode); // key, value
        Intent result = new Intent();
        result.putExtras(bundle);
        setResult(Activity.RESULT_OK, result);
        // Finish the activity
        finish();
    }
}
