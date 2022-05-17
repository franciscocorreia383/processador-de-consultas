package com.example.demo.entities.tables;

import java.util.ArrayList;

public class TipoMovimento {

	ArrayList<String> columns;
	
	public TipoMovimento() {
		columns = new ArrayList<>();
		columns.add("idTipoMovimento");
		columns.add("DescMovimentacao");
	}

	public ArrayList<String> getColumns() {
		return columns;
	}
}
