package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import Exception.DaoException;
import JavaBean.Group;
import JavaBean.Person;

public class Dao implements PersonDao{
	
	JdbcTools jdbc;
	
	public Collection<Person> findPersons() throws DaoException { 
		try (Connection conn = jdbc.newConnection()){
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("");
			st.executeQuery("");
			ResultSet qt = st.executeQuery("test");
			qt.
		} catch (SQLException e) {
		}
		 finally {
		}
		return null;
}

	@Override
	public Collection<Group> findAllGroups() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Person> findAllPersons(long groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findPerson(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void savePerson(Person p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGroup(Group g) {
		// TODO Auto-generated method stub
		
	}
	
	

}	
