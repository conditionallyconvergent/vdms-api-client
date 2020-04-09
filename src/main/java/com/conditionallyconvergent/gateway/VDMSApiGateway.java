package com.conditionallyconvergent.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.conditionallyconvergent.gateway.Compressor.zip;
import static com.conditionallyconvergent.gateway.ConnectionHelper.*;

public class VDMSApiGateway {
    private final String rootUrl;
    private final String owner;

    private final HmacUtils hmacUtils;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Base64.Encoder base64Encoder = Base64.getEncoder();

    public VDMSApiGateway(String rootUrl, String owner, String secret) {
        this.rootUrl = rootUrl;
        this.owner = owner;
        this.hmacUtils = new HmacUtils(HmacAlgorithms.HMAC_SHA_256, secret);
    }

    public String call(String url, Map<String, Object> params) throws IOException {
        HttpURLConnection connection = buildConnection(new URL(rootUrl + url), "POST");

        String message = buildMessage(params);
        String signature = buildSignature(message);

        Map<String, String> body = buildBody(message, signature);
        setBodyOnConnection(connection, body);

        return readResponse(connection);
    }

    public String call(String url) throws IOException {
        HttpURLConnection connection = buildConnection(new URL(rootUrl + url), "GET");

        String message = buildMessage();
        String signature = buildSignature(message);

        Map<String, String> body = buildBody(message, signature);
        setBodyOnConnection(connection, body);

        return readResponse(connection);
    }

    private String buildMessage(Map<String, Object> params) throws IOException {
        Map<String, Object> message = new HashMap<>(params);
        message.putAll(defaultParams());
        return encodeMessage(message);
    }

    private String buildMessage() throws IOException {
        return encodeMessage(defaultParams());
    }

    private String encodeMessage(Map<String, Object> message) throws IOException {
        String jsonString = objectMapper.writeValueAsString(message);
        return base64Encoder.encodeToString(zip(jsonString)).trim();
    }

    private String buildSignature(String base64EncodedMsg) {
        return hmacUtils.hmacHex(base64EncodedMsg);
    }

    private Map<String, String> buildBody(String base64EncodedMessage, String signature) {
        return new HashMap<String, String>() {{
            put("msg", base64EncodedMessage);
            put("sig", signature);
        }};
    }

    private Map<String, Object> defaultParams() {
        long timestamp = System.currentTimeMillis() / 1000L;
        return new HashMap<String, Object>() {{
            put("_owner", owner);
            put("_timestamp", timestamp);
        }};
    }
}
