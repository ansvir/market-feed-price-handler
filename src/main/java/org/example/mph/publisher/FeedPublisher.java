package org.example.mph.publisher;

import org.example.mph.subscriber.FeedSubscriber;

import java.util.HashSet;
import java.util.Set;

public class FeedPublisher {

    private final Set<FeedSubscriber> subscribers;

    public FeedPublisher() {
        this.subscribers = new HashSet<>();
    }

    public void publish(String message) {
        for (FeedSubscriber s : this.subscribers) {
            s.onMessage(message);
        }
    }

    public void subscribe(FeedSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(FeedSubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

}
