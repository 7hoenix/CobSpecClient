package CobSpecApp;

import server.Handler;
import server.Request;
import server.Response;

public class OptionsHandler implements Handler {
    public Response handle(Request request) {
        return new Response(200);
    }
}
