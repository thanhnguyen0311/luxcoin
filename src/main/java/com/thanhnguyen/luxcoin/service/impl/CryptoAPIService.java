package com.thanhnguyen.luxcoin.service.impl;
import com.thanhnguyen.luxcoin.service.ICryptoAPIService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Service
public class CryptoAPIService implements ICryptoAPIService {

    @Override
    public boolean checkCryptoAvailable(String name) {
//        try {
//            String endpoint = "https://api.binance.com/api/v3/ticker/price?symbol=" + name + "USDT";
//            HttpClient httpClient = HttpClients.createDefault();
//            HttpGet request = new HttpGet(endpoint);
//            HttpResponse response = httpClient.execute(request);
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                System.out.println(entity.getContentType());
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

        try {
            String pricePath = "https://api.binance.com/api/v3/ticker/price?symbol=" + name + "USDT";
            URL url = new URL(pricePath);
            URLConnection yc = url.openConnection();
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            line = bufferedReader.readLine();
            bufferedReader.close();
            String[] arr;
            arr = line.split(":");
            line = arr[arr.length - 1].substring(1, arr[arr.length - 1].length() - 2);
            return true;
        } catch (Exception e) {
            System.err.println("CAN NOT GET PRICE");
            return false;
        }
    }
}