import com.sun.org.apache.regexp.internal.RE;
import common.*;
import common.Classes.Bambino;
import common.Classes.Ingredienti;
import common.Classes.Menu;
import common.Classes.Piatto;
import common.Classes.*;

import java.time.LocalDate;
import java.util.ArrayList;


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
                    String cfProvidersDelete = (String) r.params[0];

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

                case GET_KID:
                    String cf = (String) r.params[0];

                    RegistryBuilding.kidController.getKid(cf);
                    return  new SocketResponse(false, null);

                case GET_ALL_KID:
                    ArrayList<Bambino> kidList = new ArrayList<>(RegistryBuilding.kidController.getAllBambini());
                    return  new SocketResponse(false, kidList);

                case GET_ALL_ALLERGIE:
                    Bambino bambino1 = (Bambino) r.params[0];

                    ArrayList<Ingredienti> allergieList = new ArrayList<>(RegistryBuilding.kidController.getAllAllergie(bambino1));
                    return  new SocketResponse(false, allergieList);

                case GET_ALL_CF_KID:

                    ArrayList<String> cfKidList = new ArrayList<>(RegistryBuilding.kidController.getAllCf());
                    return  new SocketResponse(false, cfKidList);

                case ADD_KID_PRESENTE:

                    String cfKidPresente = (String) r.params[0];

                    RegistryBuilding.kidController.inserisciBambinoPresente(cfKidPresente);
                    return  new SocketResponse(false, null);

                case GET_ALL_PRESENTI:

                    ArrayList<Bambino> kidPresentiList  = new ArrayList<>(RegistryBuilding.kidController.getAllPresenti());
                    return  new SocketResponse(false, kidPresentiList);

                case GET_INTOLLERANZE_KID:
                    Bambino bambino2 = (Bambino) r.params[0];

                    ArrayList<Intolleranze> intolleranzeKidList = new ArrayList<>(RegistryBuilding.kidController.getIntolleranzeBambino(bambino2));
                    return  new SocketResponse(false, intolleranzeKidList);

                case GET_ALL_KID_NOME_COGNOME:
                    String nome = (String) r.params[0];
                    String cognome = (String) r.params[1];

                    ArrayList<Bambino> kidNomeCognomeList = new ArrayList<>(RegistryBuilding.kidController.getAllBambiniNomeCognome(nome,cognome));
                    return  new SocketResponse(false, kidNomeCognomeList);

                case DELETE_KID_PRESENTE:

                    RegistryBuilding.kidController.cancellaPresenti();
                    return  new SocketResponse(false, null);

                case GET_ALL_PARENTS:

                    ArrayList<Genitore> parentsList = new ArrayList<>(RegistryBuilding.parentsController.getAllGenitori());
                    return  new SocketResponse(false, parentsList);

                case GET_ALL_CF_PARENTS:

                    ArrayList<String> parentsCfList = new ArrayList<>(RegistryBuilding.parentsController.getAllCf());
                    return  new SocketResponse(false, parentsCfList);

                case GET_ALL_PEDIATRA:

                    ArrayList<Pediatra> pediatraList = new ArrayList<>(RegistryBuilding.pediatraController.getAllPediatra());
                    return  new SocketResponse(false, pediatraList);

                case GET_ALL_CF_PEDIATRA:
                    ArrayList<String> pediatraCfList = new ArrayList<>(RegistryBuilding.pediatraController.getAllCf());
                    return  new SocketResponse(false, pediatraCfList);

                case GET_ALL_PERSONAL:
                    ArrayList<Personale> personaleList = new ArrayList<>(RegistryBuilding.personalController.getAllPersonale());
                    return new SocketResponse(false, personaleList);

                case GET_ALL_CF_PERSONAL:
                    ArrayList<String> personalCfList = new ArrayList<>(RegistryBuilding.personalController.getAllCf());
                    return new SocketResponse(false, personalCfList);

                case GET_ALL_PROVIDERS:
                    ArrayList<Fornitore> providersList = new ArrayList<>(RegistryBuilding.providersController.getAllFornitore());
                    return new SocketResponse(false, providersList);

                case GET_ALL_CF_PROVIDERS:
                    ArrayList<String> providersCfList = new ArrayList<>(RegistryBuilding.providersController.getAllCf());
                    return new SocketResponse(false, providersCfList);

                case GET_ALL_PARTITA_IVA :
                    ArrayList<String> partitaIVAList = new ArrayList<>(RegistryBuilding.providersController.getAllPartitaIVA());
                    return new SocketResponse(false, partitaIVAList);

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

                    ArrayList<Menu> menuList= new ArrayList<>(RegistryBuilding.menuController.getAllMenu());
                    return  new SocketResponse(false, menuList);

                case GET_ALL_INGREDIENTI_MENU:
                    Menu menu = (Menu) r.params[0];

                    ArrayList<Ingredienti> ingredientiMenuList = new ArrayList<>(RegistryBuilding.menuController.getAllIngredientiMenu(menu));
                    return new SocketResponse(false, ingredientiMenuList);

                case GET_ALL_KID_PRESENTI_SENZA_MENU:
                    Menu menu1 = (Menu) r.params[0];

                    ArrayList<Intolleranze> kidSenzaMenuList = new ArrayList<>(RegistryBuilding.menuController.getAllBambiniPresentiSenzaMenu(menu1));
                    return new SocketResponse(false, kidSenzaMenuList);

                case GET_ALL_NUM_MENU:

                    ArrayList<Integer> numMenuList = new ArrayList<>(RegistryBuilding.menuController.getAllNumMenu());
                    return new SocketResponse(false, numMenuList);

                case GET_MENU_NUM:
                    int numero = (int) r.params[0];

                    RegistryBuilding.menuController.getMenuNumero(numero);
                    return new SocketResponse(false,null);

                case GET_ALL_PIATTI:

                    ArrayList<Piatto> piattiList = new ArrayList<>(RegistryBuilding.piattoController.getAllPiatti());
                    return new SocketResponse(false, piattiList);

                case GET_ALL_PRIMI:
                    ArrayList<Piatto> primiList = new ArrayList<>(RegistryBuilding.piattoController.getAllPrimi());
                    return new SocketResponse(false, primiList);

                case GET_ALL_SECONDI:
                    ArrayList<Piatto> secondiList = new ArrayList<>(RegistryBuilding.piattoController.getAllSecondi());
                    return new SocketResponse(false, secondiList);

                case GET_ALL_CONTORNI:
                    ArrayList<Piatto> contoriniList = new ArrayList<>(RegistryBuilding.piattoController.getAllContorni());
                    return new SocketResponse(false, contoriniList);

                case ADD_INGREDIENTE_PIATTO:
                    Piatto piatto = (Piatto) r.params[0];
                    Ingredienti ingredienti1 = (Ingredienti) r.params[1];

                    RegistryBuilding.piattoController.inserisciIngredientePiatto(piatto,ingredienti1);
                    return  new SocketResponse(false, null);

                case DELETE_INGREDIENTE_PIATTO:
                    Piatto piatto4 = (Piatto) r.params[0];
                    Ingredienti ingredienti2 = (Ingredienti) r.params[1];

                    RegistryBuilding.piattoController.cancellaIngredientePiatto(piatto4,ingredienti2);
                    return  new SocketResponse(false, null);

                case GET_ALL_INGREDIENTE_PIATTO:

                    Piatto piatto5 = (Piatto) r.params[0];

                    ArrayList<Ingredienti> ingredientiPiattoList = new ArrayList<>(RegistryBuilding.piattoController.getAllIngredientiPiatto(piatto5));
                    return  new SocketResponse(false, ingredientiPiattoList);

                case GET_ALL_NOME_PIATTI:

                    ArrayList<String> nomePiattiList = new ArrayList<>(RegistryBuilding.piattoController.getAllNomiPiatti());
                    return new SocketResponse(false, nomePiattiList);

                case GET_ALL_MENU_MANGIA:
                    Menu menu2 = (Menu) r.params[0];

                    ArrayList<Mangia> menuMangiaList = new ArrayList<>(RegistryBuilding.menuController.GetAllBambiniMenuMangia(menu2));
                    return new SocketResponse(false, menuMangiaList);

                case GET_ALL_KID_MENU:
                    Menu menu3 = (Menu) r.params[0];

                    ArrayList<Bambino> kidMenuList = new ArrayList<>(RegistryBuilding.menuController.getAllBambiniMenu(menu3));
                    return  new SocketResponse(false, kidMenuList);

                case INS_KID_MANGIA:
                    Menu menu4 = (Menu) r.params[0];
                    Bambino bambino3 = (Bambino) r.params[1];

                    RegistryBuilding.menuController.inserisciBambinoMangia(menu4,bambino3);
                    return new SocketResponse(false, null);

                case DELETE_KID_MANGIA:
                    Menu menu5 = (Menu) r.params[0];
                    Bambino bambino4 = (Bambino) r.params[1];

                    RegistryBuilding.menuController.cancellaBambinoMangia(menu5,bambino4);
                    return new SocketResponse(false, null);

                case GET_PIATTO1:
                    int num = (int) r.params[0];

                    RegistryBuilding.menuController.getPiatto1(num);
                    return new SocketResponse(false, null);

                case GET_PIATTO2:
                    int num1 = (int) r.params[0];

                    RegistryBuilding.menuController.getPiatto2(num1);
                    return new SocketResponse(false, null);

                case GET_PIATTO3:
                    int num2 = (int) r.params[0];

                    RegistryBuilding.menuController.getPiatto3(num2);
                    return new SocketResponse(false, null);

                case GET_ALL_INGREDIENTI:

                    ArrayList<Ingredienti> ingredientiList = new ArrayList<>(RegistryBuilding.ingredientiController.getAllIngredienti());
                    return new SocketResponse(false, ingredientiList);

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

                case GET_ALL_PULLMAN:
                    ArrayList<Pullman> pullmanList = new ArrayList<>(RegistryBuilding.pullmanCotroller.getAllPullman());
                    return new SocketResponse(false, pullmanList);

                case GET_ALL_KID_PULLMAN:
                    Pullman pullman = (Pullman) r.params[0];
                    ArrayList<Bambino> kidPullmanList = new ArrayList<>(RegistryBuilding.pullmanCotroller.getAllBambiniPullman(pullman));
                    return new SocketResponse(false, kidPullmanList);

                case INS_KID_PULLMAN:
                    Bambino bambino5 = (Bambino) r.params[0];
                    Pullman pullman1 = (Pullman) r.params[1];

                    RegistryBuilding.pullmanCotroller.inserisciBambinoPulman(bambino5,pullman1);
                    return new SocketResponse(false, null);


                case GET_ALL_TARGHE:

                    ArrayList<String> targheList = new ArrayList<>(RegistryBuilding.pullmanCotroller.getAllTarghe());
                    return new SocketResponse(false, targheList);

                case GET_ALL_TRIP:

                    ArrayList<Gita> tripList = new ArrayList<>(RegistryBuilding.tripsController.getAllGite());
                    return new SocketResponse(false, tripList);

                case ADD_KID_TRIP:
                    int codice_g = (int) r.params[0];
                    Bambino bambino6 = (Bambino) r.params[1];

                    RegistryBuilding.tripsController.inserisciBambinoGita(codice_g, bambino6);
                    return new SocketResponse(false, null);

                case DELETE_KID_TRIP:

                    int codice_g1 = (int) r.params[0];
                    Bambino bambino7 = (Bambino) r.params[1];

                    RegistryBuilding.tripsController.cancellaBambinoGita(codice_g1, bambino7);
                    return new SocketResponse(false, null);

                case GET_ALL_KID_TRIP:

                    Gita gita = (Gita) r.params[0];

                    ArrayList<Bambino> kidTripList = new ArrayList<>(RegistryBuilding.tripsController.getAllBambiniGita(gita));
                    return new SocketResponse(false, kidTripList);

                case GET_ALL_NUM_TRIP:
                    ArrayList<Integer> numTripList = new ArrayList<>(RegistryBuilding.tripsController.getAllNumGite());
                    return new SocketResponse(false, numTripList);

                case GET_KID_PRESENTE:
                    String cf1 = (String) r.params[0];

                    RegistryBuilding.tripsController.getKidPresente(cf1);
                    return new SocketResponse(false, null);

            }

        } catch (Exception e)

        {
           return new SocketResponse(true,e);
        }


return null;

    }
}





