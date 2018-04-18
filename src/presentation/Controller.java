package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logic.LogicClient;
import logic.LogicComanda;
import logic.LogicProdus;
import logic.LogicStoc;
import model.Client;
import model.Comanda;
import model.Produs;
import model.Stoc;

public class Controller implements ActionListener, ListSelectionListener {
	private View view;
	private Client client;
	private Stoc stoc;
	private Comanda comanda;
	private Produs produs;
	private LogicClient logicClient;
	private LogicProdus logicProdus;
	private LogicStoc logicStoc;
	private LogicComanda logicComanda;

	public Controller(View view) {
		this.view = view;
		client = new Client();
		produs = new Produs();
		comanda = new Comanda();
		stoc = new Stoc();
		logicClient = new LogicClient();
		logicProdus = new LogicProdus();
		logicComanda = new LogicComanda();
		logicStoc = new LogicStoc();

	}

	public void createClient() {
		JTable tab = view.getTableClient().getTable();
		int i = tab.getSelectedRow();
		String s = tab.getValueAt(i, 0).toString();
		System.out.println(Integer.parseInt(s));
		client.setId(Integer.parseInt(s));
		client.setNume((String) tab.getValueAt(i, 1));
		client.setEmail((String) tab.getValueAt(i, 2));
		client.setAdresa((String) tab.getValueAt(i, 3));
		System.out.println(client);

	}

	public void createProdus() {
		JTable tab = view.getTableProdus().getTable();
		int i = tab.getSelectedRow();

		String s = tab.getValueAt(i, 0).toString();
		String s1 = tab.getValueAt(i, 2).toString();
		System.out.println(Integer.parseInt(s));
		produs.setIdProdus(Integer.parseInt(s));
		produs.setNumeProdus((String) tab.getValueAt(i, 1));
		produs.setPretProdus((float) Double.parseDouble(s1));
		// client.setAdresa((String)tab.getValueAt(i, 3));
		System.out.println(produs);
	}

	public void createStoc() {
		JTable tab = view.getTableStoc().getTable();
		int i = tab.getSelectedRow();

		String s = tab.getValueAt(i, 0).toString();
		String s1 = tab.getValueAt(i, 1).toString();
		System.out.println(Integer.parseInt(s));
		stoc.setProdus_IdProdus(Integer.parseInt(s));
		stoc.setCantitateStoc(Integer.parseInt(s1));
		System.out.println(stoc);
	}

	public void createComanda() {
		JTable tab = view.getTableComanda().getTable();
		int i = tab.getSelectedRow();

		String s = tab.getValueAt(i, 0).toString();
		String s1 = tab.getValueAt(i, 1).toString();
		String s2 = tab.getValueAt(i, 2).toString();
		String s3 = tab.getValueAt(i, 3).toString();
		comanda.setIdComanda(Integer.parseInt(s));
		comanda.setClient_IdClient(Integer.parseInt(s1));
		comanda.setProdus_IdProdus(Integer.parseInt(s2));
		comanda.setCantitateComanda(Integer.parseInt(s3));
		System.out.println(comanda);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == view.getBtnClienti())
			view.generateTableClienti();
		else if (arg0.getSource() == view.getBtnComenzi())
			view.generateTableComanda();
		else if (arg0.getSource() == view.getBtnProduse())
			view.generateTableProduse();
		else if (arg0.getSource() == view.getBtnStocProduse())
			view.generateTableStoc();
		else if (arg0.getSource() == view.getTableClient().getBtnInsert()) {
			createClient();
			logicClient.insert(this.client);
			view.generateTableClienti();
		} else if (arg0.getSource() == view.getTableClient().getBtnUpdate()) {
			createClient();
			logicClient.update(this.client);
			view.generateTableClienti();
		} else if (arg0.getSource() == view.getTableClient().getBtnDelete()) {
			createClient();
			logicClient.delete(this.client);
			view.generateTableClienti();
		} else if (arg0.getSource() == view.getTableProdus().getBtnInsert()) {
			createProdus();
			logicProdus.insert(this.produs);
			view.generateTableProduse();
		} else if (arg0.getSource() == view.getTableProdus().getBtnUpdate()) {
			createProdus();
			logicProdus.update(this.produs);
			view.generateTableProduse();
		} else if (arg0.getSource() == view.getTableProdus().getBtnDelete()) {
			createProdus();
			logicProdus.delete(this.produs);
			view.generateTableProduse();
		} else if (arg0.getSource() == view.getTableStoc().getBtnInsert()) {
			createStoc();
			logicStoc.insert(this.stoc);
			view.generateTableStoc();
		} else if (arg0.getSource() == view.getTableStoc().getBtnUpdate()) {
			createStoc();
			logicStoc.update(this.stoc);
			view.generateTableStoc();
		} else if (arg0.getSource() == view.getTableStoc().getBtnDelete()) {
			createStoc();
			logicStoc.delete(this.stoc);
			view.generateTableStoc();
		} else if (arg0.getSource() == view.getTableComanda().getBtnInsert()) {
			createComanda();
			logicComanda.insert(this.comanda);
			view.generateTableComanda();

		} else if (arg0.getSource() == view.getTableComanda().getBtnUpdate()) {
			createComanda();
			logicComanda.update(this.comanda);
			view.generateTableComanda();
		} else if (arg0.getSource() == view.getTableComanda().getBtnDelete()) {
			createComanda();
			logicComanda.delete(this.comanda);
			view.generateTableComanda();
		} else if (arg0.getSource() == view.getTableComanda().getBtnProcesare()) {
			createComanda();
			logicComanda.procesare_comanda(comanda);
			view.generateTableComanda();
		}
		;

	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == view.getTableClient().getSelect())
			createClient();
		if (arg0.getSource() == view.getTableProdus().getSelect())
			createProdus();
		if (arg0.getSource() == view.getTableStoc().getSelect())
			createStoc();
		if (arg0.getSource() == view.getTableComanda().getSelect())
			createComanda();

	}

}
