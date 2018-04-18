package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import conection.ConectionFactory;
//import model.Comanda;
import model.Produs;

public class ProdusDao {
	private static final String insertStatementString = "INSERT INTO Produs (numeProdus,pretProdus)" + " VALUES (?,?)";
	private final static String findStatementString = "SELECT * FROM Produs where idProdus = ?";
	private final static String updateStatementString = "Update Produs set numeProdus= ?,pretProdus=? where idProdus=?";
	private final static String deleteStatementString = "Delete from Produs where idProdus=? ";

	public static Produs cauta(int produsId) {
		Produs produs = null;

		Connection dbConnection = ConectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = (PreparedStatement) dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, produsId);
			rs = findStatement.executeQuery();
			rs.next();

			String numeProdus = rs.getString("numeProdus");
			float pretProdus = rs.getFloat("pretProdus");
			// int cantitate = rs.getInt("cantitateComanda");

			produs = new Produs(produsId, numeProdus, pretProdus);
		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(rs);
			ConectionFactory.close(findStatement);
			ConectionFactory.close(dbConnection);
		}
		return produs;
	}

	public static int insert(Produs produs) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = (PreparedStatement) dbConnection.prepareStatement(insertStatementString,
					Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, produs.getNumeProdus());
			insertStatement.setFloat(2, produs.getPretProdus());

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

	public static void update(Produs produs, int idProdus) {
		Connection dbConnection = ConectionFactory.getConnection();

		PreparedStatement updateStatement = null;

		try {
			updateStatement = (PreparedStatement) dbConnection.prepareStatement(updateStatementString,
					Statement.RETURN_GENERATED_KEYS);
			updateStatement.setString(1, produs.getNumeProdus());
			updateStatement.setFloat(2, produs.getPretProdus());
			// updateStatement.setInt(3, produs.getCantitateComanda());
			updateStatement.setInt(3, idProdus);
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(cauta(1));
		Produs produs = new Produs("Pomelo", (float) 2.2);
		System.out.println(insert(produs));
		Produs produs1 = new Produs("Ananas", (float) 3.5);
		update(produs1, 2);
		delete(2);

	}

}
