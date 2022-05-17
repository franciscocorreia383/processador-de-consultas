package com.example.demo.entities;

import java.io.Serializable;
import java.util.Objects;

public class Sql implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String query;
	
	public Sql() {

	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public int hashCode() {
		return Objects.hash(query);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sql other = (Sql) obj;
		return Objects.equals(query, other.query);
	}
	
	
}
