package com.example.flightgearandroidapp.viewModel;

import com.example.flightgearandroidapp.model.FGPlayer;
import com.example.flightgearandroidapp.model.IFGPlayer;

public class ViewModel {
    private IFGPlayer modelFG;
    private int s;
    public ViewModel(){
        this.modelFG = new FGPlayer();
        s=5;
    }
    public void VMconnect(int port, String ip){
        modelFG.setIP(ip);
        modelFG.setPort(port);
        modelFG.connect();
    }

}
