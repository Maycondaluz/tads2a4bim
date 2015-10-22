package br.univel.cadastro.model;

import java.util.List;

public class Teste {
	public static void main(String[] args) {
		ClienteDaoH2Impl.getNewInstace().openCon();
//		ClienteDaoH2Impl.getNewInstace().iserir(new
//				Cliente(1, "Alex", "Paraná street", "(44)9718-0827", "Cascavel", Uf.PR));
//		ClienteDaoH2Impl.getNewInstace().iserir(new
//				Cliente(2, "Ana", " Agusta street", "(41)9891-0121", "São Paulo", Uf.SP));
		
//		Cliente c = ClienteDaoH2Impl.getNewInstace().buscar(2);
//		System.out.println(c.toString());
		
//		List<Cliente> lista = ClienteDaoH2Impl.getNewInstace().listar();
//		for(Object o : lista){
//			Cliente c = (Cliente) o;
//			System.out.println(c.toString());
//		}
		
		
		//test Alex
//		int newId = ClienteDaoH2Impl.getNewInstace().ultimoId() + 1;
//		System.out.println(newId);
		
		ClienteDaoH2Impl.getNewInstace().excluir(new Cliente(2));
		
		ClienteDaoH2Impl.getNewInstace().closeCon();
	}
}
