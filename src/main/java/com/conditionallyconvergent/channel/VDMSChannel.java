package com.conditionallyconvergent.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.conditionallyconvergent.utilities.InstantToUnixTimestampSerializer;
import lombok.Builder;
import lombok.Value;

import java.net.URL;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import static com.conditionallyconvergent.utilities.JsonNodeDeserializer.*;

@Value
@Builder
public class VDMSChannel {
    private static final String ID_KEY = "id";
    private static final String TITLE_KEY = "title";
    private static final String TEST_PLAYERS_KEY = "test_players";
    private static final String TEST_PLAYER_URL_KEY = "test_player_url";
    private static final String EMBED_PLAYER_URL_KEY = "embed_player_url";
    private static final String EMBED_DOMAINS_KEY = "embed_domains";
    private static final String EXTERNAL_ID_KEY = "external_id";
    private static final String LIVE_DELAY_KEY = "live_delay";
    private static final String DELETED_KEY = "deleted";
    private static final String REQUIRE_DRM_KEY = "require_drm";
    private static final String REQUIRE_STUDIO_DRM_KEY = "require_studio_drm";
    private static final String SLICER_ID_KEY = "slicer_id";
    private static final String SLICED_OWNER_KEY = "slicer_owner_key";
    private static final String SLATE_KEY = "slate";
    private static final String RESOLVED_SLATES_KEY = "resolved_slates";
    private static final String OVERRIDE_SLICER_ID_KEY = "override_slicer_id";
    private static final String OVERRIDE_SLICED_OWNER_KEY = "override_slicer_owner_key";
    private static final String ASSET_AUTOEXPIRE_HOURS_KEY = "asset_autoexpire_hours";
    private static final String THUMB_URL_KEY = "thumb_url";
    private static final String HAS_SLICER_KEY = "has_slicer";
    private static final String METADATA_KEY = "meta";
    private static final String CREATED_KEY = "created";
    private static final String LAST_MOD_KEY = "lastmod";

    @JsonProperty(ID_KEY) String id;
    @JsonProperty(TITLE_KEY) String title;
    @JsonProperty(TEST_PLAYERS_KEY) List<VDMSTestPlayer> testPlayers;
    @JsonProperty(TEST_PLAYER_URL_KEY) URL testPlayerUrl;
    @JsonProperty(EMBED_PLAYER_URL_KEY) URL embedPlayerUrl;
    @JsonProperty(EMBED_DOMAINS_KEY) String embedDomains;
    @JsonProperty(EXTERNAL_ID_KEY) String externalId;
    @JsonProperty(LIVE_DELAY_KEY) long liveDelay;
    @JsonProperty(DELETED_KEY) Boolean deleted;
    @JsonProperty(REQUIRE_DRM_KEY) Boolean requireDRM;
    @JsonProperty(REQUIRE_STUDIO_DRM_KEY) Boolean requireStudioDRM;
    @JsonProperty(SLICER_ID_KEY) String slicerId;
    @JsonProperty(SLICED_OWNER_KEY) String slicerOwner;
    @JsonProperty(SLATE_KEY) String slate;
    @JsonProperty(RESOLVED_SLATES_KEY) VDMSResolvedSlates resolvedSlates;
    @JsonProperty(OVERRIDE_SLICER_ID_KEY) String overrideSlicerId;
    @JsonProperty(OVERRIDE_SLICED_OWNER_KEY) String overrideSlicerOwner;
    @JsonProperty(ASSET_AUTOEXPIRE_HOURS_KEY) long assetAutoexpireHours;
    @JsonProperty(THUMB_URL_KEY) URL thumbUrl;
    @JsonProperty(HAS_SLICER_KEY) Boolean hasSlicer;
    @JsonProperty(METADATA_KEY) Map<String, String> metadata;

    @JsonProperty(CREATED_KEY)
    @JsonSerialize(using = InstantToUnixTimestampSerializer.class)
    Instant created;

    @JsonProperty(LAST_MOD_KEY)
    @JsonSerialize(using = InstantToUnixTimestampSerializer.class)
    Instant lastMod;

    public static VDMSChannel deserialize(JsonNode node) {
        return VDMSChannel.builder()
            .id(stringValue(node.get(ID_KEY)))
            .title(stringValue(node.get(TITLE_KEY)))
            .testPlayers(listValue(node.get(TEST_PLAYERS_KEY), VDMSTestPlayer::deserialize))
            .testPlayerUrl(urlValue(node.get(TEST_PLAYER_URL_KEY)))
            .embedPlayerUrl(urlValue(node.get(EMBED_PLAYER_URL_KEY)))
            .externalId(stringValue(node.get(EXTERNAL_ID_KEY)))
            .liveDelay(longValue(node.get(LIVE_DELAY_KEY)))
            .deleted(booleanValue(node.get(DELETED_KEY)))
            .requireDRM(booleanValue(node.get(REQUIRE_DRM_KEY)))
            .requireStudioDRM(booleanValue(node.get(REQUIRE_STUDIO_DRM_KEY)))
            .slicerId(stringValue(node.get(SLICER_ID_KEY)))
            .slicerOwner(stringValue(node.get(SLICED_OWNER_KEY)))
            .slate(stringValue(node.get(SLATE_KEY)))
            .resolvedSlates(VDMSResolvedSlates.deserialize(node.findValue(RESOLVED_SLATES_KEY)))
            .overrideSlicerId(stringValue(node.get(OVERRIDE_SLICER_ID_KEY)))
            .overrideSlicerOwner(stringValue(node.get(OVERRIDE_SLICED_OWNER_KEY)))
            .assetAutoexpireHours(longValue(node.get(ASSET_AUTOEXPIRE_HOURS_KEY)))
            .thumbUrl(urlValue(node.get(THUMB_URL_KEY)))
            .hasSlicer(booleanValue(node.get(HAS_SLICER_KEY)))
            .metadata(toMap(node.get(METADATA_KEY)))
            .created(Instant.ofEpochSecond(node.get(CREATED_KEY).longValue()))
            .lastMod(Instant.ofEpochSecond(node.get(LAST_MOD_KEY).longValue()))
            .build();
    }
}
