package com.cegeka.xparduino.queue.serialport;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SerialPortEventBufferTest {

    @Test
    public void append() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("<event>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void append_Scattered() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("<eve");
        buffer.append("nt>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void append_IncompleteEvent() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("<");

        assertThat(buffer.hasNext()).isFalse();
    }

    @Test
    public void append_Scattered_IncompleteEvent() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("<");
        buffer.append(".");

        assertThat(buffer.hasNext()).isFalse();
    }

    @Test
    public void append_UselessPrefix() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("prefix<event>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void append_IncompleteEventPrefix() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("event><event1>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
    }

    @Test
    public void append_Scattered_UselessPrefix() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("prefix<eve");
        buffer.append("nt>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event>");
    }

    @Test
    public void append_Multiple_Scattered_Events() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("prefix<eve");
        buffer.append("nt1><event");
        buffer.append("2>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
        assertThat(buffer.next()).isEqualTo("<event2>");
    }

    @Test
    public void append_Scattered_IncompleteEventPrefix() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("event>");
        buffer.append("<event1>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
    }

    @Test
    public void append_Multiple_Scattered_IncompleteEventPrefix() throws Exception {
        SerialPortEventBuffer buffer = new SerialPortEventBuffer();

        buffer.append("event>");
        buffer.append("<event1>");
        buffer.append("event>");
        buffer.append("<event2>");

        assertThat(buffer.hasNext()).isTrue();
        assertThat(buffer.next()).isEqualTo("<event1>");
        assertThat(buffer.next()).isEqualTo("<event2>");
    }
}