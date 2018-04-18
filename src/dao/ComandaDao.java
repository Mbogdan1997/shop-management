package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import conection.ConectionFactory;
import model.Comanda;

public class ComandaDao {
	private static final String insertStatementString = "INSERT INTO Comanda (Client_idClient,Produs_idProdus,cantitateComanda)"
			+ " VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM Comanda where idComanda = ?";
	private final static String updateStatementString = "Update Comanda set Client_idClient= ?,Produs_idProdus=?,cantitateComanda=? where idComanda=?";
	private final static String deleteStatementString = "Delete from Comanda where idComanda=? ";

	public static Comanda cauta(int comandaId) {
		Comanda comanda = null;

		Connection dbConnection = ConectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = (PreparedStatement) dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, comandaId);
			rs = findStatement.executeQuery();
			rs.next();

			int clientIdClient = rs.getInt("Client_idClient");
			int produsIdProdus = rs.getInt("Produs_idProdus");
			int cantitate = rs.getInt("cantitateComanda");

			comanda = new Comanda(comandaId, clientIdClient, produsIdProdus, cantitate);
		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(rs);
			ConectionFactory.close(findStatement);
			ConectionFactory.close(dbConnection);
		}
		return comanda;
	}

	public static int insert(Comanda comanda) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = (PreparedStatement) dbConnection.prepareStatement(insertStatementString,
					Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, comanda.getClient_IdClient());
			insertStatement.setInt(2, comanda.getProdus_IdProdus());
			insertStatement.setInt(3, comanda.getCantitateComanda());

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

	public static void update(Comanda comanda, int idComanda) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement updateStatement = null;

		try {
			updateStatement = (PreparedStatement) dbConnection.prepareStatement(updateStatementString,
					Statement.RETURN_GENERATED_KEYS);
			updateStatement.setInt(1, comanda.getClient_IdClient());
			updateStatement.setInt(2, comanda.getProdus_IdProdus());
			updateStatement.setInt(3, comanda.getCantitateComanda());
			updateStatement.setInt(4, idComanda);
			updateStatement.executeUpdate();

		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(updateStatement);
			ConectionFactory.close(dbConnection);
		}

	}

	public static void delete(int idComanda) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = (PreparedStatement) dbConnection.prepareStatement(deleteStatementString,
					Statement.RETURN_GENERATED_KEYS);

			deleteStatement.setInt(1, idComanda);
			deleteStatement.executeUpdate();

		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(deleteStatement);
			ConectionFactory.close(dbConnection);
		}

	}

}
