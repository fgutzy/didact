import java.sql.*;

import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;


public class Main {
    public static void main(String[] args) throws SQLException {


        int anzahlDerLoginVersuche = 0;

        Connection conn = DriverManager     //connecten zur datenbank
                .getConnection("jdbc:mysql://localhost:3306/didact", "root", "rootpassword");

        ResultSet resultSet;


            do {
                if (anzahlDerLoginVersuche == 3) {
                    throw new HackerAlarmException("Vorsicht Hacker am werkeln");
                }
                String sql = "SELECT * FROM mitarbeiter WHERE username = ? and password = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, showInputDialog("Bitte username eingeben"));
                statement.setString(2, showInputDialog("Bitte Passwort eingeben"));
                resultSet = statement.executeQuery();
                anzahlDerLoginVersuche++;
            } while (!resultSet.next());
            showMessageDialog(null, "Der User hat sich nach " + anzahlDerLoginVersuche + " erfolgreich eingeloggt.");
        conn.close();
    }
    }
