package br.ufpa.grafos.teste;

/*import br.ufpa.grafos.classes.ListNonOrientedGraph;
import br.ufpa.grafos.classes.GrafoMatrizNaoDirecionado;*/

public class Teste {

	public static void main(String[] args) {
		// Teste grafo - Lista
		/*System.out.println("Teste grafo - Lista");
		ListNonOrientedGraph graph = new ListNonOrientedGraph(3);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
		
		System.out.println(graph.toString());
		System.out.println(graph.hasEulerianCircuit());
		System.out.println(graph.hasEulerianPath());

		Integer[] maxGrau_index = graph.maximumDegree();
        System.out.println("O vértice com maior grau no grafo é o vértice: " + graph.getVertices().get(maxGrau_index[1]).getNumero() + ", com grau igual a: " + maxGrau_index[0]);
        
        System.out.println("Verificando se o grafo é conexo!");
		System.out.println(graph.isConnected());
		System.out.println("Algoritmo DFS:");
		graph.DFS();

		System.out.println("\nAdicionando vértice e aresta!\n");

		graph.addVertex();
		
		graph.addEdge(4, 2);
		graph.addEdge(4, 3);
		
		System.out.println(graph.toString());
		System.out.println(graph.hasEulerianCircuit());
		System.out.println(graph.hasEulerianPath());

		maxGrau_index = graph.maximumDegree();
		System.out.println("O vértice com maior grau no grafo é o vértice: " + graph.getVertices().get(maxGrau_index[1]).getNumero() + ", com grau igual a: " + maxGrau_index[0]);
        System.out.println("Verificando se o grafo é conexo!");
		System.out.println(graph.isConnected());
		
		System.out.println("Algoritmo DFS:");
		graph.DFS();
        
		//Teste grafo - Matriz
		
		System.out.println("\n");
		System.out.println("Teste grafo - Matriz");
		GrafoMatrizNaoDirecionado g2 = new GrafoMatrizNaoDirecionado(3);
		
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
		g2.DFS();
		
		GrafoMatrizNaoDirecionado grafoMatriz = new GrafoMatrizNaoDirecionado(4);
		
		grafoMatriz.adicionarAresta(1, 2);
		grafoMatriz.adicionarAresta(1, 3);
		grafoMatriz.adicionarAresta(2, 3);
		grafoMatriz.adicionarAresta(4, 4);
		
		grafoMatriz.ComponentesConectados();
		
		for(Integer i : grafoMatriz.getId()) {
			System.out.print(i + " ");
		}*/
	}

}
