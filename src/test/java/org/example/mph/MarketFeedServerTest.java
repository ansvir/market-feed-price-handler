package org.example.mph;

import org.example.mph.api.MarketFeedPriceController;
import org.example.mph.model.FeedEntry;
import org.example.mph.publisher.FeedPublisher;
import org.example.mph.subscriber.FeedSubscriber;
import org.example.mph.subscriber.MarketPriceFeedSubscriber;
import org.junit.Test;

/**
 * This class represents server that publish messages to our application.
 */
public class MarketFeedServerTest {

    /**
     * Let's imagine this is not a test, but real data source
     */
    @Test
    public void testSendMarketPriceFeeds() {
        String messageOne = """
                106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001
                107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002
                108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002
                109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100
                110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110""";

        String messageTwo = """
                111, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001
                112, GBP/USD, 1.2501,1.2559,01-06-2020 12:01:02:002
                113, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:004""";

        FeedSubscriber feedSubscriber = new MarketPriceFeedSubscriber();
        FeedSubscriber feedSubscriberTwo = new MarketPriceFeedSubscriber();

        FeedPublisher publisher = new FeedPublisher();
        publisher.subscribe(feedSubscriber);
        publisher.subscribe(feedSubscriberTwo);

        publisher.publish(messageOne);
        publisher.publish(messageTwo);

        MarketFeedPriceController controller = MarketFeedPriceController.getInstance();
        for (FeedEntry e : controller.getLatestPrices()) {
            System.out.println(e);
        }
    }
}
