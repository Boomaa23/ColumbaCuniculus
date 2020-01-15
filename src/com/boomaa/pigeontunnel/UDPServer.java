package com.boomaa.pigeontunnel;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Mixer.Info;
import javax.sound.sampled.TargetDataLine;

public class UDPServer extends Thread {
    private DatagramSocket socket;
    private InetAddress ip;
    private TargetDataLine lineIn;
    private int bytesRead;
	private ByteArrayOutputStream out;
	private byte[] data;

    public UDPServer(String ip) {
        try {
        	this.ip = InetAddress.getByName(ip);
        	socket = new DatagramSocket();
        	Info[] infos = AudioSystem.getMixerInfo();
        	for(int i = 0;i < infos.length;i++) {
        		System.out.println(infos[i].getName());
        	}
        	lineIn = (TargetDataLine) AudioSystem.getMixer(infos[0]).getLine(new DataLine.Info(TargetDataLine.class, Utils.AUDIO_FORMAT));
    		lineIn.open(Utils.AUDIO_FORMAT);
    		
    		out = new ByteArrayOutputStream();
    		data = new byte[lineIn.getBufferSize() / 5];
    		lineIn.start();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            while (true) {
    			bytesRead = lineIn.read(data, 0, 1024);
    			out.write(data, 0, bytesRead);
    			DatagramPacket request = new DatagramPacket(data, bytesRead, ip, 5818);
    			socket.send(request);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
