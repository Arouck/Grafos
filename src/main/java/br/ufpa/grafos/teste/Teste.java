package br.ufpa.grafos.teste;

//import br.ufpa.grafos.classes.AdjacencyListGraph;
import br.ufpa.grafos.classes.AdjacencyMatrixGraph;

/*import br.ufpa.grafos.classes.AdjacencyListGraph;
import br.ufpa.grafos.classes.AdjacencyMatrixGraph;*/

public class Teste {

	public static void main(String[] args) {
		// Teste grafo - Lista
		/*System.out.println("Teste grafo - Lista");
		AdjacencyListGraph graph = new AdjacencyListGraph(3);
		
		graph.addEdge(1, 2);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);
		
		System.out.println(graph.toString());
		System.out.println(graph.hasEulerianCircuit());
		System.out.println(graph.hasEulerianPath());

		Integer[] maxGrau_index = graph.maximumDegree();
        System.out.println("O vértice com maior grau no grafo é o vértice: " + graph.getVertices().get(maxGrau_index[1]).getNumber() + ", com grau igual a: " + maxGrau_index[0]);
        
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
		System.out.println("O vértice com maior grau no grafo é o vértice: " + graph.getVertices().get(maxGrau_index[1]).getNumber() + ", com grau igual a: " + maxGrau_index[0]);
        System.out.println("Verificando se o grafo é conexo!");
		System.out.println(graph.isConnected());
		
		System.out.println("Algoritmo DFS:");
		graph.DFS();
        
		//Teste grafo - Matriz
		
		System.out.println("\n");
		System.out.println("Teste grafo - Matriz");
		AdjacencyMatrixGraph g2 = new AdjacencyMatrixGraph(3);
		
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
		
		AdjacencyMatrixGraph grafoMatriz = new AdjacencyMatrixGraph(4);
		
		grafoMatriz.adicionarAresta(1, 2);
		grafoMatriz.adicionarAresta(1, 3);
		grafoMatriz.adicionarAresta(2, 3);
		grafoMatriz.adicionarAresta(4, 4);
		
		grafoMatriz.ComponentesConectados();
		
		for(Integer i : grafoMatriz.getId()) {
			System.out.print(i + " ");
		}*/
		
		AdjacencyMatrixGraph g = new AdjacencyMatrixGraph(4);
		g.adicionarAresta(1, 2);
		g.adicionarAresta(2, 1);
		g.adicionarAresta(2, 3);
		g.adicionarAresta(3, 4);
		//g.warshall();
		
		System.out.println(g.toString());
		
		AdjacencyMatrixGraph g2 = new AdjacencyMatrixGraph(4);
		
		g.transposta(g.getGrafo(), g2.getGrafo());
		
		System.out.println(g2.toString());
	}

}
