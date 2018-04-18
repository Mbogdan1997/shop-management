package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import reflectionDao.Client1Dao;
import reflectionDao.Comanda1Dao;
import reflectionDao.Produs1Dao;
import reflectionDao.Stoc1Dao;
import javax.swing.JButton;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private Table tableClient;
	private Table tableProdus;
	private Table tableStoc;
	private TableComanda tableComanda;
	private Client1Dao client1Dao;
	private Produs1Dao produs1Dao;
	private Stoc1Dao stoc1Dao;
	private Comanda1Dao comanda1Dao;
	private Controller controller;
	private JButton btnClienti;
	private JButton btnProduse;
	private JButton btnComenzi;
	private JButton btnStocProduse;

	public View() {
		tableStoc = new Table();
		tableProdus = new Table();
		tableClient = new Table();
		tableComanda = new TableComanda();
		client1Dao = new Client1Dao();
		produs1Dao = new Produs1Dao();
		stoc1Dao = new Stoc1Dao();
		comanda1Dao = new Comanda1Dao();
		controller = new Controller(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnClienti = new JButton("Clienti");

		btnClienti.setBounds(29, 23, 250, 38);
		contentPane.add(btnClienti);

		btnProduse = new JButton("Produse");
		btnProduse.setBounds(29, 82, 250, 38);
		contentPane.add(btnProduse);

		btnComenzi = new JButton("Comenzi");
		btnComenzi.setBounds(29, 144, 250, 38);
		contentPane.add(btnComenzi);

		btnStocProduse = new JButton("Stoc Produse");
		btnStocProduse.setBounds(29, 213, 250, 38);
		contentPane.add(btnStocProduse);

		btnClienti.addActionListener(controller);
		btnProduse.addActionListener(controller);
		btnComenzi.addActionListener(controller);
		btnStocProduse.addActionListener(controller);
	}

	public void generateTableComanda() {
		String[] strings = comanda1Dao.generateColumnName();
		Object[][] objects = comanda1Dao.getMatrix();
		if (tableComanda != null)
			tableComanda.setVisible(false);
		tableComanda = new TableComanda(objects, strings);
		tableComanda.getSelect().addListSelectionListener(controller);
		tableComanda.getBtnDelete().addActionListener(controller);
		tableComanda.getBtnUpdate().addActionListener(controller);
		tableComanda.getBtnInsert().addActionListener(controller);
		tableComanda.getBtnProcesare().addActionListener(controller);
		tableComanda.setVisible(true);
	}

	public void generateTableClienti() {

		String[] strings = client1Dao.generateColumnName();
		Object[][] objects = client1Dao.getMatrix();
		if (tableClient != null)
			tableClient.setVisible(false);
		tableClient = new Table(objects, strings);
		tableClient.getSelect().addListSelectionListener(controller);
		tableClient.getBtnDelete().addActionListener(controller);
		tableClient.getBtnUpdate().addActionListener(controller);
		tableClient.getBtnInsert().addActionListener(controller);
		tableClient.setVisible(true);

	}

	public void generateTableProduse() {

		String[] strings = produs1Dao.generateColumnName();
		Object[][] objects = produs1Dao.getMatrix();
		if (tableProdus != null)
			tableProdus.setVisible(false);
		tableProdus = new Table(objects, strings);
		tableProdus.getSelect().addListSelectionListener(controller);
		tableProdus.getBtnDelete().addActionListener(controller);
		tableProdus.getBtnUpdate().addActionListener(controller);
		tableProdus.getBtnInsert().addActionListener(controller);
		tableProdus.setVisible(true);

	}

	public void generateTableStoc() {

		String[] strings = stoc1Dao.generateColumnName();
		Object[][] objects = stoc1Dao.getMatrix();
		if (tableProdus != null)
			tableProdus.setVisible(false);
		tableStoc = new Table(objects, strings);
		tableStoc.getSelect().addListSelectionListener(controller);
		tableStoc.getBtnDelete().addActionListener(controller);
		tableStoc.getBtnUpdate().addActionListener(controller);
		tableStoc.getBtnInsert().addActionListener(controller);
		tableStoc.setVisible(true);

	}

	public Table getTableClient() {
		return tableClient;
	}

	public Table getTableProdus() {
		return tableProdus;
	}

	public Table getTableStoc() {
		return tableStoc;
	}

	public TableComanda getTableComanda() {
		return tableComanda;
	}

	public JButton getBtnClienti() {
		return btnClienti;
	}

	public JButton getBtnProduse() {
		return btnProduse;
	}

	public JButton getBtnComenzi() {
		return btnComenzi;
	}

	public JButton getBtnStocProduse() {
		return btnStocProduse;
	}

}
