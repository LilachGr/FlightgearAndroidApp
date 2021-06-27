package com.example.flightgearandroidapp.model;

import android.util.Log;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FGPlayer implements IFGPlayer{
    private PrintWriter out;
    private Socket soc;
    private ExecutorService exe;
    private String Ip;
    private int Port;
    boolean isConnected;
    public FGPlayer(){
        exe = null;
        out = null;
        soc = null;
        isConnected = false;
    }

    @Override
    public void connect() {
        exe = Executors.newFixedThreadPool(1);
        exe.execute(() -> {
            try {
                Log.d("The Model","Connecting");
                soc = new Socket(this.Ip,this.Port);
                out = new PrintWriter(soc.getOutputStream(),true);
                if(soc.isConnected() && out!=null) {
                    Log.d("The Model","Connected");
                    isConnected = true;
                }
            }
            catch (Exception e ){
                Log.d("The Model","Failed To Connect");
                Log.d("Model",e.toString());
            }
        });
    }

    @Override
    public void disconnect() throws IOException {
        if (soc != null) {
            try {
                soc.close();
            } catch (Throwable ignored) {}
        }
    }

    @Override
    public void changeThrottle(final double throttle) {
        if(this.isConnected) {
            exe.execute(() -> {
                out.print("set /controls/engines/current-engine/throttle " + throttle + "\r\n");
                //Log.i("TAG","throttle " + data + "\n");
                out.flush();
            });
        }
    }

    @Override
    public void changeJoystick(double aileron, double elevator) {
        if(this.isConnected) {
            exe.execute(() -> {
                out.print("set /controls/flight/aileron " + Double.toString(aileron) + "\r\n");
                out.flush();
                out.print("set /controls/flight/elevator " + Double.toString(elevator) + "\r\n");
                out.flush();
            });
        }
    }

    @Override
    public void changeRudder(final double rudder) {
        if(this.isConnected) {
            exe.execute(() -> {
                out.print("set /controls/flight/rudder " + rudder + "\r\n");
                //Log.i("TAG","rudder" + data + "\n");

                out.flush();
            });
        }
    }

    @Override
    public void setIP(String ip) {
        this.Ip = ip;
    }

    @Override
    public void setPort(int port) {
        this.Port = port;
    }

    @Override
    public boolean isConnected() {
        return this.isConnected;
    }
}
