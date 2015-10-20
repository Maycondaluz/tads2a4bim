package br.univel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class SimpleReportJdbc {

	public Connection con;
	private String arq = "C:\\Users\\Maycondaluz\\JaspersoftWorkspace\\MyReports\\simplesjdbc.jasper";

	public SimpleReportJdbc() throws SQLException {
		
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(arq, null, getConnection());
			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
			jasperViewer.setVisible(true);
		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}

	private Connection getConnection() throws SQLException {
		
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = "jdbc:mysql://192.168.101.10/employees";
		String user = "jasper";
		String pass = "jasper";
	
		System.out.println("Abriu conexão");
	
		
		return con = DriverManager.getConnection(url, user, pass);
	}

	public static void main(String[] args) throws SQLException {
		new SimpleReportJdbc();
	}
}