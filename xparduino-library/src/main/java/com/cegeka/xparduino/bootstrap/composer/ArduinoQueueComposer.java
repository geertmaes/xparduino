package com.cegeka.xparduino.bootstrap.composer;

import com.cegeka.xparduino.bootstrap.ArduinoBootstrap;
import com.cegeka.xparduino.queue.*;
import com.cegeka.xparduino.queue.stub.StubQueue;

import static com.google.common.collect.Lists.newArrayList;

public class ArduinoQueueComposer implements Composer {

    @Override
    public void compose(ArduinoBootstrap bootstrap) {
        configureSender(bootstrap);
        configureReceiver(bootstrap);
        configureStubQueue(bootstrap);
    }

    private void configureSender(ArduinoBootstrap bootstrap) {
        ArduinoQueueSender queueSender
                = new ArduinoQueueSenderImpl(bootstrap.getQueue());
        ArduinoQueueCommandSender commandSender
                = new ArduinoQueueCommandSender(queueSender);

        bootstrap.getCommandChannel().register(commandSender);
    }

    private void configureReceiver(ArduinoBootstrap bootstrap) {
        ArduinoQueueReceiver eventReceiver
                = new ArduinoQueueEventReceiver(bootstrap.getEventMapper(), bootstrap.getEventChannel());
        ArduinoQueueReceiverHandler receiverHandler
                = new ArduinoQueueReceiverHandler(bootstrap.getQueue(), newArrayList(eventReceiver));

        receiverHandler.listenOnQueueChanges();
    }

    private void configureStubQueue(ArduinoBootstrap bootstrap) {
        ArduinoQueue queue = bootstrap.getQueue();

        if (queue instanceof StubQueue) {
            ((StubQueue) queue).setEventMapper(bootstrap.getEventMapper());
        }
    }
}
