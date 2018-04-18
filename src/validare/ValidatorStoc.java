package validare;

import reflectionDao.Produs1Dao;
//import reflectionDao.Stoc1Dao;
import model.Stoc;

public class ValidatorStoc implements Validator<Stoc> {
	private Produs1Dao produs;
	//private Stoc1Dao stoc;
	

	public ValidatorStoc() {
		produs = new Produs1Dao();
	}

	@Override
	public boolean validare(Stoc t) {
		// TODO Auto-generated method stub
		boolean ok = true;
		if (t.getCantitateStoc() < 0)
			ok = false;
		if (produs.findById(t.getProdus_IdProdus()) == null)
			ok = false;
		return ok;
	}

}
