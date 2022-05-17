package com.example.demo.entities.tables;

import java.util.ArrayList;

public class Categoria {

	private ArrayList<String> columns;
	private long tuples;

	public Categoria() {
		columns = new ArrayList<>();
		columns.add("idCategoria");
		columns.add("DescCategoria");
		this.setTuples(13);
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
