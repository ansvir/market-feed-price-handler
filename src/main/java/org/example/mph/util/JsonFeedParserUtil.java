package org.example.mph.util;

import org.example.mph.model.FeedEntry;

import java.util.Set;

public class JsonFeedParserUtil {

    public static String parseToJson(Set<FeedEntry> entries) {
        StringBuilder json = new StringBuilder("[\n\t");
        for (FeedEntry e : entries) {
            json.append("{\n");
            json.append(buildNumberPair("id", e.id()));
            json.append(buildStringPair("instrument", e.instrument().name()));
            json.append(buildNumberPair("bid", e.bid()));
            json.append(buildNumberPair("ask", e.ask()));
            json.append(buildStringPair("timestamp", e.timestamp()));
            json = new StringBuilder(json.substring(0, json.length() - 2));
            json.append("\n\t},\n");
        }
        json = new StringBuilder(json.substring(0, json.length() - 2));
        json.append("\n]");
        return json.toString();
    }

    private static String buildStringPair(String fieldName, Object o) {
        return "\t\t\"" +
                fieldName +
                "\": \"" +
                o.toString() +
                "\",\n";
    }

    private static String buildNumberPair(String fieldName, Object o) {
        return "\t\t\"" +
                fieldName +
                "\": " +
                o.toString() +
                ",\n";
    }

}
