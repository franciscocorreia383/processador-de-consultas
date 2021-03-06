package com.example.demo.services;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.demo.entities.tables.Categoria;
import com.example.demo.entities.tables.Contas;
import com.example.demo.entities.tables.Movimentacao;
import com.example.demo.entities.tables.TipoConta;
import com.example.demo.entities.tables.TipoMovimento;
import com.example.demo.entities.tables.Usuario;

public class SqlVerify {
	String regex;
	String sql;
	String SxF[] = new String[1000];
	String FxJ[] = new String[1000];
	String W;
	String J;
	ArrayList<ArrayList<String>> Joins = new ArrayList<ArrayList<String>>(); // array com os joins
	ArrayList<ArrayList<String>> Where = new ArrayList<ArrayList<String>>(); // array com o where

	ArrayList<Class> classes = new ArrayList<Class>();
	Class Categoria = Categoria.class;
	Class Contas = Contas.class;
	Class Movimentacao = Movimentacao.class;
	Class TipoConta = TipoConta.class;
	Class TipoMovimento = TipoMovimento.class;
	Class Usuario = Usuario.class;

	public SqlVerify(String regex, String sql) throws Exception {

		super();
		classes.add(Categoria);
		classes.add(Contas);
		classes.add(Movimentacao);
		classes.add(TipoConta);
		classes.add(TipoMovimento);
		classes.add(Usuario);
		this.regex = regex;
		this.sql = sql;

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sql);
		System.out.println(classes.get(0).getName());
		// System.out.println(pattern.matcher(sql).matches());

