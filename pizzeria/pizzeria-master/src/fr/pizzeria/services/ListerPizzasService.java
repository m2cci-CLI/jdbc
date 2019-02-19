package fr.pizzeria.services;

import java.sql.SQLException;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService {

	@Override
	public void executeUC(Scanner scanner, IPizzaDao dao) throws SQLException {
		System.out.println("Lister les pizzas");
		for (Pizza pizza: dao.findAllPizzas()){
			System.out.println(pizza);
		}

	}

}
