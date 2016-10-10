package CobSpecApp;

import server.Handler;
import server.Request;
import server.Response;

public class TeapotHandler implements Handler {
    public Response handle(Request request) {
        if (request.getPath().contains("/coffee")) {
            return new Response(418).setBody("I'm a teapot".getBytes());
        } else {
            return new Response(200);
        }
    }
}
