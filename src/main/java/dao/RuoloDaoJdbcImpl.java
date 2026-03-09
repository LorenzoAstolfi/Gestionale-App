package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Ruolo;

public class RuoloDaoJdbcImpl implements RuoloDao {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/gestionale?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Fr4L0r3nz02025!";

    static {
        try {
            Class.forName(DRIVER);
            System.out.println("Driver caricato correttamente");
        } catch (ClassNotFoundException e) {
            System.err.println("Errore nel caricamento del driver JDBC");
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public Ruolo ricercaPerId(int id) {
        Ruolo r = null;
        String qry = "SELECT * FROM ruolo WHERE idRuolo = ?";
        try (Connection con = getConnection();
             PreparedStatement cmd = con.prepareStatement(qry)) {

            cmd.setInt(1, id);

            try (ResultSet res = cmd.executeQuery()) {
                if (res.next()) {
                    r = new Ruolo();
                    r.setIdRuolo(id);
                    r.setDescrizione(res.getString("descrizione"));
                }
            }

        } catch (SQLException e) {
            System.err.println("Errore durante la ricerca per ID ruolo");
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public void inserisci(Ruolo i) {
        String sql = "INSERT INTO ruolo(descrizione) VALUES(?)";
        try (Connection dbConnection = getConnection();
             PreparedStatement cmd = dbConnection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            cmd.setString(1, i.getDescrizione());
            cmd.executeUpdate();

            try (ResultSet generatedKeys = cmd.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    i.setIdRuolo(generatedId);
                    System.out.println("Ruolo inserito con ID generato: " + generatedId);
                }
            }

        } catch (SQLException e) {
            System.err.println("Errore durante l'inserimento del ruolo");
            e.printStackTrace();
        }
    }

    @Override
    public boolean aggiorna(Ruolo r2) {
        String sql = "UPDATE ruolo SET descrizione = ? WHERE idRuolo = ?";
        try (Connection dbConnection = getConnection();
             PreparedStatement cmd = dbConnection.prepareStatement(sql)) {

            cmd.setString(1, r2.getDescrizione());
            cmd.setInt(2, r2.getIdRuolo());
            int rowsUpdated = cmd.executeUpdate();

            System.out.println("Record aggiornato nella tabella ruolo!");
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Errore durante l'aggiornamento del ruolo");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean elimina(int idRuolo) {
        String sql = "DELETE FROM ruolo WHERE idRuolo = ?";
        try (Connection dbConnection = getConnection();
             PreparedStatement cmd = dbConnection.prepareStatement(sql)) {

            cmd.setInt(1, idRuolo);
            int rowsDeleted = cmd.executeUpdate();

            System.out.println("Record eliminato dalla tabella ruolo!");
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Errore durante l'eliminazione del ruolo");
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        RuoloDao rDao = new RuoloDaoJdbcImpl();

        // Esempio inserimento
        Ruolo r = new Ruolo();
        r.setDescrizione("Amministratore");
        rDao.inserisci(r);
        System.out.println("ID assegnato dal DB: " + r.getIdRuolo());

        // Verifica
        Ruolo g = rDao.ricercaPerId(r.getIdRuolo());
        if (g != null)
            System.out.println("Ruolo trovato: " + g.getDescrizione());
        else
            System.out.println("Ruolo non trovato");
    }
}
