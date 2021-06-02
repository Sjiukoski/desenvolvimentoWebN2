package br.edu.cesusc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.cesusc.model.Client;

public class ClientDAO {

	private String jdbcURL = "jdbc:mysql://localhost/db_gym?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";

	private String jdbcUsername = "";

	private String jdbcPassword = "";

	private static final String INSERT_CLIENT_SQL = "INSERT INTO clients (name, registration, modality, district, city, CEP, state, public_place, cellphone) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_CLIENT_BY_ID = "SELECT * FROM clients WHERE id = ?";
	private static final String SELECT_ALL_CLIENTS = "SELECT * FROM clients";
	private static final String DELETE_CLIENT_SQL = "DELETE FROM clients WHERE id = ?;";
	private static final String UPDATE_CLIENT_SQL = "UPDATE clients SET name = ?, registration = ?, modality = ?, district = ?, city = ?, CEP = ?, state = ?, "
			+ "public_place = ?, cellphone = ? WHERE id = ?;";

	public ClientDAO() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertClient(Client client) throws SQLException {
		System.out.println(INSERT_CLIENT_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
			preparedStatement.setString(1, client.getName());
			preparedStatement.setInt(2, client.getRegistration());
			preparedStatement.setString(3, client.getModality());
			preparedStatement.setString(4, client.getDistrict());
			preparedStatement.setString(5, client.getCity());
			preparedStatement.setString(6, client.getCep());
			preparedStatement.setString(7, client.getState());
			preparedStatement.setString(8, client.getPublicPlace());
			preparedStatement.setString(9, client.getCellphone());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Client selectClient(int id) {
		Client client = null;

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ID)) {
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				int registration = rs.getInt("registration");
				String modality = rs.getString("modality");
				String district = rs.getString("district");
				String city = rs.getString("city");
				String cep = rs.getString("cep");
				String state = rs.getString("state");
				String publicPlace = rs.getString("public_place");
				String cellphone = rs.getString("cellphone");
				
				client = new Client(id, name, registration, modality, district, city, cep, state, publicPlace, cellphone);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return client;
	}

	public List<Client> selectAllClients() {
		List<Client> clients = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CLIENTS)) {
			System.out.println(preparedStatement);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int registration = rs.getInt("registration");
				String modality = rs.getString("modality");
				String district = rs.getString("district");
				String city = rs.getString("city");
				String cep = rs.getString("cep");
				String state = rs.getString("state");
				String publicPlace = rs.getString("public_place");
				String cellphone = rs.getString("cellphone");
				
				clients.add(new Client(id, name, registration, modality, district, city, cep, state, publicPlace, cellphone));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return clients;
	}

	public boolean deleteClient(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CLIENT_SQL)) {
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateClient(Client client) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CLIENT_SQL)) {
			System.out.println(preparedStatement);
			preparedStatement.setString(1, client.getName());
			preparedStatement.setInt(2, client.getRegistration());
			preparedStatement.setString(3, client.getModality());
			preparedStatement.setString(4, client.getDistrict());
			preparedStatement.setString(5, client.getCity());
			preparedStatement.setString(6, client.getCep());
			preparedStatement.setString(7, client.getState());
			preparedStatement.setString(8, client.getPublicPlace());
			preparedStatement.setString(9, client.getCellphone());
			preparedStatement.setInt(10, client.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException exception) {
		for (Throwable e : exception) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("C�digo do erro: " + ((SQLException) e).getErrorCode());
				System.err.println("Mensagem: " + e.getMessage());
			}
		}

	}
}
