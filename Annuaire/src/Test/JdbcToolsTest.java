package Test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Database.JdbcTools;

public class JdbcToolsTest {
	
	private JdbcTools jdbc;
	
	
	@Before
	public void before() throws ClassNotFoundException{
		jdbc = new JdbcTools();
	}
	
	@After
	public void after(){
		
	}
	
	@Test
	public void testConnection() throws SQLException {
		Connection conn = jdbc.newConnection();
		Assert.assertNotNull(conn);
		
	}
	
	
	@Test
	public void testTable() throws SQLException{
		jdbc.newConnection();
		
	}
	

}