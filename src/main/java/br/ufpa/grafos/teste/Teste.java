package br.ufpa.grafos.teste;

import java.util.Scanner;

//import br.ufpa.grafos.classes.AdjacencyListGraph;
import br.ufpa.grafos.classes.AdjacencyMatrixGraph;

/*import br.ufpa.grafos.classes.AdjacencyListGraph;
import br.ufpa.grafos.classes.AdjacencyMatrixGraph;*/

public class Teste {

	public static void main(String[] args) {
		System.out.println("INDIQUE O N�MERO DE V�RTICES DO GRAFO:\n");
		Scanner input = new Scanner(System.in);

		Integer numeroDeVertices = input.nextInt();

		System.out.println("\nCRIANDO O GRAFO...\n");

		AdjacencyMatrixGraph grafo = new AdjacencyMatrixGraph(numeroDeVertices);

		System.out.println("GRAFO CRIADO COM SUCESSO...\n");

		System.out.println("VOC� DESEJA ADICIONAR ARESTAS VALORADAS?\n1) SIM\n2) N�O\n");

		int arestasValoradas = input.nextInt();

		if (arestasValoradas == 1) {
			boolean pararArestas = false;
			System.out.println("INDIQUE A OS V�RTICES DAS ARESTAS, CASO TERMINE � S� DIGITAR 'n' NO FINAL.\n"
					+ "LEMBRANDO QUE OS V�RTICES EST�O NUMERADOS DE 1 AT� " + numeroDeVertices);

			while (!pararArestas) {
				System.out.println("De ");
				int de = input.nextInt();
				System.out.println("Para ");
				int para = input.nextInt();
				System.out.print("Com o valor ");
				int valor = input.nextInt();
				System.out.println("ADICIONANDO ARESTA...");
				grafo.adicionarArestaValorada(de, para, valor);
				System.out.println("ARESTA ADICIONADA!");
				System.out.println("DESEJA CONTINUAR?\ns ou n");
				char resp = input.next().charAt(0);

				if (resp == 'n') {
					pararArestas = true;
				}
			}
		} else {
			boolean pararArestas = false;
			System.out.println("INDIQUE A OS V�RTICES DAS ARESTAS, CASO TERMINE � S� DIGITAR 'n' NO FINAL.\n"
					+ "LEMBRANDO QUE OS V�RTICES EST�O NUMERADOS DE 1 AT� " + numeroDeVertices);

			while (!pararArestas) {
				System.out.println("De ");
				int de = input.nextInt();
				System.out.println("Para");
				int para = input.nextInt();
				System.out.println("ADICIONANDO ARESTA...");
				grafo.adicionarAresta(de, para);
				System.out.println("ARESTA ADICIONADA!");
				System.out.println("DESEJA CONTINUAR?\ns ou n");
				char resp = input.next().charAt(0);

				if (resp == 'n') {
					pararArestas = true;
				}
			}
		}
		
		
		boolean parar = false;
		
		while(!parar) {
			System.out.println("\n##################################################################");
			System.out.println("1) WARSHALL\n2) ORDENA��O TOPOL�GICA\n3) COMPONENTES FORTEMENTE CONECTADOS\n4) DIJKSTRA\n5) SAIR DO SISTEMA");
			int opcao = input.nextInt();
			switch (opcao) {
			case 1:
				grafo.warshall();
				System.out.println(grafo.toStringWarshall());
				break;
			case 2:
				grafo.topologicalSort();
				break;
			case 3:
				grafo.printSCCs();
				break;
			case 4:
				System.out.print("Indique a fonte: ");
				int fonte = input.nextInt();
				grafo.dijkstra(fonte);
				break;
			case 5:
				parar = true;
				System.out.println("SAINDO DO SISTEMA...");
				input.close();
				break;
			default:
				System.out.println("Essa n�o � uma op��o v�lida!");
				break;
			}
		}
	}
}