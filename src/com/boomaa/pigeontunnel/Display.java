package com.boomaa.pigeontunnel;

public class Display {
	public static void main(String[] args) throws Exception {
		if(args.length == 2 && args[0].toLowerCase().equals("server")) {
			server(args[1]);
		} else if(args.length == 1 && args[0].toLowerCase().equals("client")) {
			client();
		} else {
			System.err.println("Failed to start: Server or client not specified or invalid");
		}
	}
	
	public static void server(String ip) {
		UDPServer udpServer = new UDPServer(ip);
		udpServer.start();
	}
	
	public static void client() {
		UDPClient udpClient = new UDPClient();
		udpClient.start();
	}
}
