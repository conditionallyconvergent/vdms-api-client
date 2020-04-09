package com.conditionallyconvergent.gateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

class ConnectionHelper {

    static HttpURLConnection buildConnection(URL url, String method) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        return connection;
    }

    static void setBodyOnConnection(HttpURLConnection connection, Map<String, String> body) throws IOException {
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = ParameterStringBuilder.getParamsString(body).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
    }

    static String readResponse(HttpURLConnection connection) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
        try(BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = bufferedReader.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }
    }
}
