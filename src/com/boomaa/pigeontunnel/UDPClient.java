package com.boomaa.pigeontunnel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class UDPClient extends Thread {
    private DatagramSocket socket;
    private byte[] buffer = new byte[1024];
    private SourceDataLine lineOut;

    public UDPClient() {
        try {
            socket = new DatagramSocket(5818);
            lineOut = (SourceDataLine) AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, Utils.AUDIO_FORMAT));
            lineOut.open(Utils.AUDIO_FORMAT);
            lineOut.start();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                lineOut.write(packet.getData(), 0, packet.getData().length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
