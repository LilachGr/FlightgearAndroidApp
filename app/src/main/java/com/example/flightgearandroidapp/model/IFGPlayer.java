package com.example.flightgearandroidapp.model;

public interface IFGPlayer {
    public void connect();
    public void disconnect();
    public void changeThrottle(double throttle);
    public void changeJoystick(double aileron, double elevator);
    public void changeRudder(double rudder);
    public void setIP(String ip);
    public void setPort(int port);
}
