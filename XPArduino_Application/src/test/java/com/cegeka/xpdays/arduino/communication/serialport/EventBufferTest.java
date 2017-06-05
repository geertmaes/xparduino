package com.cegeka.xpdays.arduino.communication.serialport;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventBufferTest {

    @Test
    public void addPayload() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("<event>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void addPayload_Scattered() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("<eve");
        buffer.add("nt>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void addPayload_EventIncomplete() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("<");

        assertThat(buffer.hasNext()).isFalse();
    }

    @Test
    public void addPayload_Scattered_EventIncomplete() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("<");
        buffer.add(".");

        assertThat(buffer.hasNext()).isFalse();
    }

    @Test
    public void addPayload_UselessPrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("prefix<event>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void addPayload_Scattered_UselessPrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("prefix<eve");
        buffer.add("nt>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void addPayload_Scattered_StartingNewEvent() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("prefix<eve");
        buffer.add("nt1><event2>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
        assertThat(buffer.next()).isEqualTo("<event2>");
    }

    @Test
    public void addPayload_EventIncompletePrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("event><event1>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
    }

    @Test
    public void addPayload_Scattered_EventIncompletePrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("event>");
        buffer.add("<event1>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
    }

    @Test
    public void addPayload_Scattered_Multiple_EventIncompletePrefix() throws Exception {
        EventBuffer buffer = new EventBuffer();

        buffer.add("event>");
        buffer.add("<event1>");
        buffer.add("event>");
        buffer.add("<event2>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
        assertThat(buffer.next()).isEqualTo("<event2>");
    }
}