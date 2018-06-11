/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sdm;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author klein
 */
public class Sensor {

    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        while(true)
        {
        Date date = new Date();
        
        String tdate =  dateFormat.format(date);
        int hum = (60 + (int)(Math.random() * (100 - 60)));
        int temp = 10 + (int)(Math.random() * (35 - 10));
        
        String ID ="QSlkjdfdfs85dfsd4vdqqwAAAs8aasf47sf5ads7a";
        
        String urlParameters
                = "date=" + URLEncoder.encode(tdate, "UTF-8")
                + "&temp=" + URLEncoder.encode(String.valueOf(temp), "UTF-8")
                + "&hum=" + URLEncoder.encode(String.valueOf(hum), "UTF-8")
                + "&ID=" + URLEncoder.encode(ID, "UTF-8");
        URL url;
        HttpURLConnection connection = null;
        try {
            //Create connection
            url = new URL("http://localhost:8080/new");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length) + "");
            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //Send request
            DataOutputStream wr = new DataOutputStream(
                    connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            //Get Response	
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.print(response.toString()+ " "+urlParameters+"\n\r");
            
        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
        System.out.print("------------------------------------------------------------------\n\r");
        TimeUnit.SECONDS.sleep(5);
        }

    }

}
