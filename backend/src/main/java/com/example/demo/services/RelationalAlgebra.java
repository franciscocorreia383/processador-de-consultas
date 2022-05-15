package com.example.demo.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class RelationalAlgebra {

	private ArrayList<String> selection;
	private ArrayList<String> projection;
	private ArrayList<String> junction;
	private String algebra;
	private String bSelection;
	private String bProjection;
	private String bJunction;
	
	public RelationalAlgebra() {
		this.selection = new ArrayList<>();
		this.junction = new ArrayList<>();
		this.projection = new ArrayList<>();
	}

	public ArrayList<String> getSelection() {
		return selection;
	}

	public ArrayList<String> getProjection() {
		return projection;
	}

	public ArrayList<String> getJunction() {
		return junction;
	}

	public String getAlgebra() {
		return algebra;
	}

	public void setAlgebra(String algebra) {
		this.algebra = algebra;
	}

	public String getbSelection() {
		return bSelection;
	}

	public void setbSelection(String bSelection) {
		this.bSelection = bSelection;
	}

	public String getbProjection() {
		return bProjection;
	}

	public void setbProjection(String bProjection) {
		this.bProjection = bProjection;
	}

	public String getbJunction() {
		return bJunction;
	}

	public void setbJunction(String bJunction) {
		this.bJunction = bJunction;
	}	
	
}
