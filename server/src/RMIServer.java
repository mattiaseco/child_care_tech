import Controller.*;

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
            registry.bind("parents_controller", new GenitoreDAO());
            registry.bind("pediatra_controller", new PediatraDAO());
            registry.bind("personal_controller",new PersonaleDAO());
            registry.bind("providers_controller", new FornitoreDAO());
            registry.bind("trips_controller", new GitaDAO());
            registry.bind("ingredienti_controller", new IngredientiDAO());
            registry.bind("menu_controller", new MenuDAO());
            registry.bind("pullman_controller", new PullmanDAO());
            registry.bind("piatto_controller", new PiattoDAO());
        } catch (Exception e) {
            System.err.println("[RMI]Server exception");
            e.printStackTrace();
        }
        System.out.println("[RMI]Waiting for invocations from clients");
    }
}
