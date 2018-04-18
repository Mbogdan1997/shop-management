/**
 * Clasa Main contine doar metoda statica main

 * @author Bogdan
 *
 */
package main;
/**
 * Pachetul main contine doar clasa Main
 */

import presentation.View;

public class Main {
	/**
	 * In clasa main este instantiat un obiect de tip View care extinde JFrame si este facut vizibil
	 * @param args
	 */
	public static void main(String[] args) {

		View view = new View();
		view.setVisible(true);

	}

}
