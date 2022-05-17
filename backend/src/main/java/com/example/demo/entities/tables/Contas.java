package com.example.demo.entities.tables;

import java.util.ArrayList;

public class Contas {

	private ArrayList<String> columns;

	public Contas() {
		columns = new ArrayList<>();
		columns.add("idConta");
		columns.add("Descricao");
		columns.add("TipoConta_idTipoConta");
		columns.add("Usuario_idUsuario");
		columns.add("SaldoInicial");
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

}
