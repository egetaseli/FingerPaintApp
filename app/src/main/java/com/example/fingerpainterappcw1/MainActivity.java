package com.example.fingerpainterappcw1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // Activity request codes for differentiating activity results
    static final int ACTIVITY_COLOUR_REQUEST_CODE = 1;
    static final int ACTIVITY_BRUSH_REQUEST_CODE = 2;

    // Variables for getting state of the brush
    int displayColour = Color.BLACK;
    int displayBrush = 1;
    int widthBrush = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Implicit intent for opening images from other sources
        Intent intent = getIntent();
        final FingerPainterView thePaintView = findViewById(R.id.paintView);
        thePaintView.load(intent.getData());
    }

    public void OnColourSelect(View v) {
        // Find the paintView and retrieve the current colour from it
        final FingerPainterView thePaintView = findViewById(R.id.paintView);
        int retrievedColour = thePaintView.getColour();
        // Send the current colour to the colour choosing activity
        Bundle bundle = new Bundle();
        bundle.putInt("ColourOfBrush", retrievedColour); // key, value
        Intent intent = new Intent(MainActivity.this, ColourActivity.class);
        intent.putExtras(bundle); // assign the bundle as the payload
        startActivityForResult(intent, ACTIVITY_COLOUR_REQUEST_CODE);
    }

    public void OnBrushSelect(View v) {
        // Find the paintView and retrieve the current brush(shape and width) from it
        final FingerPainterView thePaintView = findViewById(R.id.paintView);
        Paint.Cap retrievedBrush = thePaintView.getBrush();
        int theWidth = thePaintView.getBrushWidth();
        //Send the current brush shape and width to BrushActivity
        Bundle bundle = new Bundle();
        bundle.putSerializable("ShapeOfBrush", retrievedBrush); // key, value
        bundle.putInt("WidthOfBrush",theWidth);
        Intent intent = new Intent(MainActivity.this, BrushActivity.class);
        intent.putExtras(bundle); // assign the bundle as the payload
        startActivityForResult(intent, ACTIVITY_BRUSH_REQUEST_CODE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("g53mdp", "action ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("g53mdp", "action ACTION_MOVE");
                // Find the paintView and retrieve the current colour from it
                final FingerPainterView thePaintView = findViewById(R.id.paintView);
                int retrievedColour = thePaintView.getColour();
                // Send the current colour to the colour choosing activity
                Bundle bundle = new Bundle();
                bundle.putInt("ColourOfBrush", retrievedColour); // key, value
                Intent intent = new Intent(MainActivity.this, ColourActivity.class);
                intent.putExtras(bundle); // assign the bundle as the payload
                startActivityForResult(intent, ACTIVITY_COLOUR_REQUEST_CODE);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("g53mdp", "action ACTION_UP");
                break;
        }
        return true;
        //return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        // Result from our colourActivity
        if (requestCode == ACTIVITY_COLOUR_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Get the colourCode from ColourActivity
                Bundle bundle = data.getExtras();
                displayColour = bundle.getInt("colourChosen");
                // Find the paintView and then based on the colourCode set the colour of brush
                final FingerPainterView thePaintView = findViewById(R.id.paintView);
                switch (displayColour) {
                    case 1:
                        thePaintView.setColour(Color.GREEN);
                        break;
                    case 2:
                        thePaintView.setColour(Color.BLUE);
                        break;
                    case 3:
                        thePaintView.setColour(Color.YELLOW);
                        break;
                    case 4:
                        thePaintView.setColour(Color.RED);
                        break;
                    case 5:
                        thePaintView.setColour(Color.BLACK);
                        break;
                    case 6:
                        thePaintView.setColour(Color.WHITE);
                        break;
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("Error", "" + resultCode);
            }
        }
        // Result from our BrushActivity
        if (requestCode == ACTIVITY_BRUSH_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // Get the brush shape and brush width from BrushActivity
                Bundle bundle = data.getExtras();
                displayBrush = bundle.getInt("brushChosen");
                widthBrush = bundle.getInt("widthChosen");
                // Find the paintView and then based on the displayBrush code set the shape of brush
                final FingerPainterView thePaintView = findViewById(R.id.paintView);
                switch (displayBrush) {
                    case 1:
                        thePaintView.setBrush(Paint.Cap.ROUND);
                        break;
                    case 2:
                        thePaintView.setBrush(Paint.Cap.SQUARE);
                        break;
                }
                // Set the width of the brush
                thePaintView.setBrushWidth(widthBrush);
            } else if (resultCode == RESULT_CANCELED) {
                Log.d("Error", "" + resultCode);
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        // Save the brush colour, shape and width
        outState.putInt("BrushColour", displayColour);
        outState.putInt("BrushShape", displayBrush);
        outState.putInt("BrushWidth", widthBrush);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final FingerPainterView thePaintView = findViewById(R.id.paintView);
        // Set the colour of the brush to the one we saved after activity destroyed
        displayColour = savedInstanceState.getInt("BrushColour");
        switch (displayColour) {
            case 1:
                thePaintView.setColour(Color.GREEN);
                break;
            case 2:
                thePaintView.setColour(Color.BLUE);
                break;
            case 3:
                thePaintView.setColour(Color.YELLOW);
                break;
            case 4:
                thePaintView.setColour(Color.RED);
                break;
            case 5:
                thePaintView.setColour(Color.BLACK);
                break;
            case 6:
                thePaintView.setColour(Color.WHITE);
                break;
        }
        // Set the shape of the brush the one we saved after activity destroyed
        displayBrush = savedInstanceState.getInt("BrushShape");
        switch (displayBrush) {
            case 1:
                thePaintView.setBrush(Paint.Cap.ROUND);
                break;
            case 2:
                thePaintView.setBrush(Paint.Cap.SQUARE);
                break;
        }
        // Set the width of the brush the one we saved after activity destroyed
        widthBrush = savedInstanceState.getInt("BrushWidth");
        thePaintView.setBrushWidth(widthBrush);
    }
}
