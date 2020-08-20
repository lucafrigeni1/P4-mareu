package com.example.mareu.DI;

import com.example.mareu.service.ApiService;
import com.example.mareu.service.MeetingApiService;

public class DI {

    private static ApiService service = new MeetingApiService();

    public static ApiService getApiService(){return service;}

    public static ApiService getNewInstanceApiService(){return new MeetingApiService();}
}
