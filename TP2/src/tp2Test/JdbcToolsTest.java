package tp2Test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.*;

import tp2.JdbcTools;


public class JdbcToolsTest {
	
	private JdbcTools jdbc;
	
	@Before
	public void before() throws ClassNotFoundException{
		jdbc = new JdbcTools();
	}
	
	@After
	public void after(){
		
	}
	
	@Test//(expected = SQLException.class)
	public void testConnection() throws SQLException {
		Connection conn = jdbc.newConnection();
		Assert.assertNotNull(conn);
		
	}

}
