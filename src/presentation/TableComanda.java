package presentation;

//import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Color;

public class TableComanda extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnUpdate;
	private JButton btnInsert;
	private JScrollPane scrollPane;
	private JButton btnProcesare;
	private JButton btnDelete;
	private ListSelectionModel select;

	public TableComanda(Object[][] a, String b[]) {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 620, 440);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
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

		btnProcesare = new JButton("Procesare");
		btnProcesare.setBounds(490, 170, 90, 25);
		contentPane.add(btnProcesare);

	}

	public TableComanda() {
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

	public JButton getBtnProcesare() {
		return btnProcesare;
	}

	public ListSelectionModel getSelect() {
		return select;
	}

	public JTable getTable() {
		return table;
	}

}
