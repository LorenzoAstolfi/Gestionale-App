package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Storico;

public class StoricoDaoJdbcImpl implements StoricoDao {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/gestionale?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Fr4L0r3nz02025!";

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    @Override
    public Storico ricercaPerId(int id) {
        Storico storico = null;
        String sql = "SELECT * FROM storico WHERE idstorico = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    storico = new Storico();
                    storico.setIdStorico(id);
                    storico.setDataInizio(rs.getDate("datainizio"));
                    storico.setDataFine(rs.getDate("datafine"));
                }
            }
        } catch (Exception e) {
            System.err.println("Errore nella ricerca per ID storico");
            e.printStackTrace();
        }
        return storico;
    }

    @Override
    public void inserisci(Storico s) {
        String sql = "INSERT INTO storico(matricola, datainizio, datafine, idruolo) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(2, new java.sql.Date(s.getDataInizio().getTime()));
            stmt.setDate(3, new java.sql.Date(s.getDataFine().getTime()));

            stmt.executeUpdate();
            System.out.println("Record inserito nella tabella storico!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean aggiorna(Storico s) {
        String sql = "UPDATE storico SET matricola = ?, datainizio = ?, datafine = ?, idruolo = ? WHERE idstorico = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDate(2, new java.sql.Date(s.getDataInizio().getTime()));
            stmt.setDate(3, new java.sql.Date(s.getDataFine().getTime()));
            stmt.setInt(5, s.getIdStorico());

            int rowsUpdated = stmt.executeUpdate();
            System.out.println("Record aggiornato nella tabella storico!");
            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
	@Override
	public List<Storico> ricercaPerMatricola(int matricola) {
        List<Storico> listaStorici = new ArrayList<>();
        String sql = "SELECT * FROM storico WHERE matricola = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, matricola);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Storico r = new Storico();
                    r.setIdStorico(rs.getInt("idStorico"));
                    r.setDataInizio(rs.getDate("datainizio"));
                    r.setDataFine(rs.getDate("datafine"));
                    listaStorici.add(r);
                }
            }
        } catch (Exception e) {
            System.err.println("Errore nella ricerca per ID storico");
            e.printStackTrace();
        }
        return listaStorici;
	}

    @Override
    public boolean elimina(int id) {
        String sql = "DELETE FROM storico WHERE idstorico = ?";
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            System.out.println("Record eliminato dalla tabella storico!");
            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