		if (matcher.find()) { // verifica se o matcher do regex funcionou, ou seja se o sql ta com a syntax
								// correta se nao ele coloca mensagem que o sql esta errado
			SxF = matcher.group(2).replaceAll("\\s+", "").split(","); // coloca array SxF os fields do select
			FxJ = matcher.group(6).replaceAll("\\s+", "").split(","); // coloca array FxJ os fields do from
			J = matcher.group(9); // pega o group do regex dos joins
			W = matcher.group(18); // pega o group do regex dos joins
			// pega array de select e analisa cada um dos field para ver se existem

			// pega array de from e analisa cada um das tabelas para ver se existem

			verificarFieldModeloFxJ();
			verificarFieldModeloSxFv();
			// verifica se existe algum where se existir separa os elementos do regex e ver
			// se os fields existem e estao em tipos corretos
			if (W != null) {
				final String regexW = "[^ ]+\\s*(?:<=|>=|<>|=|>|<)\\s*[^ ]+";
				separateWhere(regexW, W);
				verificarFieldWhere();
			}
			// verifica se existe algum join se existir separa os elementos em uma matrix
			// onde cada elemento ??? um join e cada um deles estao os fields do join, e em
			// seguida ver se os fields e tabelas estao corretos
			if (J != null) {
				final String regexJ = "join\\s+([^ ]+)\\s+on\\s+([^ ]+)\\s*=\\s*([^ ]+)";

				separateJ(regexJ, J);

				verificarFieldJoins();

			}

		} else {
			throw new Exception("Sintaxe Sql Incorreta");
		}

	}

	public void separateJ(String regexJ, String J) {
		final Pattern patternJ = Pattern.compile(regexJ, Pattern.MULTILINE);
		final Matcher matcherJ = patternJ.matcher(J);
		int k = 0;

		while (matcherJ.find()) {
			ArrayList<String> auxJoins = new ArrayList<String>();
			// System.out.println("Full match: " + matcherJ.group(0));

			for (int i = 1; i <= matcherJ.groupCount(); i++) {
				// System.out.println("Group " + i + ": " + matcherJ.group(i));
				auxJoins.add(matcherJ.group(i));
			}
			Joins.add(auxJoins);
		}

	}

	public void separateWhere(String regexW, String W) {
		final Pattern patternW = Pattern.compile(regexW, Pattern.MULTILINE);
		final Matcher matcherW = patternW.matcher(W);

		while (matcherW.find()) {
// transformar array em arraylist so para todas variaveis ficarem iguais;
			List lista = Arrays.asList(matcherW.group(0)
					.split("(\\s*\\>=\\s*)|(\\s*\\<>\\s*)|(\\s*\\=\\s*)|(\\s*\\>\\s*)|(\\s*\\<\\s*)|(\\s*\\<=\\s*)"));
			ArrayList listax = new ArrayList(lista);
			//
			Where.add(listax);

		}

	}

	public void verificarFieldModeloSxF() throws Exception {
		System.out.println("verificando select : ");
		for (int i = 0; i < SxF.length; i++) {
			boolean exist = false;
			for (int j = 0; j < classes.size(); j++) {
				Field[] fields = classes.get(j).getDeclaredFields();

				for (Field f : fields) {
					// System.out.println(SxF[i] + " " + f.getName());
					if (((SxF[i]).toLowerCase()).equals((f.getName()).toLowerCase())) {
						System.out.println("O field " + SxF[i] + " existe");
						exist = true;
						break;
					}
				}
				if (exist == true) {
					break;
				}
			}
			if (exist == false) {

				throw new Exception("Ocorreu um Erro, o field " + SxF[i] + " nao existe");

			}
		}
	}

	public void verificarFieldModeloSxFv() throws Exception {
		System.out.println("verificando select : ");
		String tabela = "";
		for (int i = 0; i < SxF.length; i++) {
			boolean exist = false;
			for (int j = 0; j < classes.size(); j++) {
				for (int u = 0; u < FxJ.length; u++) {
					if (classes.get(j).getName().toLowerCase().equals(FxJ[u].toLowerCase())) {
						tabela = FxJ[u].toLowerCase();
						Field[] fields = classes.get(j).getDeclaredFields();

						for (Field f : fields) {
							// System.out.println(SxF[i] + " " + f.getName());
							if (((SxF[i]).toLowerCase()).equals((f.getName()).toLowerCase())) {
								System.out.println("O field " + SxF[i] + " existe na tabela " + tabela);
								exist = true;
								break;
							}
						}
						if (exist == true) {
							break;
						}
					}
				}
			}
			if (exist == false) {

				throw new Exception("Ocorreu um Erro, o field " + SxF[i] + " nao existe na tabela " + tabela);

			}
		}
	}

	public void verificarFieldWhere() throws Exception {
		System.out.println("verificando where : ");
		for (int i = 0; i < Where.size(); i++) {
			boolean exist = false;

			boolean typefield = false;

			for (int l = 0; l < classes.size(); l++) {

				Field[] fields = classes.get(l).getDeclaredFields();

				for (Field f : fields) {
					String a = f.getName().toLowerCase();
					String b = Where.get(i).get(0).toLowerCase();
					// System.out.println((Where.get(i).get(0)).toLowerCase() + " " +
					// f.getName().toLowerCase());
					if (f.getName().toLowerCase().equals(Where.get(i).get(0).toLowerCase())) {
						System.out.println("O field " + Where.get(i).get(0) + " existe");
						if (f.getGenericType() == teste(Where.get(i).get(1))) {
							System.out.println(
									"O field " + Where.get(i).get(0) + " esta sendo comparado com o tipo correto "
											+ "this " + teste(Where.get(i).get(1)));
							typefield = true;

						} else if (f.getGenericType() == double.class && teste(Where.get(i).get(1)) == int.class) {
							System.out.println(
									"O field " + Where.get(i).get(0) + " esta sendo comparado com o tipo correto "
											+ "this " + teste(Where.get(i).get(1)));
							typefield = true;
						}

						exist = true;

					}

				}

				if (exist == true && typefield == true) {
					break;
				}
			}
			if (exist == false) {

				throw new Exception("Ocorreu um Erro, O field " + Where.get(i).get(0) + " nao existe");

			}
			if (typefield == false) {
				throw new Exception("Ocorreu um Erro, O field " + Where.get(i).get(0)
						+ " esta sendo comparado com um tipo incorreto");
			}

		}
	}

	public void verificarFieldJoins() throws Exception {
		System.out.println("verificando join : ");
		for (int i = 0; i < Joins.size(); i++) {
			boolean exist = false;
			for (int j = 0; j < Joins.get(i).size(); j++) {
				exist = false;
				String[] splitJ = (Joins.get(i).get(j)).split("\\.");

				for (int k = 0; k < classes.size(); k++) {
					if (((splitJ[0]).toLowerCase()).equals((classes.get(k).getName()).toLowerCase())) {
						System.out.println("A tabela " + splitJ[0] + " existe");

						exist = true;

					}
				}
				if (exist == false) {

					throw new Exception("Ocorreu um Erro, A tabela " + splitJ[0] + " nao existe");

				}
				if (splitJ.length > 1) {
					exist = false;
					splitJ = (Joins.get(i).get(j)).split("\\.");
					for (int l = 0; l < classes.size(); l++) {
						if ((classes.get(l).getName()).toLowerCase().equals((splitJ[0]).toLowerCase())) {
							Field[] fields = classes.get(l).getDeclaredFields();

							for (Field f : fields) {
								// System.out.println(splitJ[1] + " " + f.getName().toLowerCase());
								if (((splitJ[1]).toLowerCase()).equals((f.getName()).toLowerCase())) {
									System.out.println("O field " + splitJ[1] + " existe");

									exist = true;
									break;
								}
							}

						}
					}
					if (exist == false) {

						throw new Exception("Ocorreu um Erro, O field " + Joins.get(i).get(j) + " nao existe");

					}
				}
			}

			if (exist == false) {

				throw new Exception("Ocorreu um Erro, o field " + SxF[i] + " nao existe");

			}
		}
	}

	public void verificarFieldModeloFxJ() throws Exception {
		System.out.println("verificando from : ");
		for (int i = 0; i < FxJ.length; i++) {
			boolean exist = false;
			for (int j = 0; j < classes.size(); j++) {
				if (((FxJ[i]).toLowerCase()).equals((classes.get(j).getName()).toLowerCase())) {
					System.out.println("A tabela " + FxJ[i] + " existe");
					exist = true;
					break;
				}
			}
			if (exist == false) {

				throw new Exception("Ocorreu um Erro, a Tabela " + FxJ[i] + " nao existe");

			}
		}
	}

	public void printSxF() {
		int i = 0;
		for (String arrElement : SxF) {

			System.out.println("Item " + i + ": " + arrElement);
			i++;

		}

	}

	public void printFxJ() {
		int i = 0;
		for (String arrElement : FxJ) {

			System.out.println("Item " + i + ": " + arrElement);
			i++;

		}

	}

	public void printJoins() {
		for (int i = 0; i < Joins.size(); i++) {
			System.out.println("------------------------");
			System.out.println("Join " + i + ":\n");
			for (int j = 0; j < Joins.get(i).size(); j++) {

				System.out.println("Item " + j + ": " + Joins.get(i).get(j));

			}

			System.out.println("------------------------");
		}

	}

	public void printWhere() {
		for (int i = 0; i < Where.size(); i++) {
			System.out.println("------------------------");
			System.out.println("Where " + i + ":\n");
			for (int j = 0; j < Where.get(i).size(); j++) {

				System.out.println("Item " + j + ": " + Where.get(i).get(j));
			}

		}

	}

	public static boolean isStringInteger(String stringToCheck, int radix) {
		Scanner sc = new Scanner(stringToCheck.trim());
		if (!sc.hasNextInt(radix))
			return false;
		sc.nextInt(radix);
		return !sc.hasNext();
	}

	public boolean isStringDouble(String a) {
		try {
			Double.parseDouble(a);
			return true;
		} catch (NumberFormatException e) {
			// not a double
			return false;
		}
	}

	public Class teste(String a) {
		try {
			Integer.parseInt(a);
			return int.class;

		} catch (NumberFormatException e) {
			try {
				Double.parseDouble(a);
				return double.class;
			} catch (NumberFormatException e1) {
				// not a double
				return String.class;
			}
		}
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String[] getSxF() {
		return SxF;
	}

	public void setSxF(String[] sxF) {
		SxF = sxF;
	}

	public String[] getFxJ() {
		return FxJ;
	}

	public void setFxJ(String[] fxJ) {
		FxJ = fxJ;
	}

	public String getJ() {
		return J;
	}

	public void setJ(String j) {
		J = j;
	}

	public ArrayList<ArrayList<String>> getJoins() {
		return Joins;
	}

	public void setJoins(ArrayList<ArrayList<String>> joins) {
		Joins = joins;
	}

}
