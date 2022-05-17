package com.example.demo.entities.tables;

import java.util.ArrayList;

public class TipoConta {
	
	private ArrayList<String> columns;
	private long tuples;
	
	public TipoConta() {
		columns = new ArrayList<>();
		columns.add("idTipoConta");
		columns.add("Descricao");
		this.tuples = 20;
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
