package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import conection.ConectionFactory;
import model.Client;

public class ClientDao {

	private static final String insertStatementString = "INSERT INTO Client (nume,email,adresa)" + " VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM Client where id = ?";
	private final static String updateStatementString = "Update Client set nume= ?,email=?,adresa=? where id=?";
	private final static String deleteStatementString = "Delete from Client where id=? ";

	public static Client cauta(int clientId) {
		Client client = null;

		Connection dbConnection = ConectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = (PreparedStatement) dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, clientId);
			rs = findStatement.executeQuery();
			rs.next();

			String numeClient = rs.getString("nume");
			String emailClient = rs.getString("email");
			String adresaClient = rs.getString("adresa");

			client = new Client(clientId, numeClient, emailClient, adresaClient);
		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(rs);
			ConectionFactory.close(findStatement);
			ConectionFactory.close(dbConnection);
		}
		return client;
	}

	public static int insert(Client client) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = (PreparedStatement) dbConnection.prepareStatement(insertStatementString,
					Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, client.getNume());
			insertStatement.setString(2, client.getEmail());
			insertStatement.setString(3, client.getAdresa());

			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(insertStatement);
			ConectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static void update(Client client, int idClient) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement updateStatement = null;

		try {
			updateStatement = (PreparedStatement) dbConnection.prepareStatement(updateStatementString,
					Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, client.getNume());
			updateStatement.setString(2, client.getEmail());
			updateStatement.setString(3, client.getAdresa());
			updateStatement.setInt(4, idClient);
			updateStatement.executeUpdate();

		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(updateStatement);
			ConectionFactory.close(dbConnection);
		}

	}

	public static void delete(int idClient) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = (PreparedStatement) dbConnection.prepareStatement(deleteStatementString,
					Statement.RETURN_GENERATED_KEYS);

			deleteStatement.setInt(1, idClient);
			deleteStatement.executeUpdate();

		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(deleteStatement);
			ConectionFactory.close(dbConnection);
		}

	}

	/*
	 * public static void main(String[] args) { System.out.println(cauta(1)); Client
	 * client = new Client("Gheorghe", "dashj@yahoo.com", "Strada Prostilor");
	 * System.out.println(insert(client)); Client client1 = new Client("Alin1",
	 * "alin1@yahoo.com", "Strada1"); update(client1, 1); delete(1); //
	 * System.out.println(update(client1,1));
	 * 
	 * }
	 */

}
