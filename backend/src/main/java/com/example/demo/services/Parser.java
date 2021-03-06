package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Query;
import com.example.demo.entities.tables.Usuario;

@Service
public class Parser {

	public Query ParserString(String parser) {

		Query query = new Query();
		Usuario user = new Usuario();

		String[] aux = parser.split(" ");
		
		// verifica o retorno do grafo
		for (String string : aux) {
			if (string.contains("join")) {
				query.setEnableJunction(true);
			}
			if (string.contains("where")) {
				query.setEnableSelection(true);
			}
		}
		

		int count = 2;

		//preenche as colunas
		try {
			while (!aux[count].contains("from")) {

				String data = "";

				data = aux[count].replace(",", "");
				query.getColumns().add(data);

				count++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		count++;

		query.getJoin().add(aux[count]);
		if (query.getEnableJunction()) {
			for (int i = count; i < aux.length; i++) {
				if (aux[i].contains("join")) {
					i++;
					query.getJoin().add(aux[i]);
				}

				if (aux[i].contains("where")) {
					count = i;
				}
			}
		}

		//preenche a seleção
		try {
			if (query.getEnableSelection()) {
				do {
					count++;

					String data = " ";

					data = aux[count].concat(aux[count + 1]).concat(aux[count + 2]);

					query.getWhere().add(data);

					count += 3;

					if (count >= aux.length) {
						count -= 2;
					}

				} while ((aux[count].contains("and") || (aux[count].contains("or") || (aux[count].contains("in")))));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return query;
	}

}
