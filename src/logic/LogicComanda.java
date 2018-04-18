package logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.Client;
import model.Comanda;
import model.Produs;
import model.Stoc;
import reflectionDao.Client1Dao;
import reflectionDao.Comanda1Dao;
import reflectionDao.Produs1Dao;
import reflectionDao.Stoc1Dao;
import validare.ValidatorComanda;

public class LogicComanda {

	private Comanda1Dao comanda;
	private ValidatorComanda validator;
	private Client1Dao client;
	private Stoc1Dao stoc;
	private Produs1Dao produs;
	private static int idBon = 0;

	public LogicComanda() {
		comanda = new Comanda1Dao();
		validator = new ValidatorComanda();
		produs=new Produs1Dao();
		client = new Client1Dao();
		stoc = new Stoc1Dao();
	}

	public void insert(Comanda t) {

		if (validator.validare(t))
			comanda.insert(t);
		else
			JOptionPane.showMessageDialog(null, "Date incorecte", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	public void update(Comanda t) {
		System.out.println(validator.validare(t));
		if (validator.validare(t))
			comanda.update(t);
		else
			JOptionPane.showMessageDialog(null, "Date incorecte", "Alert", JOptionPane.ERROR_MESSAGE);

	}

	public void delete(Comanda t) {
		System.out.println(t.getIdComanda());
		comanda.delete(t.getIdComanda());
	}

	public void procesare_comanda(Comanda t) {
		if (t != null) {
			if (validator.validare(t)) {
				Client cl = client.findById(t.getClient_IdClient());
				Produs pro = produs.findById(t.getProdus_IdProdus());

				if (cl != null && pro != null) {
					Stoc st = stoc.findById(pro.getIdProdus());
					int cantitateStoc = st.getCantitateStoc();
					int cantitateComanda = t.getCantitateComanda();
					float pret = pro.getPretProdus() * (float) t.getCantitateComanda();
					if (cantitateStoc < cantitateComanda)
						JOptionPane.showMessageDialog(null, "Cantitate Indisponibila", "Indisponibil",
								JOptionPane.ERROR_MESSAGE);
					else {
						String message = "Pretul:" + pret + ", continuati operatiunea?";
						int a = JOptionPane.showConfirmDialog(null, message);
						if (a == JOptionPane.YES_OPTION) {
							String bon = "Bon" + idBon + ".txt";
							idBon++;
							String chitanta = "Nr Bucati:" + t.getCantitateComanda() + "\n" + pro.getNumeProdus() + "\n"
									+ "Total:" + pret;
							int nouaCantitate=st.getCantitateStoc()-t.getCantitateComanda();
							t.setCantitateComanda(nouaCantitate);
							comanda.update(t);
							File data = new File(bon);
							FileOutputStream file;

							try {
								file = new FileOutputStream(data);
								FilterOutputStream filter = new FilterOutputStream(file);

								byte b[] = chitanta.getBytes();
								filter.write(b);
								filter.flush();
								filter.close();
								file.close();
							} catch (IOException e) {

								e.printStackTrace();
							}
						}
						System.out.println("Success...");
					}
				}

			}
		}
	}

}
