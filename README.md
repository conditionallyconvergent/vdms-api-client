# VDMS Api Client

Java client library for consuming the Verizon Digital Media Services (formerly Uplynk) streaming API.

## Goals

- Comprehensive support for the Verizon Digital Media Services API.
- Minimal dependencies. Currently the main source depends on only three libraries:
    - [Lombok](https://projectlombok.org) (for ease of request & response building)
    - [Jackson Databind](https://github.com/FasterXML/jackson-databind) (for JSON serialization/deserialization)
    - [Commons Codec](http://commons.apache.org/proper/commons-codec/) (for HMAC encryption as required by the API)
- Broad compatibility. `VDMSApiClient` is written in Java 8, so can it be included in most current Java & Kotlin applications.
- Full test coverage.
- Robust error handling. `VDMSApiClient` aims to catch errors in requests *before* they are sent.

## Usage

There are two ways you can use this library:

1. Using a higher-level `VDMSApiClient`, or
2. Using the lower-level `VDMSApiGateway` (which is what each `VDMSApiClient` uses itself).

Using a `VDMSApiClient` offers several advantages over the `VDMSApiGateway`, including:
- type safety
- clear signatures for requests and responses
- built-in JSON serialization/deserialization
- request validation

The disadvantage to using a `VDMSApiClient` is that you're limited to the APIs it supports. While this library strives to offer comprehensive support of the full range of VDMS streaming APIs, it only supports a small fraction of them at the current time.

The `VDMSApiGateway` allows you to consume any and all of the published VDMS streaming APIs. However, if you use it, you are responsible for handling request validation, JSON serialization/deseriazation, response error handling, etc.

### Account credentials

Whether you use `VDMSApiClient` or `VDMSApiGateway`, you will need to supply three pieces of data:
- `ROOT_URL`: The root url of the VDMS API, currently `https://services.uplynk.com`
- `OWNER`: Your account User ID. You can find this on the [VDMS Settings page](https://cms.uplynk.com/static/cms2/index.html#/settings).
- `SECRET`: Your account API Key. You can find this on the [VDMS Integration Keys page](https://cms.uplynk.com/static/cms2/index.html#/settings/integration-keys).

### VDMSApiClient

Instantiating a new instance:

```java
VDMSChannelApiClient channelApiClient = new VDMSChannelApiClient(ROOT_URL, OWNER, SECRET);
```

For a full list of features, consult the tests; here are a few examples of things you can do with `VDMSChannelApiClient`:

- Listing all channels in your account:
    ```java
    VDMSListChannelsResponse response = channelApiClient.listChannels();
    List<VDMSChannel> channels = response.getChannels();
    ```
- Creating a new channel:
    ```java
    VDMSCreateChannelRequest request = VDMSCreateChannelRequest.builder()
        .title("A new channel")
        .build();
    VDMSCreateChannelResponse response = channelApiClient.createChannel(request);
    
    if (response.getError() == 0) {
        VDMSChannel channel = response.getChannel();
    } else {
        // handle error
    }
    ```
- Deleting a channel:
    ```java
    VDMSChannel channel; // a channel you want to delete
    
    VDMSDeleteChannelRequest request = VDMSDeleteChannelRequest.builder()
        .id(channel.getId())
        .build();
    VDMSDeleteChannelResponse response = channelApiClient.deleteChannel(request);
  
    if (response.getError() != 0) {
        // handle error
    }
    ```

Notice that `VDMSApiClient` is built around a **request/response** pattern. Each API method takes in a request object of certain shape and returns a response object of a certain shape.

### VDMSApiGateway

Instantiating a new instance:

```java
VDMSApiGateway apiGateway = new VDMSApiGateway(ROOT_URL, OWNER, SECRET);
```

Here are the same actions described above, but using `VDMSApiGateway` instead:

- Listing all channels in your account:
    ```java
    String response = apiGateway.call("/api2/channel/list");

    /*
    The response comes back as a string, which you'll have to parse manually (or use a JSON parser like Jackson).
    It looks something like this:
        {
             "channels": [
                    {
                        "title": "Live test dave 2",
                        "id": "7a35c56ea4014856bc74c0a375236cc5",
                        "external_id": "bif",
                        ...
                    }
                    {
                        "title": "audio-only live test",
                        "id": "c9f7f12f44abdfd48e2bb1a326877cd9",
                        "external_id": "",
                        ...
                    }
             ],
             "error": 0
        }
    */
    ```
- Creating a new channel:
    ```java
    Map<String, Object> params = new HashMap<String, Object>() {{
        put("desc", "New Title");
        put("slicer_id", "my_slicer");
        put("meta", "{\"key1\": \"value1\"}");
    }};
    String response = vdmsApiGateway.call("/api2/channel/create", params);

    /*
    The response comes back as a string, which you'll have to parse manually (or use a JSON parser like Jackson).
    It looks something like this:
        {
            "test_player_url": "https://content.uplynk[-z].com/player5/Hak3zjnPLSW5o0j8GMpzRMsa.html",
            "test_players": [
                {"desc": "Monitoring Test Player",
                 "id": "Hak3zjnPLSW5o0j8GMpzRMsa",
                 "url": "https://content.uplynk[-z].com/player5/Hak3zjnPLSW5o0j8GMpzRMsa.html"},
                {"desc": "Affiliate Test Player",
                 "id": "3fqeYp0yrG5Pk4bDqazn79",
                 "url": "https://content.uplynk[-z].com/player5/3fqeYp0yrG5Pk4bDqazn79sa.html"}],
            "embed_player_url": "https://content.uplynk[-z].com/player5/1wEDDmfjafWGeCtDfm5L5s2SD26A1YDRJDy10r7pSCc4EikDu.html",
        	"meta": {"key1": "value1"},
            "require_drm": 1,
            "require_studio_drm": 1,
            "slicer_id": "my_slicer",
            "slicer_owner": "ea8b4debcf0d438ea44a34e0febf8a50",
            "title": "New Title",
            "live_delay": 10,
            "has_slicer": false,
            "created": 1318976590877,
            "id": "5915d84829405cb4db1bc3f71c10fc83",
            "deleted": 0,
            "embed_domains": "",
            "thumb_url": "",
            "external_id": "test_chan",
            "asset_autoexpire_hours": 24
        }    */
    ```
- Deleting a channel:
    ```java
    List<String> ids = Collections.singletonList("0a671113bebb4192bf679db9ee146051"); // a list of ids of channels to be deleted
    List<String> externalIds = Arrays.asList("channel1", "channel2"); // a list of external ids of channels to be deleted
  
    Map<String, Object> params = new HashMap<String, Object>() {{
        put("ids", ids);
        put("external_ids", externalIds);
    }};
    String response = vdmsApiGateway.call("/api2/channel/delete", params);

    /*
    The response comes back as a string, which you'll have to parse manually (or use a JSON parser like Jackson).
    It looks something like this:
        {
            "deleted": [
                {
                    "external_id": "",
                    "id": "0a671113bebb4192bf679db9ee146051"
                },
                {
                    "external_id": "channel1",
                    "id": "54e40044a5f84050a483a5ce91cedab1"
                },
                {
                    "external_id": "channel2",
                    "id": "9c4e00aab263489aa7fd275a0c5cc478"
                }
            ],
            "error": 0
        }
    */
    ```

## Contributing

Clone the repository:

```shell script
$ git clone https://github.com/alexbasson/vdms-api-client.git
$ cd vdms-api-client
```

Run the tests:

```shell script
$ ./mvnw clean test
```

Pushing new code:

```shell script
$ ./scripts/ship-it.sh
```
