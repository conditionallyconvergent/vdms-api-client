package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.SneakyThrows;
import lombok.Value;

import java.net.URL;

import static com.conditionallyconvergent.utilities.JsonNodeDeserializer.*;

@Value
@Builder
public class VDMSTestPlayer {
    @JsonProperty("desc") String desc;
    @JsonProperty("id") String id;
    @JsonProperty("url") URL url;
    @JsonProperty("url_html5") URL urlHtml5;
    @JsonProperty("expire") String expire;
    @JsonProperty("params") String params;
    @JsonProperty("whitelist") Boolean whitelist;

    @SneakyThrows
    public static VDMSTestPlayer deserialize(JsonNode node) {
        return VDMSTestPlayer.builder()
            .desc(stringValue(node.get("desc")))
            .id(stringValue(node.get("id")))
            .url(urlValue(node.get("url")))
            .urlHtml5(urlValue(node.get("url_html5")))
            .expire(stringValue(node.get("expire")))
            .params(stringValue(node.get("params")))
            .whitelist(booleanValue(node.get("whitelist")))
            .build();
    }
}
