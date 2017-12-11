package tp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTools {
	
	private String driverName ;
	private String url = "jdbc:mysql://dbs-perso.luminy.univmed.fr/b13000276";
	private String user = "b13000276";
	private String password = "fqqg=+:d";
	
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void init() {  }
	public void close() {  }
	
	public Connection newConnection() throws SQLException {
	    Connection conn = DriverManager.getConnection(url, user, password);
	    return conn;
	}
	
	public void quietClose(Connection c) throws SQLException {
		if(c!= null) c.close();
	}
	
//	public int executeUpdate(String query) throws SQLException { 
//		Connection conn =  null;
//		try{
//			conn = newConnection();
//			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery(query);
//	        while (rs.next()) {
//	            System.out.printf("%-20s \n", rs.getString(1));
//	        }
//		}catch(SQLException e){
//			return 0;
//		}
//		finally{
//			if (conn != null) quietClose(conn);
//		}
//		return 1;
//	}
	
	
	public int executeUpdate(String query) throws SQLException { 
		try (Connection conn = newConnection()){
			Statement st = conn.createStatement();
			int nb= st.executeUpdate(query);
	        quietClose(conn);
	        return nb;
		}catch(SQLException e){
			throw e;
		}
	}
	
}
