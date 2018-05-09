import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public RMIServer() {}

    public void start() {
        try {
            System.out.println("[RMI]Binding server implementation to registry");
            Registry registry= LocateRegistry.createRegistry(1099);

            registry.bind("login_controller", new LoginController());
            // qua aggiungi altri oggetti così
            // ESEMPIO:
            // registry.bind("bambino_dao", new BambinoDAO());
        } catch (Exception e) {
            System.err.println("[RMI]Server exception");
            e.printStackTrace();
        }
        System.out.println("[RMI]Waiting for invocations from clients");
    }
}
