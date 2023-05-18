package org.example.mph.subscriber;

import org.example.mph.api.MarketFeedPriceController;
import org.example.mph.model.FeedEntry;
import org.example.mph.util.CSVFeedParserUtil;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class MarketPriceFeedSubscriber implements FeedSubscriber {

    private final String id;

    public MarketPriceFeedSubscriber() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public void onMessage(String message) {
        Set<FeedEntry> entries = CSVFeedParserUtil.parseCsv(message);
        Set<FeedEntry> newEntries = new HashSet<>();
        for (FeedEntry e : entries) {
            // 0.1% - 0.001
            BigDecimal margin = BigDecimal.valueOf(0.001);
            BigDecimal newBid = e.bid().subtract(e.bid().multiply(margin));
            BigDecimal newAsk = e.ask().add(e.ask().multiply(margin));
            newEntries.add(new FeedEntry(e.id(), e.instrument(), newBid, newAsk, e.timestamp()));
        }
        for (FeedEntry e : newEntries) {
            MarketFeedPriceController controller = MarketFeedPriceController.getInstance();
            controller.createFeedEntry(e);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MarketPriceFeedSubscriber o)) {
            return false;
        }
        return super.equals(o) && this.id.equals(o.id);
    }

}
