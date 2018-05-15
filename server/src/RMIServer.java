import Controller.BambinoDAO;
import Controller.LoginController;
import Controller.RegisterController;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public RMIServer() {}

    public void start() {
        try {
            System.out.println("[RMI]Binding server implementation to registry");
            Registry registry= LocateRegistry.createRegistry(1099);

            registry.bind("login_controller", new LoginController());
            registry.bind("register_controller", new RegisterController());
            registry.bind("kid_controller", new BambinoDAO());
        } catch (Exception e) {
            System.err.println("[RMI]Server exception");
            e.printStackTrace();
        }
        System.out.println("[RMI]Waiting for invocations from clients");
    }
}
