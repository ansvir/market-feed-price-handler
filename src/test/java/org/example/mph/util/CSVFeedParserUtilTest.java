package org.example.mph.util;

import org.example.mph.model.FeedEntry;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class CSVFeedParserUtilTest {

    @Test
    public void testParseCsv() {

        // given
        String csv = """
                106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001
                107, EUR/JPY, 119.60,119.90,01-06-2020 12:01:02:002
                108, GBP/USD, 1.2500,1.2560,01-06-2020 12:01:02:002
                109, GBP/USD, 1.2499,1.2561,01-06-2020 12:01:02:100
                110, EUR/JPY, 119.61,119.91,01-06-2020 12:01:02:110""";

        // when
        Set<FeedEntry> entries = CSVFeedParserUtil.parseCsv(csv);

        // then
        for (FeedEntry e : entries) {
            assertEquals(e.ask().compareTo(e.bid()), 1);
        }

    }
}
