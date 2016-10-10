package CobSpecApp;

import server.ApplicationBuilder;
import server.ConnectionManager;
import server.Handler;
import server.middlewares.WrapBasicAuth;
import server.middlewares.WrapRequestLog;
import server.middlewares.WrapServeStaticFiles;
import server.Repository;
import server.Router;
import server.Server;
import server.WrappedServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class CobSpecClient {
    private final Server server;

    public static void main(String[] args) throws IOException {
        Settings settings = new Settings(args);
        Repository dataStore = new DataStore();
        ArrayList<String> log = new ArrayList<>();
        Handler application = ApplicationBuilder.setHandler(
                CobSpecRoutes.generate(new Router(), dataStore))
                .use(new WrapRequestLog()
                        .setLog(log))
                .use(new WrapBasicAuth()
                        .setUserName("admin")
                        .setPassword("hunter2")
                        .setRealm("jphoenx personal server")
                        .setProtectedRoutes(new String[] {"/logs"}))
                .use(new WrapServeStaticFiles()
                        .setPublicDirectory(settings.getRoot())
                        .setAutoIndex(settings.getAutoIndex()))
                .build();
        ServerSocket serverSocket = new ServerSocket(settings.getPort());
        ConnectionManager serverConnection = new WrappedServerSocket(serverSocket, application);
        Server server = new Server(serverConnection);
        CobSpecClient client = new CobSpecClient(server);
        client.run();
    }

    public CobSpecClient(Server server) {
        this.server = server;
    }

    public void run() throws IOException {
        server.run();
    }
}
