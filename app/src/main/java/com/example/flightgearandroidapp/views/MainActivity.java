package com.example.flightgearandroidapp.views;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.lifecycle.ViewModel;
import com.example.flightgearandroidapp.R;
import com.example.flightgearandroidapp.model.FGPlayer;
import com.example.flightgearandroidapp.model.IFGPlayer;

public class MainActivity extends AppCompatActivity {
    /*private ViewModel myViewModel;
    public MainActivity(){
        this.myViewModel = new ViewModel();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
