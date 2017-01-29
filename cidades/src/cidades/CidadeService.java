package cidades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class CidadeService {

	private static String COUNT = "count *";
	private static String COUNT_DISTINCT = "count distinct";
	private static String FILTER = "filter";
	private static String EXIT = "exit";
	private static String FILE = "/cidades.csv";
	private static List<String> cidades = new ArrayList<>();

	public static void execute(String comando) throws IOException {

		setCidades();

		count(comando);

		countDistinct(comando);

		filter(comando);

		exit(comando);

	}

	private static void filter(String comando) {

		if (!comando.toLowerCase().trim().startsWith(FILTER))
			return;

		String[] split = comando.replaceAll(FILTER, "").trim().split(" ");

		if (split.length != 2)
			return;

		int posicao = getPosicaoPropriedade(cidades.get(0), split[0]);

		if (posicao != -1)
			filterPropriedade(cidades, posicao, split[1]);
	}

	private static void countDistinct(String comando) {

		if (!comando.toLowerCase().trim().startsWith(COUNT_DISTINCT))
			return;

		int posicao = getPosicaoPropriedade(cidades.get(0),
				comando.replaceAll(COUNT_DISTINCT, "").trim());

		if (posicao != -1)
			distinctPropriedade(cidades.subList(1, cidades.size()), posicao);
	}

	private static void count(String comando) {

		if (!COUNT.equalsIgnoreCase(comando.trim()))
			return;

		System.out.println(cidades.size() - 1);
	}

	private static void exit(String comando) {

		if (!EXIT.equalsIgnoreCase(comando.trim()))
			return;

		System.exit(0);
	}

	private static void filterPropriedade(List<String> list, int posicao,
			String valor) {

		List<String> semCabecalho = list.subList(1, list.size());

		System.out.println(list.get(0));
		for (String s : semCabecalho)
			if (s.split(",")[posicao].equalsIgnoreCase(valor))
				System.out.println(s);
	}

	private static void distinctPropriedade(List<String> list, int posicao) {

		SortedSet<String> sortedSet = new TreeSet<>();

		for (String s : list)
			sortedSet.add(s.split(",")[posicao]);

		for (String s : sortedSet)
			System.out.println(s);

		System.out.println(sortedSet.size());
	}

	private static int getPosicaoPropriedade(String cabecalho,
			String propriedade) {

		String[] split = cabecalho.split(",");
		for (int i = 0; i < split.length; i++)
			if (split[i].equalsIgnoreCase(propriedade))
				return i;

		return -1;
	}

	private static void setCidades() throws IOException {

		if (!cidades.isEmpty())
			return;

		BufferedReader br = new BufferedReader(new InputStreamReader(
				CidadeService.class.getResourceAsStream(FILE)));

		String line;

		while ((line = br.readLine()) != null)
			cidades.add(line.trim());

		br.close();

	}
}
