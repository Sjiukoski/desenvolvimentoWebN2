package br.edu.cesusc.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.cesusc.dao.ClientDAO;
import br.edu.cesusc.model.Client;

@WebServlet("/")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClientDAO clientDAO;
	
	public ClientServlet() {
		super();
	}

	public void init() {
		clientDAO = new ClientDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new":
				newForm(request, response);
				break;
			case "/insert":
				insertClient(request, response);
				break;
			case "/delete":
				deleteClient(request, response);
				break;
			case "/update":
				updateClient(request, response);
				break;
			case "/edit":
				editForm(request, response);
				break;
			default:
				listClient(request, response);
				break;
			}
		} catch (Exception exception) {
			throw new ServletException(exception);

		}
	}

	private void listClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Client> listClients = clientDAO.selectAllClients();
		request.setAttribute("listClient", listClients);
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-list.jsp");
		dispatcher.forward(request, response);
	}

	private void editForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Client foundClient = clientDAO.selectClient(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-form.jsp");
		request.setAttribute("client", foundClient);
		dispatcher.forward(request, response);
	}

	private void updateClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int registration = Integer.parseInt(request.getParameter("registration"));
		String modality = request.getParameter("modality");
		String district = request.getParameter("district");
		String city = request.getParameter("city");
		String cep = request.getParameter("cep");
		String state = request.getParameter("state");
		String publicPlace = request.getParameter("publicPlace");
		String cellphone = request.getParameter("cellphone");
		
		Client clint = new Client(id, name, registration, modality, district, city, cep, state, publicPlace, cellphone);
		clientDAO.updateClient(clint);
		response.sendRedirect("list");
	}

	private void deleteClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		clientDAO.deleteClient(id);
		response.sendRedirect("list");
	}

	private void insertClient(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		int registration = Integer.parseInt(request.getParameter("registration"));
		String modality = request.getParameter("modality");
		String district = request.getParameter("district");
		String city = request.getParameter("city");
		String cep = request.getParameter("cep");
		String state = request.getParameter("state");
		String publicPlace = request.getParameter("publicPlace");
		String cellphone = request.getParameter("cellphone");

		Client client = new Client(name, registration, modality, district, city, cep, state, publicPlace, cellphone);
		clientDAO.insertClient(client);
		response.sendRedirect("list");
	}

	private void newForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-form.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
