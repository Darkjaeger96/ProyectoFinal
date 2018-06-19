package modelo;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

public class Conexion {

	private static Connection conexion;

	private Conexion () {

		Properties properties = new Properties();
		
		try {
			properties.load(new FileReader("bd/bd.properties"));

			String BD = properties.getProperty("BD");

			String DRIVER = properties.getProperty("DRIVER");

			String DB_DIR = properties.getProperty("DB_DIR");

			Class.forName(DRIVER);
			SQLiteConfig sqLiteConfig = new SQLiteConfig();
			sqLiteConfig.enforceForeignKeys(true);
			conexion = DriverManager.getConnection(
					DB_DIR + BD, sqLiteConfig.toProperties());


		} catch (IOException | SQLException | ClassNotFoundException e) {
			System.out.println("Error, sin conexión");
			e.printStackTrace();
		}
	}

	public static Connection getConexion() {
		if (conexion == null) {
			new Conexion();
			Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		}
		return conexion; 

	}

	static class ShutdownHook extends Thread{
		
		@Override
		public void run() {
			if (conexion != null)
				try {
					conexion.getMetaData();
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}
