package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Medicine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedicineService {

    private static final String URL_STRING = ("http://localhost:8080/pharmacy");

    public List<Medicine> getAll() throws IOException {

        List<Medicine> medicineList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            HttpURLConnection conn = this.getConnection("GET");

            if (!isResponseSuccessful(conn.getResponseCode())) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            String jsonResponse = getJsonResponse(conn);
            medicineList = Arrays.asList(objectMapper.readValue(jsonResponse, Medicine[].class));

        } catch (IOException e) {
            e.printStackTrace();

        }
        return medicineList;
    }

    public String getJsonResponse(HttpURLConnection conn) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public boolean isResponseSuccessful(int responseCode) {
        return responseCode == 200;
    }

    public HttpURLConnection getConnection(String method) throws IOException {
        URL url = new URL(URL_STRING);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setRequestProperty("Accept", "application/json");
        return conn;
    }

}
