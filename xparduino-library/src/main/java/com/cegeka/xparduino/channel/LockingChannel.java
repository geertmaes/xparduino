package com.cegeka.xparduino.channel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class LockingChannel<T> implements Channel<T> {

    private static final int QUEUE_CAPACITY = 1024;

    public static <T> LockingChannel<T> locked(Channel<T> delegate) {
        return new LockingChannel<>(delegate, true);
    }

    private boolean locked;
    private final Channel<T> delegate;
    private final BlockingQueue<T> messageQueue;

    private LockingChannel(Channel<T> delegate, boolean locked) {
        this.locked = locked;
        this.delegate = delegate;
        this.messageQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY, true);
    }

    public void release() {
        this.locked = false;
        while (messageQueue.size() > 0) {
            send(messageQueue.poll());
        }
    }

    @Override
    public void send(T message) {
        if (this.locked) {
            addMessageToQueue(message);
        } else {
            delegate.send(message);
        }
    }

    private void addMessageToQueue(T message) {
        try {
            messageQueue.put(message);
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to add message to queue", e);
        }
    }

    @Override
    public void register(ChannelListener<T> listener) {
        delegate.register(listener);
    }

}
