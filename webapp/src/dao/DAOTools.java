package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOTools {

	/* Silent close of the ResultSet */
	public static void silentClose(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Fail to close the ResultSet : " + e.getMessage());
			}
		}
	}

	/* Silent close of the Statement */
	public static void silentClose(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.out.println("Fail to close the Statement : " + e.getMessage());
			}
		}
	}

	/* Silent close of the connection */
	public static void silentClose(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Fail to close the connection : " + e.getMessage());
			}
		}
	}

	/* Silent close of the connection and the statement */
	public static void silentCloses(Statement statement, Connection connexion) {
		silentClose(statement);
		silentClose(connexion);
	}

	/* Silent close of the connection, the statement and the resultSet */
	public static void silentCloses(ResultSet resultSet, Statement statement, Connection connexion) {
		silentClose(resultSet);
		silentClose(statement);
		silentClose(connexion);
	}

	/*
	 * Initialize preparedStatement with the connection connection, the SQL
	 * query sql and the objects objects.
	 */
	public static PreparedStatement initializePreparedStatement(Connection connection, String sql,
			boolean returnGeneratedKeys, Object... objects) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql,
				returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for (int i = 0; i < objects.length; i++) {
			preparedStatement.setObject(i + 1, objects[i]);
		}
		return preparedStatement;
	}
}