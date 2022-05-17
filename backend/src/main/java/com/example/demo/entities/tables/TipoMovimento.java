package com.example.demo.entities.tables;

import java.util.ArrayList;

public class TipoMovimento {

	ArrayList<String> columns;
	private long tuples;
	
	public TipoMovimento() {
		columns = new ArrayList<>();
		columns.add("idTipoMovimento");
		columns.add("DescMovimentacao");
		this.setTuples(30);
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
