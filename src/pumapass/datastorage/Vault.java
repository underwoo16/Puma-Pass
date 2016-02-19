/**
 * 
 */
package pumapass.datastorage;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 
 *
 */
public class Vault
{
	Connection c = null;
	
	public static void main(String[] args)
	{
		new Vault();
	}
	
	Vault()
	{
		this.init();
	}
	
	private void init()
	{
		try
		{
			this.connectToDatabase();
			this.checkManifest();
		}
		catch ( Exception e )
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		
	}
	
	private void connectToDatabase() throws Exception
	{
		Class.forName("org.sqlite.JDBC");
		this.c = DriverManager.getConnection("jdbc:sqlite:Vault.db");
	}
	
	private void checkManifest() throws Exception
	{
		Statement stmt = null;
	    Class.forName("org.sqlite.JDBC");
	    stmt = c.createStatement();
	    String sql = "CREATE TABLE IF NOT EXISTS MANIFEST " +
	                 "(PROFILENAME CHAR(100) PRIMARY KEY     NOT NULL," +
	                 " DATECREATED           DATE, " + 
	                 " DATEMODIFIED            DATE, " + 
	                 " TOTALEDITS        INT, " + 
	                 " PCHECK         CHAR(100))"; 
	    stmt.executeUpdate(sql);
	    stmt.close();
	}
	
	public ArrayList<String> getProfiles()
	{
	    Statement stmt = null;
	    ArrayList<String> ret = new ArrayList<String>();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT PROFILENAME FROM MANIFEST;" );
	      while ( rs.next() )
	      {
	         ret.add(rs.getString(0));
	      }
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	    
	    return ret;
	}
	
}
