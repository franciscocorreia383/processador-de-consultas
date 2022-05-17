package com.example.demo.entities.tables;

import java.util.ArrayList;

public class Contas {

	private ArrayList<String> columns;
	private long tuples;

	public Contas() {
		columns = new ArrayList<>();
		columns.add("idConta");
		columns.add("Descricao");
		columns.add("TipoConta_idTipoConta");
		columns.add("Usuario_idUsuario");
		columns.add("SaldoInicial");
		this.tuples = 100000; 
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
