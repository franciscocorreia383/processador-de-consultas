package com.example.demo.entities.tables;

import java.util.ArrayList;

public class Movimentacao {

	private ArrayList<String> columns;
	private long tuples;
	
	public Movimentacao() {
		columns = new ArrayList<>();
		columns.add("idMovimentacao");
		columns.add("DataMovimentacao");
		columns.add("Descricao");
		columns.add("TipoMovimento_idTipoMovimento");
		columns.add("Categoria_idCategoria");
		columns.add("Contas_idConta");
		columns.add("Valor");
		this.setTuples(2000000000);
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public long getTuples() {
		return tuples;
	}

	public void setTuples(long tuples) {
		this.tuples = tuples;
	}
	
}
