package Controller;

import common.Interface.iLoginController;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class LoginController extends UnicastRemoteObject implements iLoginController {
    public LoginController() throws RemoteException {}

    @Override
    public boolean login(String username, String password) {
        try {
            return loginAttempt(username, password);
        } catch(SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private static boolean loginAttempt(String name, String pwd) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/progetto?user=root&password=root");
        Statement st = conn.createStatement();
        ResultSet rs;
        String sql = buildLoginQuery(name, pwd);

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

    private static String buildLoginQuery(String name, String pwd){
        return "Select * " +
                "From Utente " +
                "Where UserName = '" + name + "' and Password = '" + pwd + "';";
    }
}
