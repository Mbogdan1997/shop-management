package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import conection.ConectionFactory;
import model.Stoc;

public class StocDao {
	private static final String insertStatementString = "INSERT INTO Stoc (Produs_idProdus,cantitateStoc)"
			+ " VALUES (?,?)";
	private final static String findStatementString = "SELECT * FROM Stoc where Produs_idProdus = ?";
	private final static String updateStatementString = "Update Stoc set cantitateStoc=? where Produs_idProdus=?";
	private final static String deleteStatementString = "Delete from Stoc where Produs_idProdus=? ";

	public static Stoc cauta(int produsId) {
		Stoc stoc = null;

		Connection dbConnection = ConectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = (PreparedStatement) dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, produsId);
			rs = findStatement.executeQuery();
			rs.next();

			// int idProdus = rs.getInt("Produs_idProdus");
			int cantitateStoc = rs.getInt("cantitateStoc");
			// int cantitate = rs.getInt("cantitateComanda");

			stoc = new Stoc(produsId, cantitateStoc);
		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(rs);
			ConectionFactory.close(findStatement);
			ConectionFactory.close(dbConnection);
		}
		return stoc;
	}

	public static void insert(Stoc stoc) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement insertStatement = null;

		try {
			insertStatement = (PreparedStatement) dbConnection.prepareStatement(insertStatementString,
					Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, stoc.getProdus_IdProdus());
			insertStatement.setInt(2, stoc.getCantitateStoc());

			insertStatement.executeUpdate();

		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(insertStatement);
			ConectionFactory.close(dbConnection);
		}

	}

	public static void update(Stoc stoc, int idProdus) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement updateStatement = null;

		try {
			updateStatement = (PreparedStatement) dbConnection.prepareStatement(updateStatementString,
					Statement.RETURN_GENERATED_KEYS);
			updateStatement.setInt(1, stoc.getCantitateStoc());
			// updateStatement.setFloat(2, stoc.getPretProdus());
			// updateStatement.setInt(3, produs.getCantitateComanda());
			updateStatement.setInt(2, idProdus);
			updateStatement.executeUpdate();

		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(updateStatement);
			ConectionFactory.close(dbConnection);
		}

	}

	public static void delete(int idProdus) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement deleteStatement = null;

		try {
			deleteStatement = (PreparedStatement) dbConnection.prepareStatement(deleteStatementString,
					Statement.RETURN_GENERATED_KEYS);

			deleteStatement.setInt(1, idProdus);
			deleteStatement.executeUpdate();

		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(deleteStatement);
			ConectionFactory.close(dbConnection);
		}

	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(cauta(1));
		Stoc stoc = new Stoc(3, 120);
		insert(stoc);
		Stoc stoc1 = new Stoc(25);
		update(stoc1, 1);
		delete(1);
	}*/
}
