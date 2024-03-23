package com.example.exerciciopinguim;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final PinguimView mv = new PinguimView(this); //classe derivada de View
        setContentView(mv);
    }
}