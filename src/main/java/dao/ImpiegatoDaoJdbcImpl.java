package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Impiegato;

public class ImpiegatoDaoJdbcImpl implements ImpiegatoDao {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/gestionale?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Fr4L0r3nz02025!";

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public void inserisci(Impiegato i) {
        try (
        	Connection dbConnection = getConnection();
            PreparedStatement cmd = dbConnection.prepareStatement(
                 "INSERT INTO impiegato(nome, cognome, codicefiscale) VALUES(?,?,?)")) {

            cmd.setString(1, i.getNome());
            cmd.setString(2, i.getCognome());
            cmd.setString(3, i.getCodicefiscale());
            cmd.executeUpdate();

            System.out.println("Record inserito nella tabella impiegato!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Impiegato ricercaPerCodiceFiscale(String codiceFiscale) {
        Impiegato imp = null;
        String qry = "SELECT * FROM impiegato WHERE codicefiscale = ?";
        try (
        		
        	Connection con = getConnection();
            PreparedStatement cmd = con.prepareStatement(qry)) {

            cmd.setString(1, codiceFiscale);

            try (ResultSet res = cmd.executeQuery()) {
                if (res.next()) {
                    imp = new Impiegato();
                    imp.setCodicefiscale(codiceFiscale);
                    imp.setCognome(res.getString("cognome"));
                    imp.setNome(res.getString("nome"));
                    imp.setMatricola(res.getInt("matricola"));
                }
            }

        } catch (Exception e) {
            System.err.println("Errore durante la ricerca per codice fiscale");
            e.printStackTrace();
        }
        return imp;
    }
    
	@Override
	public List<Impiegato> ricercaPerCognome(String cognome) {
		List<Impiegato> listImpiegati = new ArrayList<>();
        String qry = "SELECT * FROM impiegato WHERE cognome = ?";
        try (
        		
        	Connection con = getConnection();
            PreparedStatement cmd = con.prepareStatement(qry)) {

            cmd.setString(1, cognome);

            try (ResultSet res = cmd.executeQuery()) {
                while (res.next()) {
                    Impiegato imp = new Impiegato();
                    imp.setCodicefiscale(res.getString("codicefiscale"));
                    imp.setCognome(res.getString("cognome"));
                    imp.setNome(res.getString("nome"));
                    imp.setMatricola(res.getInt("matricola"));
                    listImpiegati.add(imp);
                }
            }

        } catch (Exception e) {
            System.err.println("Errore durante la ricerca per codice fiscale");
            e.printStackTrace();
        }
        return listImpiegati;
	}

    @Override
    public boolean aggiorna(Impiegato imp2) {
        Impiegato res = ricercaPerCodiceFiscale(imp2.getCodicefiscale());

        if (res != null) {
            String qry = "UPDATE impiegato SET matricola = ?, nome = ?, cognome = ? WHERE codicefiscale = ?";
            try (Connection con = getConnection();
                 PreparedStatement cmd = con.prepareStatement(qry)) {

                cmd.setInt(1, imp2.getMatricola());
                cmd.setString(2, imp2.getNome());
                cmd.setString(3, imp2.getCognome());
                cmd.setString(4, imp2.getCodicefiscale());

                cmd.executeUpdate();

                return true;

            } catch (Exception e) {
                System.err.println("Errore durante l'aggiornamento dell'impiegato");
                e.printStackTrace();
            }
        }

        return false;
    }

    @Override
    public boolean elimina(String codiceFiscale) {
        Impiegato res = ricercaPerCodiceFiscale(codiceFiscale);

        if (res != null) {
            String qry = "DELETE FROM impiegato WHERE codicefiscale = ?";
            try (Connection con = getConnection();
                 PreparedStatement cmd = con.prepareStatement(qry)) {

                cmd.setString(1, codiceFiscale);
                cmd.executeUpdate();

                return true;

            } catch (Exception e) {
                System.err.println("Errore durante l'eliminazione dell'impiegato");
                e.printStackTrace();
            }
        }
        return false;
    }


}
