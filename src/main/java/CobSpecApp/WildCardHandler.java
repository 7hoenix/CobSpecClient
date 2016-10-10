package CobSpecApp;

import server.Handler;
import server.Request;
import server.Response;

public class WildCardHandler implements Handler {
    public Response handle(Request request) {
        return new Response(404)
                .setHeader("Content-Type", "text/html")
                .setBody(("<!DOCTYPE html><html lang=\"en\"><body>" +
                        "<img src=\"https://media.giphy.com/media/7yTqXVALy7Fwk/giphy.gif\">" +
                        "</body></html>").getBytes());
    }
}
