package com.example.kaanacha.myapplication;

import android.annotation.SuppressLint;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class LineupActivity extends AppCompatActivity {

    float xCoOrdinate,yCoOrdinate;
    LayoutInflater inflater;
    ArrayList<Integer> buttonIDs;
    ViewGroup container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lineup);


        goMain();
        addingimage();

    }
    private void addingimage() {
         final RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout);
         final Button btn = (Button) findViewById(R.id.addButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new ImageView widget
                ImageView iv = new ImageView(getApplicationContext());

                // Set an image for ImageView
                iv.setImageDrawable(getDrawable(R.drawable.ic_download));

                Toast.makeText(getApplicationContext(), Integer.toString(iv.getId()), Toast.LENGTH_LONG).show();

                // Create layout parameters for ImageView
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                // Add rule to layout parameters
                // Add the ImageView below to Button
                lp.addRule(RelativeLayout.BELOW, btn.getId());

                // Add layout parameters to ImageView
                iv.setLayoutParams(lp);

                // Finally, add the ImageView to layout
                rl.addView(iv);
                movingImagelistener(iv);

            }
        });
    }
    private void goMain() {
        Button mainButton = findViewById(R.id.backButton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void movingImagelistener(ImageView imageView) {

        Toast.makeText(getApplicationContext(), Integer.toString(imageView.getId()), Toast.LENGTH_LONG).show();


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        xCoOrdinate = view.getX() - event.getRawX();
                        yCoOrdinate = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });

    }


}
