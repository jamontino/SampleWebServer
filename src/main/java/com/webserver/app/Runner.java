package com.webserver.app;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Runner {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/";
    public static final String staticHttpPath = "src/main/resources/statichttp/";

    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("com.webserver.app");
        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        httpServer.getServerConfiguration().addHttpHandler(new StaticHttpHandler(staticHttpPath));
        return httpServer;
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        System.out.println(String.format("Jersey app started with WADL available at "
            + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}
