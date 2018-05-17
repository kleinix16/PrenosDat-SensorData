/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sdm;

import java.io.IOException;
import java.text.DateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author klein
 */
public class PollsDemo {

    public static void main(String[] args) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.6")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PollsService polls = retrofit.create(PollsService.class);
        
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        
        //add new sensor data to server
        SenData sensorData = new SenData();
        Date date = new Date();
        
        sensorData.date =  dateFormat.format(date);
        sensorData.hum = 60 + (int)(Math.random() * (100 - 60));
        sensorData.temp = 10 + (int)(Math.random() * (35 - 10));
        
        try {
            System.out.print(sensorData.toString());
            Response<SenData> execute = polls.getSensorData().execute();
            Logger.getGlobal().log(Level.INFO, execute.toString());
            if (execute.isSuccessful()) { 
                Logger.getGlobal().log(Level.INFO, execute.body().toString());
                Logger.getGlobal().log(Level.INFO, "SUCCESSFULL");
            }else 
               Logger.getGlobal().log(Level.INFO, "FAILED");
        } catch (IOException ex) {
            Logger.getLogger(PollsDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print("------------------------------------------------------------------\n\r");
        
 
    }
}
