package br.univel.cadastro.model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClienteDaoH2Impl implements ClienteDao {

	public static Connection con;

	public void abrirConexao() throws SQLException {
		//
		String url = "jdbc:h2:~/Maycon";
		String user = "sa";
		String pass = "sa";
		con = DriverManager.getConnection(url, user, pass);
	}

	public void fecharConexao() throws SQLException {
		con.close();
	}

	@Override
	public void inserir(Cliente c) throws SQLException {

		PreparedStatement ps = con
				.prepareStatement("INSERT INTO CLIENTE (ID, NOME, ENDERECO, TELEFONE, UF) VALUES(?, ?, ?, ?, ?)");
		ps.setInt(1, 1);
		ps.setString(2, "Hugo");
		ps.setString(3, "Andrea Galafaci 839");
		ps.setString(4, "3321-3201");
		ps.setString(5, "Parana");

		int res = ps.executeUpdate();

		// Aqui nao garante que executa o close.
		ps.close();

		System.out.println(res + "Registros Inserido(s).");

	}

	@Override
	public void atualizar(Cliente c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Cliente c) throws SQLException {
		String sql = "DELETE FROM CLIENTE";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			int res = ps.executeUpdate();
			System.out.println(res + "registro apagados");

		}

	}

	@Override
	public Cliente buscar(int id) {
	return null;
	}

	@Override
	public Cliente buscarPorExemplo(Cliente c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
