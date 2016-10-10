package CobSpecApp;

import server.Handler;
import server.Request;
import server.Response;

public class ErrorHandler implements Handler {
    private int statusCode;

    public ErrorHandler(int statusCode) {
        this.statusCode = statusCode;
    }

    public Response handle(Request request) {
        return new Response(statusCode);
    }
}
