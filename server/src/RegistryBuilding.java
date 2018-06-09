import Controller.*;
import common.Classes.*;
import common.Interface.iLoginController;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryBuilding {

    public RegistryBuilding() {}


    public static LoginController loginController;
    public static RegisterController registerController;
    public static BambinoDAO kidController;
    public static GenitoreDAO parentsController;
    public static PediatraDAO pediatraController;
    public static PersonaleDAO personalController;
    public static FornitoreDAO providersController;
    public static GitaDAO tripsController;
    public static IngredientiDAO ingredientiController;
    public static MenuDAO menuController;
    public static PullmanDAO pullmanCotroller;
    public static PiattoDAO piattoController;


    static {
        try {
            loginController = new LoginController();
            registerController = new RegisterController();
            kidController = new BambinoDAO();
            parentsController = new GenitoreDAO();
            pediatraController = new PediatraDAO();
            personalController = new PersonaleDAO();
            providersController = new FornitoreDAO();
            tripsController = new GitaDAO();
            ingredientiController = new IngredientiDAO();
            menuController = new MenuDAO();
            pullmanCotroller = new PullmanDAO();
            piattoController = new PiattoDAO();


        }catch (RemoteException e){

            e.printStackTrace();
            System.err.println(e.getMessage());
        }

    }

    public void start() {


        try{
            System.out.println("[RMI]Binding server implementation to registry");
            Registry registry= LocateRegistry.createRegistry(1099);

            registry.bind("login_controller", loginController);
            registry.bind("register_controller", registerController);
            registry.bind("kid_controller", kidController);
            registry.bind("parents_controller", parentsController);
            registry.bind("pediatra_controller", pediatraController);
            registry.bind("personal_controller",personalController);
            registry.bind("providers_controller", providersController);
            registry.bind("trips_controller", tripsController);
            registry.bind("ingredienti_controller", ingredientiController);
            registry.bind("menu_controller", menuController);
            registry.bind("pullman_controller", pullmanCotroller);
            registry.bind("piatto_controller", piattoController);

    } catch (Exception e) {
        System.err.println("[RMI]Server exception");
        e.printStackTrace();
    }
        System.out.println("[RMI]Waiting for invocations from clients");
    }
}
