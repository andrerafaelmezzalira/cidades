package cidades;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author andrerafaelmezzalira
 * 
 *         Interface que utiliza System.in e System.out para iteragir com o
 *         programa cidades.
 */
public class ExecuteCidade {

	public static void main(String... args) {

		Scanner scan = new Scanner(System.in);

		while (scan.hasNextLine()) {

			/*
			 * Informe os seguintes comandos:
			 * 
			 * - count * => escreve no console a contagem total de registros
			 * importados
			 * 
			 * - count distinct [propriedade] => escreve no console o total de
			 * valores distintos da propriedade
			 * 
			 * - filter [propriedade] [valor] => escreve no console a linha de
			 * cabeçalho e todas as linhas em que a propriedade enviada possua o
			 * valor enviado
			 * 
			 * - exit => finaliza o programa
			 */
			String comando = scan.nextLine();

			try {
				CidadeService.execute(comando);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
		}

		scan.close();
	}
}
