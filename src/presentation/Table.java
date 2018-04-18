package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JButton;

public class Table extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnInsert;
	private ListSelectionModel select;
	// private Controller controller;

	public void setTable(JTable table) {
		this.table = table;
	}

	public Table(Object a[][], String b[]) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 440);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 460, 380);
		contentPane.add(scrollPane);

		table = new JTable(a, b);
		select = table.getSelectionModel();

		scrollPane.setViewportView(table);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(490, 20, 90, 25);
		contentPane.add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(490, 70, 90, 25);
		contentPane.add(btnUpdate);

		btnInsert = new JButton("Insert");
		btnInsert.setBounds(490, 120, 90, 25);
		contentPane.add(btnInsert);

	}

	public Table() {
		// TODO Auto-generated constructor stub
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public JButton getBtnInsert() {
		return btnInsert;
	}

	public ListSelectionModel getSelect() {
		return select;
	}

	public JTable getTable() {
		return table;
	}
	// public Controller getController() {
	// return this.controller;
	// }
}
