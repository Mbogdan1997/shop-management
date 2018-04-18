package logic;

import javax.swing.JOptionPane;

import model.Produs;
import model.Stoc;
import reflectionDao.Produs1Dao;
import reflectionDao.Stoc1Dao;
import validare.ValidatorProdus;

public class LogicProdus {
	Produs1Dao produs;
	ValidatorProdus validator;
	Stoc1Dao stoc;

	public LogicProdus() {
		produs = new Produs1Dao();
		validator = new ValidatorProdus();
		stoc=new Stoc1Dao();
	}

	public void insert(Produs t) {
		// System.out.println(t);
		// System.out.println(validator.validare(t));
		if (validator.validare(t))
			{produs.insert(t);
			stoc.insert(new Stoc(t.getIdProdus(),0));
			}
		else
			JOptionPane.showMessageDialog(null, "Date incorect", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	public void update(Produs t) {
		System.out.println(validator.validare(t));
		if (validator.validare(t))
			produs.update(t);
		else
			JOptionPane.showMessageDialog(null, "Date incorect", "Alert", JOptionPane.ERROR_MESSAGE);

	}

	public void delete(Produs t) {
		System.out.println(t.getIdProdus());
		produs.delete(t.getIdProdus());
	}

}
