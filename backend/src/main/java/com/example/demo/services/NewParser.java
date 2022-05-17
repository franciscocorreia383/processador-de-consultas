package com.example.demo.services;

import com.example.demo.entities.Grafo;
import com.example.demo.entities.tables.Categoria;
import com.example.demo.entities.tables.Contas;
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

		// Verifica se a query está correta
		VerifyElements(newSql);

		// Carrega as colunas da projeção
		LoadProjection(newSql);

		// Carrega a/as coluna/s da pesquisa da query
		LoadJunction(newSql);

		if (grafo.isContainsSelection() && grafo.isContainsJunction()) {
			LoadSelectionOptimized(newSql);
		} else if (grafo.isContainsSelection()) {
			LoadSelection(newSql);
		}

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

		while (!newSql[counter].contains("SELECT")) {
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

		if (newSql[counter].contains("FROM")) {
			counter++;
		}

		grafo.getJunction().add(newSql[counter]);

		if (grafo.isContainsJunction()) {
			for (int i = counter; i < newSql.length; i++) {
				if (newSql[i].contains("JOIN")) {
					grafo.getJunction().add(newSql[i + 1]);
				}
			}
		}

	}

	private static void LoadSelectionOptimized(String[] newSql) {
		
	}

	private static void LoadSelection(String[] newSql) {
		for (int i = 0; i < newSql.length; i++) {
			if (newSql[i].contains("WHERE")) {
				counter = i;
			}
		}

		do {
			counter++;
			int aux = 0;
			String data = " ";

			data = newSql[counter].concat(newSql[counter + 1]);

			// Verifica se é uma string contem espaço removido pelo split
			if (newSql[counter + 2].contains("'")) {

				aux = counter + 2;

				data = data.concat(newSql[aux]);

				do {
					aux++;
					data = data.concat(" ");
					data = data.concat(newSql[aux]);

				} while (!newSql[aux].contains("'"));
				aux++;
				counter = aux;
			} else {
				data = data.concat(newSql[counter + 2]);
				counter += 3;
			}

			grafo.getSelection().add(data);

			if (counter >= newSql.length) {
				counter -= 1;
			}

		} while ((newSql[counter].contains("AND")
				|| (newSql[counter].contains("OR") || (newSql[counter].contains("IN")))));
	}

}
