package com.example.demo.entities.tables;

import java.util.ArrayList;

public class Usuario {
	private ArrayList<String> columns;
	
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
	}

	public ArrayList<String> getColumns() {
		return columns;
	}
	
}
