package com.example.mareu.Controler.DI;

import com.example.mareu.Controler.service.ApiService;
import com.example.mareu.Controler.service.MeetingApiService;

public class DI {

    private static ApiService service = new MeetingApiService();

    public static ApiService getApiService(){return service;}

    public static ApiService getNewInstanceApiService(){
        service = new MeetingApiService();
        return service;
    }
}
