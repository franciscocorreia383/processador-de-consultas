package com.example.demo.entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Query implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<String> columns;
	private ArrayList<String> where;
	private ArrayList<String> table;
	private ArrayList<String> join;
	private Boolean enableSelection;
	private Boolean enableJunction;

	public Query() {
		super();
		this.columns = new ArrayList<>();
		this.where = new ArrayList<>();
		this.table = new ArrayList<>();
		this.join = new ArrayList<>();
		this.enableJunction = false;
		this.enableSelection = false;
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public ArrayList<String> getWhere() {
		return where;
	}

	public ArrayList<String> getTable() {
		return table;
	}

	public ArrayList<String> getJoin() {
		return join;
	}
	
	public Boolean getEnableSelection() {
		return enableSelection;
	}

	public void setEnableSelection(Boolean enableSelection) {
		this.enableSelection = enableSelection;
	}

	public Boolean getEnableJunction() {
		return enableJunction;
	}

	public void setEnableJunction(Boolean enableJunction) {
		this.enableJunction = enableJunction;
	}
}
