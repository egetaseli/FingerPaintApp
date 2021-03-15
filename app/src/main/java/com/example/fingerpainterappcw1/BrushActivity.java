package com.example.fingerpainterappcw1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BrushActivity extends AppCompatActivity {

    // Initialise the variables
    String selectedBrush;
    int brushCode;
    int selectedWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brush);
        // Gets the current brush used in the main activity
        Bundle bundle = getIntent().getExtras();
        Paint.Cap theBrush = (Paint.Cap) bundle.getSerializable("ShapeOfBrush");
        int theWidthOfBrush = bundle.getInt("WidthOfBrush");
        String strBrush = theBrush.name();
        // Finds the field and displays the current brush type
        final EditText shapeField = (EditText) findViewById(R.id.editText1);
        shapeField.setText(strBrush);
        // Finds the field and displays the current brush width
        final EditText widthField = (EditText) findViewById(R.id.editText2);
        widthField.setText(""+theWidthOfBrush);
        selectedBrush = strBrush;
        selectedWidth = theWidthOfBrush;
    }

    public void BrushSelected(View v) {
        // Find out the brush type based on which brush button is clicked
        switch(v.getId()) {
            case R.id.button1:
                selectedBrush = "ROUND";
                brushCode = 1;
                break;
            case R.id.button2:
                selectedBrush = "SQUARE";
                brushCode = 2;
                break;
        }
        // Find out the brush width based on which brush button is clicked
        switch(v.getId()) {
            case R.id.button3:
                selectedWidth = 20;
                break;
            case R.id.button4:
                selectedWidth = 40;
                break;
            case R.id.button5:
                selectedWidth = 60;
                break;
        }
        // Set the current shape field as the shape clicked by user
        final EditText brushField = (EditText) findViewById(R.id.editText1);
        brushField.setText(selectedBrush);
        // Set the current width field as the width clicked by user
        final EditText brushWidthField = (EditText) findViewById(R.id.editText2);
        brushWidthField.setText(""+ selectedWidth);
    }

    public void ActivityFinished(View v){
        // Send the selected brush shape back to the main activity
        Bundle bundle = new Bundle();
        bundle.putInt("brushChosen",brushCode); // key, value
        bundle.putInt("widthChosen",selectedWidth);
        Intent result = new Intent();
        result.putExtras(bundle);
        setResult(Activity.RESULT_OK, result);
        // Finish the activity
        finish();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        // Save the currently selected brush shape and width in case activity gets destroyed(like rotation)
        outState.putInt("BrushShape", brushCode);
        outState.putInt("BrushWidth", selectedWidth);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Set the brush accordingly to the ones we saved
        brushCode = savedInstanceState.getInt("BrushShape");
        selectedWidth = savedInstanceState.getInt("BrushWidth");
    }
}
