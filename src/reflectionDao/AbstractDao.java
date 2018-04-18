package reflectionDao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import conection.ConectionFactory;

public class AbstractDao<T> {
	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	private String createSelectAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("Select * from ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}

	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	private String createDeleteQuery() {
		StringBuffer sb = new StringBuffer();
		Field field = type.getDeclaredFields()[0];
		sb.append("Delete from ");
		sb.append(type.getSimpleName());
		sb.append(" where ");
		sb.append(field.getName());
		sb.append("=?");
		return sb.toString();
	}

	private String createUpdateQuery(T clasa) {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> strings = new ArrayList<String>();
		sb.append("Update ");
		sb.append(type.getSimpleName());
		sb.append(" Set ");
		try {
			for (Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				strings.add(field.getName());

			}
			strings.remove(0);
			for (int i = 0; i < strings.size(); i++) {
				sb.append(strings.get(i));
				sb.append("=?");
				if (i + 1 < strings.size())
					sb.append(",");
			}

			sb.append(" where ");
			Field field = type.getDeclaredFields()[0];
			sb.append(field.getName());
			sb.append("=?");
			System.out.println(sb.toString());

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		System.out.println(sb.toString());

		return sb.toString();
	}

	public String getInsertStatement(T clasa) {
		return createInsertQuery(clasa);
	}

	private String createInsertQuery(T clasa) {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> strings = new ArrayList<String>();
		sb.append("Insert Into ");
		sb.append(type.getSimpleName());
		sb.append("(");
		try {
			boolean ok = false;
			for (Field field : type.getDeclaredFields()) {
				if (ok) {
					field.setAccessible(true);
					strings.add(field.getName());
				}
				ok = true;
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

	public ArrayList<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectAll();// createSelectQuery(type.getDeclaredFields()[0].getName());
		try {
			connection = ConectionFactory.getConnection();
			statement = connection.prepareStatement(query);

			resultSet = statement.executeQuery();

			ArrayList<T> list = new ArrayList<T>();
			list = createObjects(resultSet);
			if (list.isEmpty())
				return null;
			else
				return list;

		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(resultSet);

		}
		return null;

	}

	public String[] generateColumnName() {

		Field[] fields = type.getDeclaredFields();
		int size = fields.length;
		String[] strings = new String[size];

		for (int i = 0; i < size; i++) {
			strings[i] = fields[i].getName();
			System.out.println(strings[i]);
		}

		return strings;
	}

	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery(type.getDeclaredFields()[0].getName());
		try {
			connection = ConectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			List<T> list = new ArrayList<T>();
			list = createObjects(resultSet);
			if (list.isEmpty())
				return null;
			else
				return list.get(0);

		} catch (SQLException e) {

		} finally {
			ConectionFactory.close(resultSet);

		}
		return null;
	}

	private ArrayList<T> createObjects(ResultSet resultSet) {
		ArrayList<T> list = new ArrayList<T>();
		T instance;
		try {
			while (resultSet.next()) {

				instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
					System.out.println(instance);
				}
				if (instance != null)
					list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Object[][] getMatrix() {
		ArrayList<T> list = this.findAll();
		T instance;
		try {
			instance = type.newInstance();
			list.add(instance);
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int nrLinii = list.size();
		Field fields[] = type.getDeclaredFields();
		int nrCol = fields.length;
		Object[][] objects = new Object[nrLinii][nrCol];

		for (int i = 0; i < nrLinii; i++)
			for (int j = 0; j < nrCol; j++)
				try {
					//System.out.println("Aici");
					fields[j].setAccessible(true);
					objects[i][j] = fields[j].get(list.get(i));
					System.out.println(objects[i][j]);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//for(int j=0;j<nrCol;j++)
			//objects[nrLinii][j]="Add New";

		return objects;

	}

	public T insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Object> objects = new ArrayList<Object>();
		String query = createInsertQuery(t);
		System.out.println(query);
		try {
			connection = ConectionFactory.getConnection();
			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			boolean ok = false;
			for (Field field : type.getDeclaredFields()) {
				field.setAccessible(true);
				if (ok) {
					Object value = field.get(t);
					System.out.println(value.toString());
					objects.add(value);
				}
				ok = true;
			}

			for (int i = 0; i < objects.size(); i++) {// objects.get(i);
				statement.setString((i + 1), String.valueOf(objects.get(i)));
			}
			System.out.println(statement.toString());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				int insertedId = rs.getInt(1);
				Field field = type.getDeclaredFields()[0];
				field.setAccessible(true);
				field.set(t, insertedId);
			}

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

	public T update(T t) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ArrayList<Object> objects = new ArrayList<Object>();
		String query = createUpdateQuery(t);
		try {
			connection = ConectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			for (Field field : type.getDeclaredFields()) {
				field.setAccessible(true);

				Object value = field.get(t);
				System.out.println(value.toString());
				objects.add(value);
			}
			objects.remove(0);
			for (int i = 0; i < objects.size(); i++) {// objects.get(i);
				statement.setString((i + 1), String.valueOf(objects.get(i)));
			}
			Field field = type.getDeclaredFields()[0];
			field.setAccessible(true);
			Object value = field.get(t);
			statement.setString(objects.size() + 1, String.valueOf(value));
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

	public void delete(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		// ResultSet resultSet = null;
		System.out.println("aici");
		String query = createDeleteQuery();
		try {
			System.out.println(query);
			connection = ConectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setString(1, String.valueOf(id));
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}
