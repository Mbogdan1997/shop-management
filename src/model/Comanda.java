package model;

public class Comanda {
	private int idComanda;
	private int client_IdClient;
	private int produs_IdProdus;
	private int cantitateComanda;

	public Comanda() {

	}

	public Comanda(int clientIdClient, int produsIdProdus, int cantitateComanda) {
		super();
		this.client_IdClient = clientIdClient;
		this.produs_IdProdus = produsIdProdus;
		this.cantitateComanda = cantitateComanda;
	}

	public Comanda(int idComanda, int clientIdClient, int produsIdProdus, int cantitateComanda) {
		super();
		this.idComanda = idComanda;
		this.client_IdClient = clientIdClient;
		this.produs_IdProdus = produsIdProdus;
		this.cantitateComanda = cantitateComanda;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int idComanda) {
		this.idComanda = idComanda;
	}

	public int getClient_IdClient() {
		return client_IdClient;
	}

	public void setClient_IdClient(int clientIdClient) {
		this.client_IdClient = clientIdClient;
	}

	public int getProdus_IdProdus() {
		return produs_IdProdus;
	}

	public void setProdus_IdProdus(int produsIdProdus) {
		this.produs_IdProdus = produsIdProdus;
	}

	public int getCantitateComanda() {
		return cantitateComanda;
	}

	public void setCantitateComanda(int cantitateComanda) {
		this.cantitateComanda = cantitateComanda;
	}

	public String toString() {
		return "Comanda [idComanda=" + idComanda + ", clientIdClient=" + client_IdClient + ", produsIdProdus="
				+ produs_IdProdus + ", cantitateComanda=" + cantitateComanda + "]";
	}

}
