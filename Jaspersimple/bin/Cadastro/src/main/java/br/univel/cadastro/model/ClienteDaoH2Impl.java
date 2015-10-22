package br.univel.cadastro.model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ClienteDaoH2Impl implements ClienteDao {

	public Connection con;
	private static ClienteDaoH2Impl instace;

	private ClienteDaoH2Impl() {
	}

	public static ClienteDaoH2Impl getNewInstace() {
		if (instace == null)
			return instace = new ClienteDaoH2Impl();
		return instace;
	}

	public void openCon() {
		String url = "jdbc:h2:C:\\Users\\Alex\\BDB_4BIM\\BD_4bim";
		String user = "sa";
		String pass = "sa";
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Abriu conexão");
	}

	public void closeCon() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("fechou conexão");
	}

	public void iserir(Cliente c) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("INSERT INTO CLIENTE (id,nome,endereco,telefone,cidade,uf) VALUES (? , ?, ?, ?, ?, ?)");
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNome());
			ps.setString(3, c.getEndereco());
			ps.setString(4, c.getTelefone());
			ps.setString(5, c.getCidade());
			ps.setObject(6, c.getUf().getNome());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void atualizar(Cliente c) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("UPDATE CLIENTE SET NOME = ?,ENDERECO = ?,TELEFONE = ?,UF = ? WHERE ID ="
					+ c.getId());
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNome() + "");
			ps.setString(3, c.getEndereco() + "");
			ps.setString(4, c.getTelefone() + "");
			ps.setString(5, c.getCidade() + "");
			ps.setObject(6, c.getUf() + "");
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao atualizar dados do cliente!");
		}

	}

	public void excluir(Cliente c) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("DELETE FROM CLIENTE WHERE ID ="
					+ c.getId());
			int res = ps.executeUpdate();
			ps.close();
			JOptionPane.showMessageDialog(null,"( "+ res + " ) cliente"
					+ "\nExcluido com sucesso.");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao deletar dados do cliente \n->" + c.getNome());
		}

	}

	public Cliente buscar(int id) {
		Statement st = null;
		ResultSet rs = null;
		Cliente c = null;
		Uf uf = Uf.PR;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT nome,endereco,telefone,cidade,uf  FROM cliente WHERE ID="
					+ id);
			rs.next();
			c = new Cliente(id, rs.getString("nome"), rs.getString("endereco"),
					rs.getString("telefone"), rs.getString("cidade"),
					uf.valida(rs.getObject("uf")));
		} catch (SQLException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Erro ao pesquisar o cliente no banco!!\n"
									+ e.getMessage());
		}
		return c;
	}

	public Cliente buscarPorExemplo(Cliente c) {
		return null;
	}

	public List<Cliente> listar() {
		Statement st = null;
		ResultSet rs = null;
		Uf uf = Uf.PR;
		List<Cliente> lista = new ArrayList<Cliente>();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT id,nome,endereco,telefone,cidade,uf  FROM cliente");
			while (rs.next()) {
				lista.add(new Cliente(rs.getInt("ID"), rs.getString("nome"), rs
						.getString("endereco"), rs.getString("telefone"), rs
						.getString("cidade"), uf.valida(rs.getObject("uf"))));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao listar todos os clientes!!\n" + e.getMessage());
		}
		return lista;
	}

	public int ultimoId() {
		Statement st = null;
		ResultSet rs = null;
		int id = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT id FROM cliente");
			while (rs.next()) {
				id = rs.getInt("ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
