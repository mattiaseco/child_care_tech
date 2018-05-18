package Controller;

import common.Classes.Gateway;
import common.Interface.iGatewayDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GatewayDAO  extends UnicastRemoteObject implements iGatewayDAO {
    public GatewayDAO() throws RemoteException {}

    @Override
    public void inserisciGatway(int cod_porta,LocalDate data_gate)throws SQLException {

        try {
            createGateway (cod_porta,data_gate);
        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void createGateway (int cod_porta,LocalDate data_gate) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildCreateGatewaySQL(cod_porta, data_gate);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();

        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return;
        }
    }

    private static String buildCreateGatewaySQL(int cod_porta,LocalDate data_gate){

        return "INSERT INTO Gateway(cod_porta,date_gate)" +
                "VALUES('"+cod_porta+"','"+data_gate+"')";

    }

    public void modificaGatway(int cod_porta,LocalDate data_gate) throws SQLException {
        try{
            updateGateway(cod_porta,data_gate);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }

    }

    private static void updateGateway(int cod_porta,LocalDate data_gate) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildUpdateGatewaySQL(cod_porta,data_gate);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();

        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return ;
        }

    }

    public static String buildUpdateGatewaySQL(int cod_porta,LocalDate data_gate) throws SQLException {
        return "UPDATE Gateway SET cod_porta = '"+cod_porta+"' and data_gate = '"+data_gate+"'";
    }

    public List<Gateway> getAllGatway() throws RemoteException,SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM Gateway";
        ResultSet rs=stmt.executeQuery(sql);
        List<Gateway> gatewayList = new ArrayList<>();

        while (rs.next()) {
            int cod_porta = rs.getInt("cod_porta");
            LocalDate data_gate=LocalDate.parse(rs.getString("data_gate"));
            Gateway gateway= new Gateway(cod_porta,data_gate);

            gatewayList.add(gateway);
        }
        return gatewayList;

    }
    public void cancellaGatway(int cod_porta)throws SQLException {
        try {
            deleteGateway(cod_porta);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    private static void deleteGateway(int cod_porta) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildDeleteGatewaySQL(cod_porta);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();


        } catch (SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
            return;
        }

    }
    private static String buildDeleteGatewaySQL(int cod_porta){
        return "DELETE FROM Gateway WHERE cod_porta='"+cod_porta+"'";

    }
}
