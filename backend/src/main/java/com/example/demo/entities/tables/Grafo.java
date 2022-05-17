package com.example.demo.entities.tables;

import java.util.ArrayList;

public class Grafo {
	private boolean containsSelection;
	private boolean containsJunction;
	private ArrayList<String> projection;
	private ArrayList<String> junction;

	public Grafo() {
		this.containsJunction = false;
		this.containsSelection = false;
		projection = new ArrayList<>();
		junction = new ArrayList<>();
	}

	public boolean isContainsSelection() {
		return containsSelection;
	}

	public void setContainsSelection(boolean containsSelection) {
		this.containsSelection = containsSelection;
	}

	public boolean isContainsJunction() {
		return containsJunction;
	}

	public void setContainsJunction(boolean containsJunction) {
		this.containsJunction = containsJunction;
	}

	public ArrayList<String> getProjection() {
		return projection;
	}

	public ArrayList<String> getJunction() {
		return junction;
	}

}
