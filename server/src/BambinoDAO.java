import common.Classes.Bambino;
import common.Classes.Person;
import common.Interface.iBambinoDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BambinoDAO extends UnicastRemoteObject implements iBambinoDAO {

    public BambinoDAO() throws RemoteException {}

    @Override
    public boolean inserisciBambino(int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {

        try {
            return createKid (cod_qr, cf, nome, cognome, data, indirizzo, contatto1, contatto2);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private static boolean createKid (int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateKidSQL(cod_qr, cf, nome, cognome, data, indirizzo, contatto1, contatto2);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            return rs.next();

        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return false;
        }
    }

    private static String buildCreateKidSQL(int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2){

        return "INSERT INTO Bambino(cod_qr,cf,nome,cognome,data,indirizzo,telefono,contatto1,contatto2)" +
                "VALUES('"+cod_qr+"','"+cf+"','"+nome+"','"+cognome+"','"+data+"','"+indirizzo+"','"+contatto1+"','"+contatto2+"')";

    }

    public boolean modificaBambino(int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {
         try{
             return updateKid(cod_qr, cf, nome, cognome, data, indirizzo, contatto1, contatto2);
         }catch (SQLException e){
             System.err.println(e.getMessage());
             e.printStackTrace();
             return false;
         }

    }

    private static boolean updateKid(int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateKidSQL(cod_qr, cf, nome, cognome, data, indirizzo, contatto1, contatto2);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            return rs.next();

        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return false;
        }

    }

    public static String buildUpdateKidSQL(int cod_qr, String cf, String nome, String cognome, LocalDate data, String indirizzo, String contatto1, String contatto2) throws SQLException {
        return "UPDATE Bambino SET cod_qr = '"+cod_qr+"' and cf = '"+cf+"' and nome = '"+nome+"'"; //TODO da finire!!!!
    }

    public List<Bambino> getAllBambini() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Bambino";
        ResultSet rs=stmt.executeQuery(sql);
        List<Bambino> kids = new ArrayList<>();

        while (rs.next()) {
            String cf = rs.getString("cf");
            String cod_qr= rs.getString("cod_qr");
            String nome = rs.getString("nome");
            String cognome = rs.getString("cognome");
            LocalDate data= LocalDate.parse(rs.getString("data"));
            String indirizzo = rs.getString("indirizzo");
            String contatto1 = rs.getString("contatto1");
            String contatto2 = rs.getString("contatto2");

            Person Bimbo=new Person(cf,nome,cognome,data,indirizzo);
            Bambino Bambino= new Bambino(cod_qr,Bimbo,contatto1,contatto2);

            kids.add(Bambino);
        }
        return kids;

    }



}
