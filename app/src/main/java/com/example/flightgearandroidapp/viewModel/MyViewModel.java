package com.example.flightgearandroidapp.viewModel;

import com.example.flightgearandroidapp.model.FGPlayer;
import com.example.flightgearandroidapp.model.IFGPlayer;

public class MyViewModel {
    private IFGPlayer modelFG;
    public MyViewModel(){
        this.modelFG = new FGPlayer();
    }
    public void VMConnect(int port, String ip){
        modelFG.setIP(ip);
        modelFG.setPort(port);
        modelFG.connect();
    }
    public void VMDisconnect(){
        modelFG.disconnect();
    }
    public void VNChangeThrottle(double throttle){
        modelFG.changeThrottle(throttle);
    }
    public void VNChangeJoystick(double aileron, double elevator){
        modelFG.changeJoystick(aileron,elevator);
    }
    public void VNChangeRudder(double rudder){
        modelFG.changeRudder(rudder);
    }
}
