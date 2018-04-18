package validare;

import model.Comanda;
import reflectionDao.Client1Dao;
import reflectionDao.Comanda1Dao;
import reflectionDao.Produs1Dao;

public class ValidatorComanda implements Validator<Comanda> {
	Client1Dao client;
	Comanda1Dao comanda;
	Produs1Dao produs;

	public ValidatorComanda() {
		client = new Client1Dao();
		comanda=new Comanda1Dao();
		produs=new Produs1Dao();
	}

	@Override
	public boolean validare(Comanda t) {
		// TODO Auto-generated method stub
		if (t.getCantitateComanda() <= 0)
			return false;
		if (t.getClient_IdClient() >= 0) {
			if (client.findById(t.getClient_IdClient()) == null)
				return false;
		} else
			return false;
		if (t.getProdus_IdProdus() >= 0) {
			if (produs.findById(t.getProdus_IdProdus()) == null)
				return false;
		} else
			return false;
		return true;
	}

}
