package ctr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RuoloDao;
import dao.RuoloDaoJPA;
import model.Ruolo;

/**
 * Servlet implementation class RuoloSrv
 */
@WebServlet("/RuoloSrv")
public class RuoloSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RuoloSrv() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		RuoloDao ruoloDao = new RuoloDaoJPA();

		String tipoOp = request.getParameter("tipoOperazione");
		System.out.println("tipoOperazione=" + tipoOp);

		if (tipoOp != null && tipoOp.equals("inserisci")) {
			String descr = request.getParameter("descrizione");

			Ruolo r = new Ruolo();
			r.setDescrizione(descr);
			ruoloDao.inserisci(r);
			
			request.getRequestDispatcher("/inserimentoRuoloOk.jsp").forward(request,response);
		}

		if (tipoOp != null && tipoOp.equals("aggiorna")) {
			String descr = request.getParameter("descrizione");
			String id = request.getParameter("id");
			int idR = Integer.parseInt(id);
			
			Ruolo r = new Ruolo();
			r.setDescrizione(descr);
			r.setIdRuolo(idR);
			ruoloDao.aggiorna(r);
			
			response.sendRedirect(request.getContextPath() + "/aggiornaRuoloOK.jsp");
		}

		if (tipoOp != null && tipoOp.equals("elimina")) {
			String id = request.getParameter("id");
			int idR = Integer.parseInt(id);
			ruoloDao.elimina(idR);
		}

		if (tipoOp != null && tipoOp.equals("ricercaperid")) {
			String id = request.getParameter("id");
			int idR = Integer.parseInt(id);
			
			Ruolo r = ruoloDao.ricercaPerId(idR);
			if(r != null) {
			     System.out.println("Storico trovato: " + r.getDescrizione());
			     request.getSession().setAttribute("ruoloTrovato", r);
			     request.getRequestDispatcher("/aggiornaRuolo.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/ricercaRuoloPerIDKO.jsp").forward(request, response);
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
