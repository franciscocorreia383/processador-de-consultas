package com.example.demo.entities.tables;

import java.util.ArrayList;

public class Movimentacao {

	private ArrayList<String> columns;
	
	public Movimentacao() {
		columns = new ArrayList<>();
		columns.add("idMovimentacao");
		columns.add("DataMovimentacao");
		columns.add("Descricao");
		columns.add("TipoMovimento_idTipoMovimento");
		columns.add("Categoria_idCategoria");
		columns.add("Contas_idConta");
		columns.add("Valor");
	}

	public ArrayList<String> getColumns() {
		return columns;
	}
	
}
