package org.example.mph.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FeedEntry(long id, Instrument instrument, BigDecimal bid, BigDecimal ask, LocalDateTime timestamp) {

    public enum Instrument {
        EUR_USD, GBP_USD, EUR_JPY;

        public String getValue() {
            return this.name().replaceAll("_", "/");
        }

        public static Instrument getInstrumentByValue(String value) {
            for (Instrument i : values()) {
                if (i.getValue().equals(value)) {
                    return i;
                }
            }
            return null;
        }

    }

}
