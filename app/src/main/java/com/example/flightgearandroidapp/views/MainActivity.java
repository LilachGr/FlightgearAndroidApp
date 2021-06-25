package com.example.flightgearandroidapp.views;

import android.content.Context;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.flightgearandroidapp.R;
import com.example.flightgearandroidapp.viewModel.MyViewModel;

public class MainActivity extends AppCompatActivity {
    private MyViewModel myViewModel;
    private EditText ip;
    private EditText port;
    private boolean isConnect = false;

    public MainActivity(){
        this.myViewModel = new MyViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.ip = findViewById(R.id.ipText);
        this.port = findViewById(R.id.portText);
        Button connButton = findViewById(R.id.connectButton);
        //Define and attach click listener
        connButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });
        SeekBar rudder = findViewById(R.id.rudderSeekBar);
        rudder.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double val = (progress - 20) / 20.0;
                myViewModel.VNChangeRudder(val);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {  }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {  }
        });
        SeekBar throttle = findViewById(R.id.throttleSeekBar);
        throttle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double val = progress / 20.0;
                myViewModel.VNChangeThrottle(val);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {  }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {  }
        });
    }

    public void connect(){
        String ipStr = String.valueOf(ip.getText());
        String portStr = String.valueOf(port.getText());
        Context context = getApplicationContext();
        CharSequence textFail = "Enter correct ip and port! Try again!";
        CharSequence textSuccess = "The connection succeeded";

        int duration = Toast.LENGTH_SHORT;

        if (! isNumeric(portStr) || ! validIP(ipStr)){
            Toast.makeText(context, textFail, duration).show();
            return;
        }
        int port1 = Integer.parseInt(portStr);
        myViewModel.VMConnect(port1, ipStr);
        Toast.makeText(context, textSuccess, duration).show();
    }

    private boolean isNumeric(String string) {
        if(string == null || string.equals("")) {
            return false;
        }
        try {
             int intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException error) {
            return false;
        }
    }

    private boolean validIP (String ip) {
        try {
            if ( ip == null || ip.isEmpty()) {
                return false;
            }
            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }
            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            return !ip.endsWith(".");
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
