package ctr;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoricoDao;
import dao.StoricoDaoJPA;
import model.Impiegato;
import model.Ruolo;
import model.Storico;

/**
 * Servlet implementation class StoricoSrv
 */
@WebServlet("/StoricoSrv")
public class StoricoSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoricoSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StoricoDao stDao = new StoricoDaoJPA();

		String tipoOp = request.getParameter("tipoOperazione");
		System.out.println("tipoOperazione=" + tipoOp);

		if (tipoOp != null && tipoOp.equals("inserisci")) {
			String matr = request.getParameter("matricola");
			String idR = request.getParameter("idRuolo");
			String dateI = request.getParameter("dataInizio");
			String dateF = request.getParameter("dataFine");
			
			int matricola = Integer.parseInt(matr);
			int idRuolo = Integer.parseInt(idR);
			
			Storico r = new Storico();
			Ruolo R = new Ruolo();
			R.setIdRuolo(idRuolo);
			r.setRuolo(R);
			Impiegato imp = new Impiegato();
			imp.setMatricola(matricola);
			r.setImpiegato(imp);
			
			r.setDataFine(convert(dateF));
			r.setDataInizio(convert(dateI));
			
			stDao.inserisci(r);
			
			request.getRequestDispatcher("/inserimentoStoricoOK.jsp").forward(request,response);
		}

		if (tipoOp != null && tipoOp.equals("aggiorna")) {
		    String idStoricoStr = request.getParameter("idStorico");
		    int idStorico = Integer.parseInt(idStoricoStr);

		    Storico r = stDao.ricercaPerId(idStorico);
		    if (r != null) {
		        int matricola = Integer.parseInt(request.getParameter("matricola"));
		        int idRuolo = Integer.parseInt(request.getParameter("idRuolo"));
		        r.setDataInizio(convert(request.getParameter("dataInizio")));
		        r.setDataFine(convert(request.getParameter("dataFine")));

		        Ruolo ruolo = new Ruolo();
		        ruolo.setIdRuolo(idRuolo);
		        r.setRuolo(ruolo);

		        Impiegato imp = new Impiegato();
		        imp.setMatricola(matricola);
		        r.setImpiegato(imp);

		        stDao.aggiorna(r);
		        request.getRequestDispatcher("/aggiornaStoricoOK.jsp").forward(request, response);
		    } else {
		    	System.out.println("NULLO!");
		    }
		}

		if (tipoOp != null && tipoOp.equals("elimina")) {
			String id = request.getParameter("idStorico");
			int idR = Integer.parseInt(id);
			stDao.elimina(idR);
		}

		if (tipoOp != null && tipoOp.equals("ricercaperid")) {
			String id = request.getParameter("idStorico");
			int idR = Integer.parseInt(id);

			Storico s = stDao.ricercaPerId(idR);
			if(s != null) {
			     System.out.println("Storico trovato: " + s.getIdStorico());
			     request.getSession().setAttribute("storicoTrovato", s);
			     request.getRequestDispatcher("/aggiornaStorico.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/ricercaRuoloPerIDKO.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Date convert(String s) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		
		Date date = null;
		try {
			date = formatter.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
