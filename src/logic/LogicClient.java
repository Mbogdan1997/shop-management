package logic;

import javax.swing.JOptionPane;

import model.Client;
import reflectionDao.Client1Dao;
import validare.ValidatorClient;

public class LogicClient {
	Client1Dao client;
	ValidatorClient validator;

	public LogicClient() {
		client = new Client1Dao();
		validator = new ValidatorClient();
	}

	public void insert(Client t) {
		if (validator.validare(t))
			client.insert(t);
		else
			JOptionPane.showMessageDialog(null, "Date incorect", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	public void update(Client t) {
		System.out.println(validator.validare(t));
		if (validator.validare(t))
			client.update(t);
		else
			JOptionPane.showMessageDialog(null, "Date incorect", "Alert", JOptionPane.ERROR_MESSAGE);

	}

	public void delete(Client t) {
		System.out.println(t.getId());
		client.delete(t.getId());
	}

	

}
