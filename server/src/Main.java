
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String [] args) throws SQLException, ClassNotFoundException {
        Database.avviaDatabase();

        RMIServer rmiServer = new RMIServer();
        rmiServer.start();

        /*SocketServer server = new SocketServer(1337);
        try {
            server.startServer();
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }*/


    }
}