import com.sun.org.apache.regexp.internal.RE;
import common.*;
import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Classes.Menu;
import common.Classes.Piatto;
import org.apache.tools.ant.taskdefs.Local;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;


public class SocketServerResponse{
    public SocketServerResponse(){}

    public SocketResponse sendResponse(SocketRequest r) {


        try {


            switch(r.requestType){

                case LOGIN:
                    String username = (String) r.params[0];
                    String password = (String) r.params[1];
                    boolean returnValue = RegistryBuilding.loginController.login(username,password);
                    return new SocketResponse(false, returnValue);

                case REGISTER:
                    String usern = (String) r.params[0];
                    String pass = (String) r.params[1];
                    RegistryBuilding.registerController.register(usern,pass);
                    return  new SocketResponse(false, null);

                /**ANAGRAFICA*/
                case CREATE_KID:
                    String cfKid = (String) r.params[0];
                    String nomeKid = (String) r.params[1];
                    String cognomeKid = (String) r.params[2];
                    LocalDate dataKid = (LocalDate) r.params[3];
                    String indirizzoLKid = (String) r.params[4];
                    String contatto1 = (String) r.params[5];
                    String contatto2 = (String) r.params[6];

                    RegistryBuilding.kidController.inserisciBambino(cfKid,nomeKid,cognomeKid,dataKid,indirizzoLKid,contatto1,contatto2);
                    return  new SocketResponse(false, null);

                case CREATE_PARENTS:
                    String cfParents = (String) r.params[0];
                    String nomeParents = (String) r.params[1];
                    String cognomeParens = (String) r.params[2];
                    LocalDate dataParents = (LocalDate) r.params[3];
                    String indirizzoParents = (String) r.params[4];
                    String telefonoParents = (String) r.params[5];

                    RegistryBuilding.parentsController.inserisciGenitore(cfParents,nomeParents,cognomeParens,dataParents,indirizzoParents,telefonoParents);
                    return  new SocketResponse(false, null);

                case CREATE_PERSONAL:
                    String cfPersonal = (String) r.params[0];
                    String nomePersonal = (String) r.params[1];
                    String cognomePersonal = (String) r.params[2];
                    LocalDate dataPersonal = (LocalDate) r.params[3];
                    String indirizzoPersonal = (String) r.params[4];
                    String telefonoPersonal = (String) r.params[5];

                    RegistryBuilding.personalController.inserisciPersonale(cfPersonal,nomePersonal,cognomePersonal,dataPersonal,indirizzoPersonal,telefonoPersonal);
                    return  new SocketResponse(false, null);

                case CREATE_PEDIATRA:
                    String cfPediatra = (String) r.params[0];
                    String nomePediatra = (String) r.params[1];
                    String cognomePediatra = (String) r.params[2];
                    LocalDate dataPediatra = (LocalDate) r.params[3];
                    String indirizzoPediatra = (String) r.params[4];
                    String telefonoPediatra = (String) r.params[5];

                    RegistryBuilding.pediatraController.inserisciPediatra(cfPediatra,nomePediatra,cognomePediatra,dataPediatra,indirizzoPediatra,telefonoPediatra);
                    return  new SocketResponse(false, null);

                case CREATE_PROVIDERS:
                    String partitaIVA = (String) r.params[0];
                    String cfProviders = (String) r.params[1];
                    String nomeProviders = (String) r.params[2];
                    String cognomeProviders = (String) r.params[3];
                    LocalDate dataProviders = (LocalDate) r.params[4];
                    String indirizzoProviders = (String) r.params[5];
                    String telefonoProviders = (String) r.params[6];

                    RegistryBuilding.providersController.inserisciFornitore(partitaIVA,cfProviders,nomeProviders,cognomeProviders,dataProviders,indirizzoProviders,telefonoProviders);
                    return  new SocketResponse(false, null);

                case DELETE_KID:

                    String cfKidDelete = (String) r.params[0];

                    RegistryBuilding.kidController.cancellaBambino(cfKidDelete);
                    return  new SocketResponse(false, null);

                case DELETE_PARENTS:
                    String cfParentsDelete = (String) r.params[0];

                    RegistryBuilding.parentsController.cancellaGenitore(cfParentsDelete);
                    return  new SocketResponse(false, null);

                case DELETE_PERSONAL:
                     String cfPersonalDelete = (String) r.params[0];

                     RegistryBuilding.personalController.cancellaPersonale(cfPersonalDelete);
                    return  new SocketResponse(false, null);

                case DELETE_PEDIATRA:
                     String cfPediatraDelete = (String) r.params[0];

                     RegistryBuilding.pediatraController.cancellaPediatra(cfPediatraDelete);
                    return  new SocketResponse(false, null);

                case DELETE_PROVIDERS:
                    String cfProvidersDelete = (String) r.params[1];

                    RegistryBuilding.providersController.cancellaFornitore(cfProvidersDelete);
                    return  new SocketResponse(false, null);

                case UPDATE_KID:
                    String cfKidUpdate = (String) r.params[0];
                    String nomeKidUpdate = (String) r.params[1];
                    String cognomeKidUpdate = (String) r.params[2];
                    LocalDate dataKidUpdate = (LocalDate) r.params[3];
                    String indirizzoKidUpdate = (String) r.params[4];
                    String contatto1KidUpdate = (String) r.params[5];
                    String contatto2KidUpdate = (String) r.params[6];

                    RegistryBuilding.kidController.modificaBambino(cfKidUpdate,nomeKidUpdate,cognomeKidUpdate,dataKidUpdate,indirizzoKidUpdate,contatto1KidUpdate,contatto2KidUpdate);
                    return  new SocketResponse(false, null);

                case UPDATE_PARENTS:
                    String cfParentsUpdate = (String) r.params[0];
                    String nomeParentsUpdate = (String) r.params[1];
                    String cognomeParentsUpdate = (String) r.params[2];
                    LocalDate dataParentsUpdate = (LocalDate) r.params[3];
                    String indirizzoParentsUpdate = (String) r.params[4];
                    String telefonoParentsUpdate = (String) r.params[5];

                    RegistryBuilding.parentsController.modificaGenitore(cfParentsUpdate,nomeParentsUpdate,cognomeParentsUpdate,dataParentsUpdate,indirizzoParentsUpdate,telefonoParentsUpdate);
                    return  new SocketResponse(false, null);

                case UPDATE_PERSONAL:
                    String cfPersonalUpdate = (String) r.params[0];
                    String nomePersonalUpdate = (String) r.params[1];
                    String cognomePersonalUpdate = (String) r.params[2];
                    LocalDate dataPersonalUpdate = (LocalDate) r.params[3];
                    String indirizzoPersonalUpdate = (String) r.params[4];
                    String telefonoPersonalUpdate = (String) r.params[5];

                    RegistryBuilding.personalController.modificaPersonale(cfPersonalUpdate,nomePersonalUpdate,cognomePersonalUpdate,dataPersonalUpdate,indirizzoPersonalUpdate,telefonoPersonalUpdate);
                    return  new SocketResponse(false, null);

                case UPDATE_PEDIATRA:
                    String cfPediatraUpdate = (String) r.params[0];
                    String nomePediatraUpdate = (String) r.params[1];
                    String cognomePediatraUpdate = (String) r.params[2];
                    LocalDate dataPediatraUpdate = (LocalDate) r.params[3];
                    String indirizzoPediatraUpdate = (String) r.params[4];
                    String telefonoPediatraUpdate = (String) r.params[5];

                    RegistryBuilding.pediatraController.modificaPediatra(cfPediatraUpdate,nomePediatraUpdate,cognomePediatraUpdate,dataPediatraUpdate,indirizzoPediatraUpdate,telefonoPediatraUpdate);
                    return  new SocketResponse(false, null);

                case UPDATE_PROVIDERS:
                    String partitaIVAUpdate = (String) r.params[0];
                    String cfProvidersUpdate = (String) r.params[1];
                    String nomeProvidersUpdate = (String) r.params[2];
                    String cognomeProvidersUpdate = (String) r.params[3];
                    LocalDate dataProvidersUpdate = (LocalDate) r.params[4];
                    String indirizzoProvidersUpdate = (String) r.params[5];
                    String telefonoProvidersUpdate = (String) r.params[6];

                    RegistryBuilding.providersController.modificaFornitore(partitaIVAUpdate,cfProvidersUpdate,nomeProvidersUpdate,cognomeProvidersUpdate,dataProvidersUpdate,indirizzoProvidersUpdate,telefonoProvidersUpdate);
                    return  new SocketResponse(false, null);

                case ADD_ALLERGY:
                    Bambino bambino = (Bambino) r.params[0];
                    Ingredienti ingredienti = (Ingredienti) r.params[1];

                    RegistryBuilding.kidController.inserisciAllergia(bambino,ingredienti);
                    return  new SocketResponse(false, null);

                case REMOVE_ALLERGY:
                    Bambino bambinoR = (Bambino) r.params[0];
                    Ingredienti ingredientiR = (Ingredienti) r.params[1];

                    RegistryBuilding.kidController.cancellaAllergia(bambinoR,ingredientiR);
                    return  new SocketResponse(false, null);

                case SEARCH_KID:
                    String nomeKidSearch = (String) r.params[0];
                    String cognomeKidSearch = (String) r.params[1];

                    RegistryBuilding.kidController.getAllBambiniNomeCognome(nomeKidSearch,cognomeKidSearch);
                    return  new SocketResponse(false, null);

                    /**MENSA*/

                case CREATE_INGREDIENTS:
                    String nomeIngrediente = (String) r.params[0];
                    RegistryBuilding.ingredientiController.inserisciIngrediente(nomeIngrediente);
                    return  new SocketResponse(false, null);

                case CREATE_DISHES:
                    String nomePiatto = (String) r.params[0];
                    String tipoPiatto = (String) r.params[1];

                    RegistryBuilding.piattoController.inserisciPiatto(nomePiatto,tipoPiatto);
                    return  new SocketResponse(false, null);

                case CREATE_MENU:
                    int numeroMenu = (int) r.params[0];
                    Piatto piatto1 = (Piatto) r.params[1];

                    RegistryBuilding.menuController.inserisciPrimo(numeroMenu,piatto1);
                    return  new SocketResponse(false, null);

                case CREATE_STEP2_MENU:
                    int numeroMenu2 = (int) r.params[0];
                    Piatto piatto2 = (Piatto) r.params[1];

                    RegistryBuilding.menuController.inserisciSecondo(numeroMenu2,piatto2);
                    return  new SocketResponse(false, null);

                case CREATE_STEP3_MENU:

                    int numeroMenu3 = (int) r.params[0];
                    Piatto piatto3 = (Piatto) r.params[1];

                    RegistryBuilding.menuController.inserisciContorno(numeroMenu3,piatto3);
                    return  new SocketResponse(false, null);

                case DELETE_INGREDIENTS:
                    String nomeIngredienteDelete = (String) r.params[0];

                    RegistryBuilding.ingredientiController.cancellaIngredienti(nomeIngredienteDelete);
                    return  new SocketResponse(false, null);

                case DELETE_DISHES:
                    String nomePiattoDelete = (String) r.params[0];

                    RegistryBuilding.piattoController.cancellaPiatti(nomePiattoDelete);
                    return  new SocketResponse(false, null);

                case DELETE_MENU:
                    int numeroMenuDelete = (int) r.params[0];

                    RegistryBuilding.menuController.cancellaMenu(numeroMenuDelete);
                    return  new SocketResponse(false, null);

                case GET_ALL_MENU:
                    int numeroMenuGETALL = (int) r.params[0];
                    Piatto piatto1GETALL = (Piatto) r.params[1];
                    Piatto piatto2GETALL = (Piatto) r.params[2];
                    Piatto piatto3GETALL = (Piatto) r.params[3];
                    RegistryBuilding.menuController.getAllMenu();

                /**GITE*/

                case CREATE_TRIP:
                    int codiceGita = (int) r.params[0];
                    String destinazione = (String) r.params[1];
                    LocalDate data_partenza = (LocalDate) r.params[2];
                    LocalDate data_ritorno = (LocalDate) r.params[3];
                    double prezzo = (double) r.params[4];
                    String note = (String) r.params[5];

                    RegistryBuilding.tripsController.inserisciGita(codiceGita,destinazione,data_partenza,data_ritorno,prezzo,note);
                    return  new SocketResponse(false, null);

                case CREATE_STEP2_TRIP:
                    int codiceGita2 = (int) r.params[0];
                    int numPartecipanti = (int) r.params[1];

                    RegistryBuilding.tripsController.insertNumPartecipanti(codiceGita2,numPartecipanti);
                    return  new SocketResponse(false, null);

                case CREATE_STEP3_TRIP:
                    int codiceGita3 = (int) r.params[0];
                    int numPullman = (int) r.params[1];

                    RegistryBuilding.tripsController.insertNumPullman(codiceGita3,numPullman);
                    return  new SocketResponse(false, null);

                case CREATE_PULLMAN:
                    String targa = (String) r.params[0];
                    int capienza = (int) r.params[1];

                    RegistryBuilding.pullmanCotroller.inserisciPullman(targa,capienza);
                    return  new SocketResponse(false, null);

                case DELETE_TRIP:

                    int codiceGitaDelete = (int) r.params[0];

                    RegistryBuilding.tripsController.cancellaGita(codiceGitaDelete);
                    return  new SocketResponse(false, null);

                case DELETE_PULLMAN:
                    String targaDelete = (String) r.params[0];

                    RegistryBuilding.pullmanCotroller.cancellaPullman(targaDelete);
                    return  new SocketResponse(false, null);



            }

        } catch (Exception e)

        {
           return new SocketResponse(true,e);
        }


return null;

    }
}





