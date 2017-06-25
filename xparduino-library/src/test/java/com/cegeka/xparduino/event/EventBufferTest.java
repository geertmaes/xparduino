package com.cegeka.xparduino.event;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventBufferTest {

    @Test
    public void append() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("<event>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void append_Scattered() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("<eve");
        buffer.append("nt>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void append_IncompleteEvent() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("<");

        assertThat(buffer.hasNext()).isFalse();
    }

    @Test
    public void append_Scattered_IncompleteEvent() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("<");
        buffer.append(".");

        assertThat(buffer.hasNext()).isFalse();
    }

    @Test
    public void append_UselessPrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("prefix<event>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void append_IncompleteEventPrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("event><event1>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
    }

    @Test
    public void append_Scattered_UselessPrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("prefix<eve");
        buffer.append("nt>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void append_Multiple_Scattered_Events() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("prefix<eve");
        buffer.append("nt1><event");
        buffer.append("2>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
        assertThat(buffer.next()).isEqualTo("<event2>");
    }

    @Test
    public void append_Scattered_IncompleteEventPrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("event>");
        buffer.append("<event1>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
    }

    @Test
    public void append_Multiple_Scattered_IncompleteEventPrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.append("event>");
        buffer.append("<event1>");
        buffer.append("event>");
        buffer.append("<event2>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
        assertThat(buffer.next()).isEqualTo("<event2>");
    }
}