package com.example.demo.entities.tables;

import java.util.ArrayList;

public class Categoria {

	private ArrayList<String> columns;

	public Categoria() {
		columns = new ArrayList<>();
		columns.add("idCategoria");
		columns.add("DescCategoria");
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

}
