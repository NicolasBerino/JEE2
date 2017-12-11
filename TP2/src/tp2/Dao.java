package tp2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;

import tp2Exception.DaoException;

public class Dao extends JdbcTools{

	public Collection<Person> findPersons() throws DaoException { 
		try (Connection conn = newConnection()){
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("");
		// 2. preparer l'instruction
		// 3. executer la requ^ete
		// 4. lire le resultat
		} catch (SQLException e) {
		// 5. construire l'exception DAO
		// 6. renvoyer cette exception
		}
		} finally {
		// 7. fermer la connexion
		}
	
}
