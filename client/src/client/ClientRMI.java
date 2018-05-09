package client;

import common.InterfaceRMI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;

public class ClientRMI {

    public ClientRMI() throws RemoteException, NamingException, NotBoundException {

        Context namingContext = new InitialContext();
        System.setProperty("java.security.policy", "client.policy");
        System.setSecurityManager(new SecurityManager());
        System.out.print("RMI registry bindings: ");
        Enumeration<NameClassPair> e = namingContext.list("rmi://localhost/");
        while (e.hasMoreElements())
            System.out.println(e.nextElement().getName());
        String url = "rmi://localhost/Interface";
        InterfaceRMI i = (InterfaceRMI) namingContext.lookup(url);
        ArrayList<String> l =new ArrayList<String>();

    }
}

