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

	
	public static boolean checkManifest()
	{
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			// connect to DB
			c = DriverManager.getConnection("jdbc:sqlite:data.db");
			
			// create and exec statement
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS MANIFEST" +
					"(PROFILENAME VARCHAR PRIMARY KEY NOT NULL," +
					" TOTALEDITS INT NOT NULL, " + 
					" PCHECK VARCHAR NOT NULL)"; 
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return false;
		}	
		return true;
	}
	
	public static ArrayList<String> getProfileList()
	{
		ArrayList<String> pList = new ArrayList<>();
	    Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:data.db");
	    	c.setAutoCommit(false);
	    	
	    	stmt = c.createStatement();
	    	ResultSet rs = stmt.executeQuery( "SELECT PROFILENAME FROM MANIFEST;" );
	    	
	    	while (rs.next()) 
	    	{
	    		pList.add(rs.getString(1));
	    	}
	    	
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    } 
	    catch (Exception e)
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	return null;
	    }
		return pList;
	}
	
	public static boolean checkProfileExists(final String str)
	{
		String res;
	    Connection c = null;
	    Statement stmt = null;
	    try {
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:data.db");
	    	c.setAutoCommit(false);
	    	
	    	stmt = c.createStatement();
	    	ResultSet rs = stmt.executeQuery( "SELECT PROFILENAME FROM MANIFEST"
	    									+ "WHERE PROFILENAME = '" + str + "' ;" );
	    	
	    	res = rs.getString(1);	    	
	    	
	    	rs.close();
	    	stmt.close();
	    	c.close();
	    } 
	    catch (Exception e)
	    {
	    	System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	    	return false;
	    }
	    if (res != null)
	    	return true;
	    else
	    	return false;
	}
	
	public static boolean createProfile(final String pName, final String pCheck)
	{
		Connection c = null;
		Statement stmt = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
		    c = DriverManager.getConnection("jdbc:sqlite:data.db");
		    
		    
		    /// Create entry in manifest
		    c.setAutoCommit(false);
		    
		    stmt = c.createStatement();
		    String sql = "INSERT INTO MANIFEST (PROFILENAME, TOTALEDITS, PCHECK) " +
		                 "VALUES (" + pName + ", 0, " + pCheck + ");"; 
		    stmt.executeUpdate(sql);

		    stmt.close();
		    c.commit();
		    
		    
		    /// create table for the profile
		    stmt = c.createStatement();
			sql = "CREATE TABLE IF NOT EXISTS "+ pName +
					"(SITE VARCHAR NOT NULL," +
					" USERNAME VARCHAR NOT NULL, " + 
					" PASS VARCHAR NOT NULL PRIMARY KEY(SITE, USERNAME, PASS))"; 
			stmt.executeUpdate(sql);
			stmt.close();
		    
		    c.close();
		}
		catch ( Exception e )
		{
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    return false;
		}
		return true;
	}
	
	
	
	
	
}
