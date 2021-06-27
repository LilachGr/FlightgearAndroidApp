package com.example.flightgearandroidapp.viewModel;

import com.example.flightgearandroidapp.model.FGPlayer;
import com.example.flightgearandroidapp.model.IFGPlayer;

import java.io.IOException;

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
    public void VMDisconnect() throws IOException {
        modelFG.disconnect();
    }
    public void VNChangeThrottle(double throttle){
        modelFG.changeThrottle(throttle);
    }
    public void VNChangeJoystick(float aileron, float elevator){
        modelFG.changeJoystick(aileron,elevator);
    }
    public void VNChangeRudder(double rudder){
        modelFG.changeRudder(rudder);
    }

    public boolean VMIsConnected(){
        return modelFG.isConnected();
    }
}
