package model;

public class Produs {
	private int idProdus;
	private String numeProdus;
	private float pretProdus;
	
	public Produs() {
		
	}

	public Produs(String numeProdus, float pretProdus) {
		super();
		this.numeProdus = numeProdus;
		this.pretProdus = pretProdus;
	}

	public Produs(int idProdus, String numeProdus, float pretProdus) {
		super();
		this.idProdus = idProdus;
		this.numeProdus = numeProdus;
		this.pretProdus = pretProdus;
	}

	public String getNumeProdus() {
		return numeProdus;
	}

	public void setNumeProdus(String numeProdus) {
		this.numeProdus = numeProdus;
	}

	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int idProdus) {
		this.idProdus = idProdus;
	}

	public float getPretProdus() {
		return pretProdus;
	}

	public void setPretProdus(float pretProdus) {
		this.pretProdus = pretProdus;
	}

	@Override
	public String toString() {
		return "Produs [idProdus=" + idProdus + ", numeProdus=" + numeProdus + ", pretProdus=" + pretProdus + "]";
	}

}
