package com.example.flightgearandroidapp.model;

import java.io.IOException;

public interface IFGPlayer {
    public void connect();
    public void disconnect() throws IOException;
    public void changeThrottle(double throttle);
    public void changeJoystick(double aileron, double elevator);
    public void changeRudder(double rudder);
    public void setIP(String ip);
    public void setPort(int port);
    public boolean isConnected();
}
