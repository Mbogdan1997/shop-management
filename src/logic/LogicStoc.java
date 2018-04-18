package logic;

import javax.swing.JOptionPane;

import model.Stoc;
import reflectionDao.Stoc1Dao;
import validare.ValidatorStoc;

public class LogicStoc {
	Stoc1Dao stoc;
	ValidatorStoc validator;

	public LogicStoc() {
		stoc = new Stoc1Dao();
		validator = new ValidatorStoc();
	}

	public void insert(Stoc t) {

		if (validator.validare(t))
			stoc.insert(t);
		else
			JOptionPane.showMessageDialog(null, "Date incorect", "Alert", JOptionPane.ERROR_MESSAGE);
	}

	public void update(Stoc t) {
		System.out.println(validator.validare(t));
		if (validator.validare(t))
			stoc.update(t);
		else
			JOptionPane.showMessageDialog(null, "Date incorect", "Alert", JOptionPane.ERROR_MESSAGE);

	}

	public void delete(Stoc t) {
		System.out.println(t.getProdus_IdProdus());
		stoc.delete(t.getProdus_IdProdus());
	}

}
