package org.example.mph.util;

import org.example.mph.model.FeedEntry;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public final class CSVFeedParserUtil {

    private static final String DELIMITER = " *, *";
    private static final String NEW_LINE_DELIMITER = "[\r\n]+";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

    public static Set<FeedEntry> parseCsv(String csv) {
        String[] csvEntries = csv.split(NEW_LINE_DELIMITER);
        Set<FeedEntry> entries = new HashSet<>();
        for (String s : csvEntries) {
            String[] values = s.split(DELIMITER);
            long id = Long.parseLong(values[0]);
            FeedEntry.Instrument instrument = FeedEntry.Instrument.getInstrumentByValue(values[1]);
            BigDecimal bid = new BigDecimal(values[2]);
            BigDecimal ask = new BigDecimal(values[3]);
            LocalDateTime timestamp = LocalDateTime.parse(values[4], FORMAT);
            entries.add(new FeedEntry(id, instrument, bid, ask, timestamp));
        }
        return entries;
    }

}
