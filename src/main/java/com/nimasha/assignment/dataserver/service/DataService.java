package com.ragul.assignment.dataserver.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ragul.assignment.dataserver.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class DataService {
    @Autowired
    StateService stateService;

    public void getDataFromFile() throws InterruptedException {
        // read JSON and load json
        try {
            URL url = new URL("https://gist.githubusercontent.com/mshafrir/2646763/raw/8b0dbb93521f5d6889502305335104218454c2bf/states_titlecase.json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<State>> typeReference = new TypeReference<List<State>>() {
            };

            List<State> stateData = mapper.readValue(inputStream, typeReference);
            this.stateService.save(stateData);
            System.out.println("stateData Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save states: " + e.getMessage());
            TimeUnit.SECONDS.sleep(2);
            this.getDataFromFile();

        }
    }
}
