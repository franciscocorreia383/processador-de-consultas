package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entities.Query;

public class Services {

	@Autowired
	Parser parser = new Parser();
	Query query = new Query();
	
	public RelationalAlgebra parserString(String sql){
		
		//String regex = "^(SELECT)\\s+([^ ]+(,( )?[^ ]*)*)\\s+(FROM)\\s([^ ]*(,([^ ])*)*)((\\s(JOIN)\\s([^ ]+)\\sON\\s([^ ]+.[^ ]+)\\s=\\s([^ ]+.[^ ]+))+)?((\\s)+(WHERE)\\s+((([^ ]+)(\\s)*(<>|=|>|<|<=|>=)(\\s)*([^ ]+))((\\s)*(AND|IN|NOT IN|AND|IN|NOT IN)(\\s)*([^ ]+(\\s)*(<>|=|>|<|<=|>=)(\\s)*([^ ])+))*))?\\s*;$";
	
		//SqlVerify sqlVerify = new SqlVerify(regex, sql);
		
//		query = parser.ParserString(sql);
//		System.out.println(query.getColumns().toString());
//		System.out.println(query.getJoin().toString());
//		System.out.println(query.getWhere());
//		
		try {
			NewParser.Parser(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RelationalAlgebra algebra = new RelationalAlgebra();
		
		algebra.getProjection().addAll(query.getColumns());
		algebra.getJunction().addAll(query.getJoin());
		
		if (!query.getWhere().isEmpty()) {
			algebra.getSelection().addAll(query.getWhere());
		}
		
		return algebra;
	}
	
	
	
	
}
