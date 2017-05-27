package com.cegeka.xpdays.arduino.monitor;

public class BufferedMessageListener implements MessageListener {

    private String bufferedMessage = "";

    @Override
    public void onMessage(String message) {
        bufferedMessage += message;

        if (bufferedMessage.startsWith("<") && bufferedMessage.indexOf(">") > 0) {
            System.out.println(bufferedMessage);
            bufferedMessage = "";
        }
    }
}
