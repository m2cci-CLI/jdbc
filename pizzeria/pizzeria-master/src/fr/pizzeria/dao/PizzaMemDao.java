package fr.pizzeria.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.mysql.jdbc.Connection;

import fr.pizzeria.model.Pizza;

public class PizzaMemDao implements IPizzaDao {

	private List<Pizza> pizzas = new ArrayList<Pizza>();
	private String url="jdbc:mysql://localhost:3306/pizza";
	
	public PizzaMemDao() throws SQLException, IOException{
	
		Properties prop = new Properties();
		InputStream input = null;
		

		try {
			input = new FileInputStream("ressources/jdbc.properties");

			prop.load(input);
			this.url = prop.getProperty("url");
		} catch (IOException ex) {
			ex.printStackTrace();
		}




		     
		  
	}

	public List<Pizza> findAllPizzas() throws SQLException {
		Connection conn = null;
		try {
			conn = (Connection) DriverManager.getConnection(this.url,"root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "SELECT * FROM Pizza"; 
		Statement state = conn.createStatement();
		ResultSet resultats = state.executeQuery(sql);
		ArrayList<Pizza> pizzaresult = new ArrayList<Pizza>();
		while(resultats.next()) {
		pizzaresult.add(new Pizza(resultats.getInt("id"),resultats.getString("libelle"),resultats.getString("code"),resultats.getDouble("prix")));
		}
	
		
		return pizzaresult;
	}

	public void saveNewPizza(Pizza pizza) {
		Connection conn = null;
		Pizza pizza1;
		try
	    {
	      
	      Connection conn1 = (Connection) DriverManager.getConnection(this.url, "root", "");
	    
	      

	      // the mysql insert statement
	      String query = " insert into pizza (code, libelle, prix)"
	        + " values (?, ?, ?)";

	      // create the mysql insert preparedstatement
	      PreparedStatement preparedStmt = conn1.prepareStatement(query);
	      preparedStmt.setString (1, pizza.getCode());
	      preparedStmt.setString (2, pizza.getLibelle());
	      preparedStmt.setDouble(3, pizza.getPrix());

	      // execute the preparedstatement
	      preparedStmt.execute();
	      
	      conn1.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception!");
	      System.err.println(e.getMessage());
	    }
	  }
	

	

	public void updatePizza(String codePizza, Pizza pizza) {
		for (Pizza p: pizzas){
			if (p.getCode().equals(codePizza)){
				Connection conn = null;

				try
			    {
			      
			      Connection conn1 = (Connection) DriverManager.getConnection(this.url, "root", "");
			    
			      

			      // the mysql  statement
			      String query = "UPDATE pizza SET(code, libelle, prix)"
			        + " values (?, ?, ?)";

			      // create the mysql insert preparedstatement
			      PreparedStatement preparedStmt = conn1.prepareStatement(query);
			      preparedStmt.setString (1, pizza.getCode());
			      preparedStmt.setString (2, pizza.getLibelle());
			      preparedStmt.setDouble(3, pizza.getPrix());

			      // execute the preparedstatement
			      preparedStmt.execute();
			      
			      conn1.close();
			    }
			    catch (Exception e)
			    {
			      System.err.println("Got an exception!");
			      System.err.println(e.getMessage());
			    }
			}}
	}

	public void deletePizza(String codePizza) {
		Iterator<Pizza> it = pizzas.iterator();
		while (it.hasNext()){
			Pizza p = it.next();
			if (p.getCode().equals(codePizza)){
			

				try
			    {
			      
			      Connection conn1 = (Connection) DriverManager.getConnection(this.url, "root", "");
			    
			      

			      // the mysql  statement
			      String query = "delete from pizza where code = ?";
			        

			      PreparedStatement preparedStmt = conn1.prepareStatement(query);
			      preparedStmt.setString(1,codePizza);

			      // execute the preparedstatement
			      preparedStmt.execute();
			      
			      conn1.close();
			    }
			    catch (Exception e)
			    {
			      System.err.println("Got an exception!");
			      System.err.println(e.getMessage());
			    }
			}}}
				
			
		

	public Pizza findPizzaByCode(String codePizza) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}

}
