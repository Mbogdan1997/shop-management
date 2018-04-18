package validare;

import model.Produs;

public class ValidatorProdus implements Validator<Produs> {
	private boolean validareNumeProdus(Produs p) {
		return p.getNumeProdus().matches("[A-Z][a-zA-Z]*");
	}

	@Override
	public boolean validare(Produs t) {
		// TODO Auto-generated method stub

		if (!validareNumeProdus(t))
			return false;

		if (t.getPretProdus() <= 0)
			return false;

		return true;
	}

}
