package com.example.demo.entities;

import java.util.ArrayList;

public class Grafo {
	private boolean containsSelection;
	private boolean containsJunction;
	private ArrayList<String> projection;
	private ArrayList<String> junction;
	private ArrayList<String> selection;
	private ArrayList<OptimizedSelectionTuple> optimizedSelection;

	public Grafo() {
		this.containsJunction = false;
		this.containsSelection = false;
		projection = new ArrayList<>();
		junction = new ArrayList<>();
		selection = new ArrayList<>();
		optimizedSelection = new ArrayList<>();
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

	public ArrayList<String> getSelection() {
		return selection;
	}

	public ArrayList<OptimizedSelectionTuple> getOptimizedSelection() {
		return optimizedSelection;
	}

	
}
