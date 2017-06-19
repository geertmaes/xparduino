package com.cegeka.xpdays.arduino.channel.serialport;

import java.util.LinkedList;
import java.util.Queue;

class EventBuffer {

    private static final String EVENT_PREFIX = "<";
    private static final String EVENT_SUFFIX = ">";

    private final Queue<String> events = new LinkedList<>();
    private final StringBuilder buffer = new StringBuilder();

    void append(String payload) {
        int payloadPrefixIndex = payload.indexOf(EVENT_PREFIX);
        int payloadSuffixIndex = payload.indexOf(EVENT_SUFFIX);

        if (payloadPrefixIndex > 0 && (payloadSuffixIndex == -1 || payloadSuffixIndex > payloadPrefixIndex)) {
            buffer.append(payload.substring(payloadPrefixIndex, payload.length()));
        } else {
            buffer.append(payload);
        }

        int prefixIndex = buffer.indexOf(EVENT_PREFIX);
        int suffixIndex = buffer.indexOf(EVENT_SUFFIX);

        if (suffixIndex >= 0 && prefixIndex > suffixIndex) {
            buffer.delete(0, suffixIndex + 1);
        }

        prefixIndex = buffer.indexOf(EVENT_PREFIX);
        suffixIndex = buffer.indexOf(EVENT_SUFFIX);

        while(prefixIndex == 0 && suffixIndex > 0) {
            events.add(buffer.substring(prefixIndex, suffixIndex + 1));
            buffer.delete(prefixIndex, suffixIndex + 1);

            prefixIndex = buffer.indexOf(EVENT_PREFIX);
            suffixIndex = buffer.indexOf(EVENT_SUFFIX);
        }
    }

    String next() {
        return events.remove();
    }

    boolean hasNext() {
        return !events.isEmpty();
    }
}