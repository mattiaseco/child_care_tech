package Controller;

import common.Interface.iRegisterController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class RegisterController extends UnicastRemoteObject implements iRegisterController {
    public RegisterController() throws RemoteException{}

    public void register(String username, String password){
        try {
            registerAttempt(username, password);
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void registerAttempt(String user,String pass) throws SQLException {

        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildLoginQuery(user, pass);

        try {
            rs = st.executeQuery(sql);
            conn.close();
            rs.next();
        } catch(SQLException ex) {
            System.err.println("sql exception");
            ex.printStackTrace();
            conn.close();
        }
    }

    private String buildLoginQuery(String user, String pass) throws SQLException{
        return "INSERT INTO Utente(UserName, Password)"+
                "VALUES('"+user+"','"+pass+"')";
    }
}
