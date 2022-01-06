/*
 * Author : Abdelmalek BOUGUERRA
 * Author Email : abdelmalekbouguerra2000@gmail.com
 * Created : Oct 2021
 * description : Lightweight Directory Access Protocol.
 *
 * (c) Copyright by Abdelmalek BOUGUERRA.
 */

package Classes;

import java.sql.*;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import static DAO.DB.*;

public class LDAP {

    public boolean connect(String Uname, String pass) {
        //On remplis un tableau avec les parametres d'environement et de connexion au LDAP.
        Hashtable environnement = new Hashtable();
        environnement.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        environnement.put(Context.PROVIDER_URL, "ldap://10.111.106.11:389");
        environnement.put(Context.SECURITY_AUTHENTICATION, "simple");
        environnement.put(Context.SECURITY_PRINCIPAL,"SONATRACH\\"+ Uname);
        environnement.put(Context.SECURITY_CREDENTIALS, pass);
        try {
            //On appelle le contexte à partir de l'environnement
            DirContext contexte = new InitialDirContext(environnement);
            //Si ça ne plante pas c'est que la connexion est faite
            System.out.println("Connexion au serveur : SUCCES");
//            NamingEnumeration<SearchResult> matricule =
//                    contexte.search("dc=epam,dc=com", "samaccountname=" + Uname, cons);
//            return String.valueOf(contexte.getEnvironment());
            return true;
        }catch (AuthenticationNotSupportedException ex) {
//            return "auth not supported";
            return false;
        }catch (AuthenticationException  ex){
//            return "Incorrect password or Uname";
            return false;
        } catch (NamingException e) {
            System.out.println("Connexion au serveur : ECHEC");
            e.printStackTrace();
//            return "Connexion au serveur : ECHEC";
            return false;
        }
    }

//    public String getMatricule (DirContext contexte,String Uname){
//        try{
//            // creation de searchControl
//            SearchControls cons = new SearchControls();
//            cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
//            String[] attrIDs = {"postalCode"};
//            cons.setReturningAttributes(attrIDs);
//
//            NamingEnumeration<SearchResult> matricule =
//                    contexte.search("dc=epam,dc=com", "samaccountname=" + Uname, cons);
//
//            System.out.println("matricule est : "+matricule);
//            return String.valueOf(matricule);
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//
//    }

//    public void setUsername(String Uname,String matricule){
//        String set ="UPDATE pers SET username = ? WHERE matricule = ?";
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection(url,username,password);
//            PreparedStatement pStatement = connection.prepareStatement(set);
//            pStatement.setString(2,matricule);
//            pStatement.setString(1,Uname);
//            pStatement.executeUpdate();
//            pStatement.close();
//            connection.close();
//            System.out.println("add username : SUCCESS");
//        } catch (ClassNotFoundException | SQLException e) {
////            e.printStackTrace();
//            System.out.println("add username : FAILURE ");
//        }
//    }
}