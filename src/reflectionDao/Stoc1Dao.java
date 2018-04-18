package reflectionDao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import conection.ConectionFactory;
import model.Stoc;

public class Stoc1Dao extends AbstractDao<Stoc> {
	private String createInsertQuery(Stoc clasa) {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> strings = new ArrayList<String>();
		sb.append("Insert Into ");
		sb.append(clasa.getClass().getSimpleName());
		sb.append("(");
		try {

			for (Field field : clasa.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				strings.add(field.getName());

			}
			// strings.remove(0);
			for (int i = 0; i < strings.size(); i++) {
				sb.append(strings.get(i));
				if (i + 1 < strings.size())
					sb.append(",");
			}

			sb.append(")");
			sb.append(" Values(");
			System.out.println(sb.toString());
			for (int i = 0; i < strings.size(); i++) {// sb.append(strings.get(i));
				sb.append("?");
				if (i + 1 < strings.size())
					sb.append(",");
			}

			sb.append(")");
			System.out.println(sb.toString());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public Stoc insert(Stoc t) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Object> objects = new ArrayList<Object>();
		String query = createInsertQuery(t);
		System.out.println(query);
		try {
			connection = ConectionFactory.getConnection();
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			
			for (Field field : t.getClass().getDeclaredFields()) {
				
				field.setAccessible(true);
			
					Object value = field.get(t);
					System.out.println(value.toString());
					objects.add(value);
		
			}

			for (int i = 0; i < objects.size(); i++) {// objects.get(i);
				statement.setString((i + 1), String.valueOf(objects.get(i)));
			}
			System.out.println(statement.toString());
			statement.executeUpdate();

			return t;
		} catch (SQLException e) {
			System.out.println("lalals");// printf("lalalala");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConectionFactory.close(resultSet);

		}

		return null;
	}
	
	/*public static void main(String[] args) {
		Stoc stoc=new Stoc(1,40);
		Stoc1Dao stoc1=new Stoc1Dao();
		stoc1.insert(stoc);
		stoc1.update(stoc);
		stoc1.delete(1);
		System.out.println(stoc1.findById(3));
		ArrayList<String> al=new ArrayList<String>(stoc1.generateColumnName());
		for(String s:al) {
			System.out.println(s);
		}
		ArrayList<Stoc> stoc2=new ArrayList<Stoc>(stoc1.findAll());
		for(Stoc s:stoc2) {
			System.out.println(s);
		}
	}
	*/

}
