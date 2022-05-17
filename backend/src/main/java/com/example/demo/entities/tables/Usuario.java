package com.example.demo.entities.tables;

import java.util.ArrayList;

public class Usuario {
	private ArrayList<String> columns;
	private long tuples;
	
	public Usuario() {
		columns = new ArrayList<>();
		columns.add("idUsuario");
		columns.add("Nome");
		columns.add("Logradouro");
		columns.add("Numero");
		columns.add("Bairro");
		columns.add("CEP");
		columns.add("UF");
		columns.add("DataNascimento");
		this.setTuples(1000000);
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
