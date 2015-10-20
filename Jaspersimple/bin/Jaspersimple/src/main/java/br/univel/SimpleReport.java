package br.univel;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class SimpleReport {
	
// caminho do relatorio
	private String arq = "C:\\Users\\Maycondaluz\\JaspersoftWorkspace\\MyReports\\simples.jasper";

	public SimpleReport() {
		
		//TableModel tableModel = getTableModel();
		TableModel tableModel = TableModelData();
		
		//jasperprint é o relatorio prenchido e voce jja pode exportar ele para outro arquivo
		JasperPrint jasperPrint = null;
		TableModelData();
		try {
			
			// atribui os atributos empresa e telefone no relatorio
			Map<String, Object>map = new HashMap<String, Object>();
			map.put("empresa", "Petrobras");
			map.put("telefone", "33213201");
			// preenche o relatorio: JasperFillManager.fillReport
			jasperPrint = JasperFillManager.fillReport(arq, map,
					// padrao decorator
					new JRTableModelDataSource(tableModel));
			//instancia o jframe pra visualizar na tela
			JasperViewer jasperViewer = new JasperViewer(jasperPrint);
			
			//jasperviwer.setBoounds(50, 50, 320 ,240); muda a localizaçao do jfrmae
			jasperViewer.setVisible(true);
			
			
		} catch (JRException ex) {
			ex.printStackTrace();
		}
	}
//  getTableModel retorna um table model  criar um vetor de array uma matriz bidimensional uma pra coluna e outra
	// pros dados
	private TableModel TableModelData() {
		String[] columnNames = { "Id", "Nome", "Departamento", "Email" };
		
		String[][] data = {
				{ "1", "Hugo", "Financeiro", "hugod@univel.br" },
				{ "2", "José", "Comercial",  "josed@univel.br" },
				{ "3", "Luiz", "Contábil",   "luizd@univel.br" }
		};
		
		return new DefaultTableModel(data, columnNames);
	}

	public static void main(String[] args) {
		new SimpleReport();
	}
}
