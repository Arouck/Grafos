package br.ufpa.grafos.teste;

import br.ufpa.grafos.classes.Grafo_Lista;
import br.ufpa.grafos.classes.Grafo_Matriz;

public class Teste {

	public static void main(String[] args) {
		// Teste grafo - Lista
		System.out.println("Teste grafo - Lista");
		Grafo_Lista g = new Grafo_Lista(3);
		
		g.AdicionarAresta(1, 2);
		g.AdicionarAresta(2, 3);
		g.AdicionarAresta(3, 1);
		
		System.out.println(g.toString());
		System.out.println(g.isEuleriano());
		System.out.println(g.hasPercursoAbertoEuler());
		
		g.AdicionarVertice();
		
		g.AdicionarAresta(4, 2);
		g.AdicionarAresta(4, 3);
		
		System.out.println("\n" + g.toString());
		System.out.println(g.isEuleriano());
		System.out.println(g.hasPercursoAbertoEuler());
		
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
		
		g2.adicionarVertice();
		
		g2.adicionarAresta(4, 2);
		g2.adicionarAresta(4, 3);
		
		System.out.println("\n" + g2.toString());
		System.out.println(g2.isEuleriano());
		System.out.println(g2.hasPercursoAbertoEuler());
	}

}
