package model;

public class Client {
	private int id;
	private String nume;
	private String email;
	private String adresa;


	public Client() {
		
	}
	public Client(String numeClient, String emailClient, String adresa) {
		super();
		this.nume = numeClient;
		this.email = emailClient;
		this.adresa = adresa;
	}
	public Client(int idClient, String numeClient, String emailClient, String adresa) {
		super();
		this.id = idClient;
		this.nume = numeClient;
		this.email = emailClient;
		this.adresa = adresa;
	}

	public int getId() {
		return id;
	}

	public void setId(int idClient) {
		this.id = idClient;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String numeClient) {
		this.nume = numeClient;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String emailClient) {
		this.email = emailClient;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@Override
	public String toString() {
		return "Client [idClient=" + id + ", numeClient=" + nume + ", emailClient=" + email
				+ ", adresaClient=" + adresa + "]";
	}

}
