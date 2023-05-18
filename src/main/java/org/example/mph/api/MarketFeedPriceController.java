package org.example.mph.api;

import org.example.mph.model.FeedEntry;
import org.example.mph.util.JsonFeedParserUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * This is test HTTP Controller that publish data to plural endpoints for market feed.
 */
public class MarketFeedPriceController {

    private static final String TEST_TOKEN = "=1234dsaJKLY231Lsda";
    private static final String LATEST_PRICE_ENDPOINT = "http://localhost:8080/rest/v1/feed?price=latest";
    private static final String POST_REQUEST = """
            curl -X POST "%s" \\
                 -H "Content-Type: application/json" \\
                 -H "Authorization: Bearer %s" \\
                 -d '%s'""";

    /**
     * Let imagine this is database in form of JVM memory
     */
    private final Set<FeedEntry> entries;

    private static MarketFeedPriceController instance;

    private MarketFeedPriceController() {
        this.entries = new HashSet<>();
    }

    /**
     * It's singleton, like default Spring scope for @RestController
     * @return new instance or existing one
     */
    public static MarketFeedPriceController getInstance() {
        if (instance == null) {
            instance = new MarketFeedPriceController();
        }
        return instance;
    }

    /**
     * Performs CURL requests to test server on local host to publish the latest prices and save to "database"
     * @param entry body of request
     */
    public void createFeedEntry(FeedEntry entry) {
        System.out.println("[LOG] POST NEW FEED ENTRY");
        System.out.println("Response: " + sendCreateFeedEntry(LATEST_PRICE_ENDPOINT, TEST_TOKEN,
                JsonFeedParserUtil.parseToJson(Set.of(entry))));
        this.entries.add(entry);
    }

    public Set<FeedEntry> getLatestPrices() {
        System.out.println("[LOG] GET LATEST PRICES FOR FEED");
        return this.entries;
    }

    /**
     * Let assume this is POST method for web server with authentication
     * @param endpoint endpoint
     * @param body request body
     * @return response from server
     */
    private String sendCreateFeedEntry(String endpoint, String token, String body) {
        System.out.printf((POST_REQUEST) + "%n", endpoint, token, body);
        return "{ \"success\": true }";
    }

}
