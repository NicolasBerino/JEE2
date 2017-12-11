package monpkg.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class MessageManager {

	@Resource(name = "myDS")
	private DataSource ds;


    public void addMessage(String message) throws SQLException {
        Connection c = ds.getConnection();
        Statement st = c.createStatement();
        st.execute("CREATE TABLE IF NOT EXISTS MSG(TEXTE VARCHAR(30))");
        st.execute("INSERT INTO MSG(TEXTE) VALUES('" + message + "')");
    }

}