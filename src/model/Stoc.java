package model;

public class Stoc {
	private int produs_IdProdus;
	private int cantitateStoc;
	
	public Stoc() {
		
	}
	public Stoc(int cantitateStoc) {
		super();
		this.cantitateStoc = cantitateStoc;
	}

	public Stoc(int produsIdProdus, int cantitateStoc) {
		super();
		this.produs_IdProdus = produsIdProdus;
		this.cantitateStoc = cantitateStoc;
	}

	public int getProdus_IdProdus() {
		return produs_IdProdus;
	}

	public void setProdus_IdProdus(int produsIdProdus) {
		this.produs_IdProdus = produsIdProdus;
	}

	public int getCantitateStoc() {
		return cantitateStoc;
	}

	public void setCantitateStoc(int cantitateStoc) {
		this.cantitateStoc = cantitateStoc;
	}

	@Override
	public String toString() {
		return "Stoc [produsIdProdus=" + produs_IdProdus + ", cantitateStoc=" + cantitateStoc + "]";
	}

}
