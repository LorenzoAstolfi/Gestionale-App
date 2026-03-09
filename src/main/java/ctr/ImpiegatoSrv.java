package ctr;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ImpiegatoDao;
import dao.ImpiegatoDaoJPA;
import dao.ImpiegatoDaoJdbcImpl;
import dao.StoricoDao;
import dao.StoricoDaoJdbcImpl;
import model.Impiegato;
import model.Storico;

/**
 * Servlet implementation class ImpiegatoSrv
 */
@WebServlet("/ImpiegatoSrv")
public class ImpiegatoSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ImpiegatoSrv() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		ImpiegatoDao impDao = new ImpiegatoDaoJPA();

		String tipoOp = request.getParameter("tipoOperazione");
		System.out.println("tipoOperazione=" + tipoOp);

		if (tipoOp != null && tipoOp.equals("inserisci")) {
			String cf = request.getParameter("cf");
			String cogn = request.getParameter("cognome");
			String nom = request.getParameter("nome");

			Impiegato imp = new Impiegato();

			imp.setCodicefiscale(cf);
			imp.setCognome(cogn);
			imp.setNome(nom);

			impDao.inserisci(imp);

			request.getRequestDispatcher("/inserimentoImpiegatoOk.jsp").forward(request, response);
		}

		if (tipoOp != null && tipoOp.equals("aggiorna")) {
			String cf = request.getParameter("cf");
			String cogn = request.getParameter("cognome");
			String nom = request.getParameter("nome");
			String matr = request.getParameter("matr");
			int matricola = Integer.parseInt(matr);

			Impiegato imp = new Impiegato();

			imp.setCodicefiscale(cf);
			imp.setCognome(cogn);
			imp.setMatricola(matricola);
			imp.setNome(nom);

			impDao.aggiorna(imp);

			response.sendRedirect(request.getContextPath() + "/aggiornaImpiegatoOK.jsp");

		}

		if (tipoOp != null && tipoOp.equals("ricercaCF")) {
			String cf = request.getParameter("cf");

			Impiegato imp = impDao.ricercaPerCodiceFiscale(cf);
			if (imp != null) {
				System.out.println("Impiegato trovato: " + imp.getMatricola());
				request.getSession().setAttribute("impiegatoTrovato", imp);
				request.getRequestDispatcher("/aggiornaImpiegato.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/ricercaPerCodiceFiscaleKO.jsp").forward(request, response);
			}
		}

		if ("ricercaCognome".equals(tipoOp)) {
			String cognome = request.getParameter("cognome");

			List<Impiegato> impiegati = impDao.ricercaPerCognome(cognome);
			if (!impiegati.isEmpty()) {
				System.out.println("Impiegati trovati: " + impiegati.size());
				request.getSession().setAttribute("impiegatiTrovati", impiegati);
				request.getRequestDispatcher("/risultatiRicercaImpiegati.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/ricercaImpiegatoPerCognKO.jsp").forward(request, response);
			}
		}

		if ("elimina".equals(tipoOp)) {
			String cf = request.getParameter("cf");
			String cognome = request.getParameter("cognome");
			String matricola = request.getParameter("matricola");
			int mat = Integer.parseInt(matricola);

			StoricoDao sDao = new StoricoDaoJdbcImpl();
			List<Storico> res = sDao.ricercaPerMatricola(mat);

			if (res != null && res.size() > 0) {
				for (Storico storico : res) {
					sDao.elimina(storico.getIdStorico());
				}
			}

			impDao.elimina(cf);

			List<Impiegato> imp = impDao.ricercaPerCognome(cognome);
			if (imp != null && imp.size() > 0) {
				System.out.println("Impiegati trovati: " + imp.size());
				request.getSession().setAttribute("impiegatiTrovati", imp);
				request.getRequestDispatcher("/risultatiRicercaImpiegati.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/ricercaImpiegatoPerCognKO.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
