package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Query;
import com.example.demo.entities.Sql;
import com.example.demo.services.RelationalAlgebra;
import com.example.demo.services.Services;

@RestController
@RequestMapping(value = "/query")
public class Controller {

	Services services = new Services();

//	@PostMapping
//	public ResponseEntity<RelationalAlgebra> queryParser(@RequestBody String sql) throws Exception {
//		
//		RelationalAlgebra algebra = new RelationalAlgebra(); 
//		
//		algebra = services.parserString(sql);
//		
//		String relationalAlgebra = "¶ "+ algebra.getProjection()+"(q "+ algebra.getSelection() + " (" + algebra.getJunction() + "));";
//		algebra.setbSelection("q "+ algebra.getSelection() + " ");
//		algebra.setbSelection(algebra.getbSelection().replace("[", ""));
//		algebra.setbSelection(algebra.getbSelection().replace("]", ""));
//		algebra.setbSelection(algebra.getbSelection().replace(" ", ""));	
//		
//		
//		algebra.setbJunction(""+algebra.getJunction());
//		algebra.setbJunction(algebra.getbJunction().replace("[", ""));
//		algebra.setbJunction(algebra.getbJunction().replace("]", ""));
//		
//		algebra.setbProjection("¶ "+ algebra.getProjection());
//		algebra.setbProjection(algebra.getbProjection().replace("[", ""));
//		algebra.setbProjection(algebra.getbProjection().replace("]", ""));
//		
//		relationalAlgebra = relationalAlgebra.replace("[", "");
//		relationalAlgebra = relationalAlgebra.replace("]", "");
//		
//		
//		algebra.setAlgebra(relationalAlgebra);
//		
//		return ResponseEntity.ok().body(algebra);
//	}

	@PostMapping
	public ResponseEntity<Sql> queryParser(@RequestBody Sql sql) {
		
		services.parserString(sql.getQuery());
		
		return ResponseEntity.ok().body(sql);
	}

}
