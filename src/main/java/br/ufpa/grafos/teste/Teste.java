package br.ufpa.grafos.teste;

import br.ufpa.grafos.classes.Grafo_Lista;
import br.ufpa.grafos.classes.Grafo_Matriz;

public class Teste {

	public static void main(String[] args) {
		/*// Teste grafo - Lista
		System.out.println("Teste grafo - Lista");
		Grafo_Lista g = new Grafo_Lista(3);
		
		g.AdicionarAresta(1, 2);
		g.AdicionarAresta(2, 3);
		g.AdicionarAresta(3, 1);
		
		System.out.println(g.toString());
		System.out.println(g.isEuleriano());
		System.out.println(g.hasPercursoAbertoEuler());

		int[] maxGrau_index = g.maxGrau();
        System.out.println("O vértice com maior grau no grafo é o vértice: " + g.getVertices().get(maxGrau_index[1]).getNumero() + ", com grau igual a: " + maxGrau_index[0]);
        
        System.out.println("Verificando se o grafo é conexo!");
		System.out.println(g.isConexo());
		System.out.println("Algoritmo DFS:");
		g.DFS();

		System.out.println("\nAdicionando vértice e aresta!\n");

		g.AdicionarVertice();
		
		g.AdicionarAresta(4, 2);
		g.AdicionarAresta(4, 3);
		
		System.out.println(g.toString());
		System.out.println(g.isEuleriano());
		System.out.println(g.hasPercursoAbertoEuler());

		maxGrau_index = g.maxGrau();
		System.out.println("O vértice com maior grau no grafo é o vértice: " + g.getVertices().get(maxGrau_index[1]).getNumero() + ", com grau igual a: " + maxGrau_index[0]);
        System.out.println("Verificando se o grafo é conexo!");
		System.out.println(g.isConexo());
		
		System.out.println("Algoritmo DFS:");
		g.DFS();
        
		//Teste grafo - Matriz
		
		System.out.println("\n");
		System.out.println("Teste grafo - Matriz");
		Grafo_Matriz g2 = new Grafo_Matriz(3);
		
		g2.adicionarAresta(1, 2);
		g2.adicionarAresta(2, 3);
		g2.adicionarAresta(3, 1);

		System.out.println(g2.toString());
		System.out.println(g2.isEuleriano());
		System.out.println(g2.hasPercursoAbertoEuler());

		maxGrau_index = g2.maxGrau();
        System.out.println("O vértice com maior grau no grafo é o vértice: " + maxGrau_index[1] + ", com grau igual a: " + maxGrau_index[0]);
        System.out.println("Verificando se o grafo é conexo!");
		System.out.println(g2.isConexo());
		System.out.println("\nAlgoritmo DFS:");
		g2.DFS();

		System.out.println("\nAdicionando vértice e aresta!\n");
		
		g2.adicionarVertice();
		
		g2.adicionarAresta(4, 2);
		g2.adicionarAresta(4, 3);
		
		System.out.println(g2.toString());
		System.out.println(g2.isEuleriano());
		System.out.println(g2.hasPercursoAbertoEuler());

		maxGrau_index = g2.maxGrau();
        System.out.println("O vértice com maior grau no grafo é o vértice: " + maxGrau_index[1] + ", com grau igual a: " + maxGrau_index[0]);
        System.out.println("Verificando se o grafo é conexo!");
		System.out.println(g2.isConexo());

		System.out.println("\nAlgoritmo DFS:");
		g2.DFS();*/
		
		Grafo_Matriz grafoMatriz = new Grafo_Matriz(4);
		
		grafoMatriz.adicionarAresta(1, 2);
		grafoMatriz.adicionarAresta(1, 3);
		grafoMatriz.adicionarAresta(2, 3);
		grafoMatriz.adicionarAresta(4, 4);
		
		grafoMatriz.ComponentesConectados();
		
		for(Integer i : grafoMatriz.getId()) {
			System.out.print(i + " ");
		}
	}

}
