package com.example.demo.services;

import com.example.demo.entities.tables.Categoria;
import com.example.demo.entities.tables.Contas;
import com.example.demo.entities.tables.Grafo;
import com.example.demo.entities.tables.Movimentacao;
import com.example.demo.entities.tables.TipoConta;
import com.example.demo.entities.tables.TipoMovimento;
import com.example.demo.entities.tables.Usuario;

public class NewParser {

	static Grafo grafo = new Grafo();
	static int counter; 

	public static void Parser(String sql) throws Exception {

		// tabelas do banco de dados
		Categoria categoria = new Categoria();
		Contas conta = new Contas();
		Movimentacao movimentacao = new Movimentacao();
		TipoConta tipoConta = new TipoConta();
		TipoMovimento tipoMovimento = new TipoMovimento();
		Usuario usuario = new Usuario();
		counter = 0;
		
		// Vetorização do sql
		sql = sql.replace(";", "");
		String[] newSql = sql.split(" ");

		if (!VerifySql(sql)) {
			throw new Exception("Incorrect SQL inputed!");
		}

		//Verifica se a query está correta
		VerifyElements(newSql);

		//Carrega as colunas da projeção
		LoadProjection(newSql);
		
		//Carrega a/as coluna/s da pesquisa da query
		LoadJunction(newSql);
		
		
//		if (grafo.isContainsJunction()) {
//			
//		}else {
//			
//		}
	}

//---------------------------------------------------------------------------------------------------
	private static boolean VerifySql(String sql) {

		return true;
	}

	private static void VerifyElements(String[] newSql) {
		for (String item : newSql) {
			if (item.contains("JOIN")) {
				grafo.setContainsJunction(true);
			}

			if (item.contains("WHERE")) {
				grafo.setContainsSelection(true);
			}
		}

	}

	private static void LoadProjection(String[] newSql) {

		while(!newSql[counter].contains("SELECT")) {
			counter++;
		}
		
		counter++;
		
		while (!newSql[counter].contains("FROM")) {

			String data = "";

			data = newSql[counter].replace(",", "");
			grafo.getProjection().add(data);

			counter++;
		}
	}

	private static void LoadJunction(String[] newSql) {
		
		if(newSql[counter].contains("FROM")) {
			counter++;
		}
		
		grafo.getJunction().add(newSql[counter]);

		if(grafo.isContainsJunction()) {
			for(int i = counter; i < newSql.length; i++) {
				if (newSql[i].contains("JOIN")) {
					grafo.getJunction().add(newSql[i+1]);
				}
			}
		}
	
	}

}
