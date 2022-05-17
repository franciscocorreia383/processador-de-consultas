package com.example.demo.entities.tables;

import java.util.ArrayList;

public class TipoConta {
	
	private ArrayList<String> columns;
	
	public TipoConta() {
		columns = new ArrayList<>();
		columns.add("idTipoConta");
		columns.add("Descricao");
	}

	public ArrayList<String> getColumns() {
		return columns;
	}
}
