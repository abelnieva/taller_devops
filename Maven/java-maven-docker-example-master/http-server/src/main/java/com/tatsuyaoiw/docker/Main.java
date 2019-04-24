package com.tatsuyaoiw.docker;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class Main {
	public static void main(String[] args) {
		try {
			int port = 9999;
			HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/", new HttpHandler() {
				public void handle(HttpExchange httpExchange) throws IOException {
					String body = "Hello world!";
					byte[] bytes = body.getBytes(Charset.forName("UTF-8"));
					httpExchange.sendResponseHeaders(200, bytes.length);
					OutputStream os = httpExchange.getResponseBody();
					os.write(bytes);
					os.close();
				}
			});
			server.start();
			System.out.println("App listening on port " + port + "...");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
