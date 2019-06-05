package br.ufpa.grafos.classes;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * This class represents a graph using a adjacency matrix.
 * */
public class AdjacencyMatrixGraph {

	private Integer numeroVertices;
	private Integer numeroArestas;
	private Byte[][] grafo;
	private Integer[][] valores;
	private String[] cor;
	private Integer[] d;
	private Integer tempo;
	private Integer[] f;
	private Integer[] anterior;
	private Integer[] id;
	private boolean[] marcado;
	private Integer contador;
	private List<Integer> topologicalSort;
	private Integer[] grauEntrada;
	private Integer[] grauSaida;
	private LinkedList<Integer> adj[];
	private Boolean controller;
	private Byte[][] warshalG;

	public Integer minDistance(Integer distancia[], Boolean shortestPathTree[]) {
		int min = Integer.MAX_VALUE;
		int min_index = -1;

		for (int v = 0; v < numeroVertices; v++)
			if (shortestPathTree[v] == false && distancia[v] <= min) {
				min = distancia[v];
				min_index = v;
			}

		return min_index;
	}

	public void printSolution(Integer distancia[]) {

		System.out.println("Vertex   Distance from Source");

		for (int i = 0; i < numeroVertices; i++) {
			int j = i + 1;
			System.out.println(j + " \t\t " + distancia[i]);
		}
	}

	public void dijkstra(int fonte) {

		controller = true;

		for (int i = 0; i < numeroVertices; i++) {
			for (int j = 0; j < numeroVertices; j++) {
				if (valores[i][j] < 0) {
					controller = false;
				}
			}
		}

		if (controller == false) {
			System.out.println("This graph has an negative edge!");
		} else {
			Integer distancia[] = new Integer[numeroVertices];

			Boolean shortestPathTree[] = new Boolean[numeroVertices];

			for (int i = 0; i < numeroVertices; i++) {
				distancia[i] = Integer.MAX_VALUE;
				shortestPathTree[i] = false;
			}

			distancia[fonte] = 0;

			for (int count = 0; count < numeroVertices - 1; count++) {

				Integer u = minDistance(distancia, shortestPathTree);

				shortestPathTree[u] = true;

				for (int v = 0; v < numeroVertices; v++) {
					if (!shortestPathTree[v] && valores[u][v] != 0 && distancia[u] != Integer.MAX_VALUE
							&& distancia[u] + valores[u][v] < distancia[v]) {
						distancia[v] = distancia[u] + valores[u][v];
					}
				}
			}

			printSolution(distancia);
		}
	}

	public void DFSUtil2(int v, boolean visited[]) {
		visited[v] = true;
		System.out.print(v + 1 + " ");

		int n;

		Iterator<Integer> i = adj[v].iterator();
		while (i.hasNext()) {
			n = i.next();
			if (!visited[n])
				DFSUtil2(n, visited);
		}
	}

	public void fillOrder(int v, boolean visited[], Stack<Integer> stack) {
		visited[v] = true;

		Iterator<Integer> i = adj[v].iterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				fillOrder(n, visited, stack);
		}

		stack.push(new Integer(v));
	}

	public void printSCCs() {
		Stack<Integer> stack = new Stack<>();

		boolean visited[] = new boolean[numeroVertices];
		for (int i = 0; i < numeroVertices; i++)
			visited[i] = false;

		for (int i = 0; i < numeroVertices; i++)
			if (visited[i] == false)
				fillOrder(i, visited, stack);

		AdjacencyMatrixGraph gr = new AdjacencyMatrixGraph(this.numeroVertices);

		this.transposta(this.grafo, gr);

		for (int i = 0; i < numeroVertices; i++)
			visited[i] = false;

		while (stack.empty() == false) {
			
			int v = (int) stack.pop();

			if (visited[v] == false) {
				gr.DFSUtil2(v, visited);
				System.out.println();
			}
		}
	}

	public void topologicalSortUtil(int v, boolean visited[], Stack<Integer> stack) {
		visited[v] = true;
		Integer i;

		Iterator<Integer> it = adj[v].iterator();
		while (it.hasNext()) {
			i = it.next();
			if (!visited[i]) {
				topologicalSortUtil(i, visited, stack);
			}
		}

		stack.push(new Integer(v));
	}

	public void topologicalSort() {
		Stack<Integer> stack = new Stack<>();

		boolean visited[] = new boolean[numeroVertices];
		for (int i = 0; i < numeroVertices; i++) {
			visited[i] = false;
		}
		
		for (int i = 0; i < numeroVertices; i++) {
			if (visited[i] == false) {
				topologicalSortUtil(i, visited, stack);
			}
		}
		
		while (stack.empty() == false) {
			System.out.print(stack.pop() + 1 + " ");
		}
	}

	@SuppressWarnings("unchecked")
	public void transposta(Byte[][] G, AdjacencyMatrixGraph Graph2) {
		Byte[][] G2 = new Byte[numeroVertices][numeroVertices];

		for (int i = 0; i < numeroVertices; i++) {
			for (int j = 0; j < numeroVertices; j++) {
				G2[i][j] = G[j][i];
			}
		}

		LinkedList<Integer> adj2[];

		adj2 = new LinkedList[numeroVertices];

		for (int i = 0; i < numeroVertices; i++) {
			adj2[i] = new LinkedList<>();
		}

		for (int i = 0; i < numeroVertices; i++) {
			for (int j = 0; j < numeroVertices; j++) {
				if (G2[i][j] == 1) {
					adj2[i].add(j);
				}
			}
		}

		Graph2.setGrafo(G2);
		Graph2.setAdj(adj2);
	}

	public void warshall() {
		warshalG = grafo.clone();
		
		for (int k = 0; k < numeroVertices; k++) {
			for (int i = 0; i < numeroVertices; i++) {
				for (int j = 0; j < numeroVertices; j++) {
					if (warshalG[i][j] != 1) {
						if ((warshalG[i][k] == 1) && (warshalG[k][j] == 1)) {
							warshalG[i][j] = 1;
						}
					}
				}
			}
		}
	}

	public void ComponentesConectados() {
		marcado = new boolean[numeroVertices];
		id = new Integer[numeroVertices];

		for (Integer s = 0; s < numeroVertices; s++) {
			if (!marcado[s]) {
				DFS(s + 1);
				contador++;
			}
		}
	}

	public void DFS(Integer fonte) {
		marcado[fonte - 1] = true;
		id[fonte - 1] = contador;

		for (Integer i = 0; i < numeroVertices; i++) {
			if (grafo[fonte - 1][i] == 1) {
				if (!marcado[i]) {
					DFS(i + 1);
				}
			}
		}
	}

	public void BFS(Integer fonte) {
		boolean visitado[] = new boolean[numeroVertices];

		LinkedList<Integer> fila = new LinkedList<Integer>();
		List<Integer> adjacentes = new ArrayList<>();

		visitado[fonte - 1] = true;
		fila.add(fonte);

		while (!fila.isEmpty()) {
			fonte = fila.poll();

			System.out.print(fonte + " ");

			for (Integer i = 0; i < numeroVertices; i++) {
				if (grafo[fonte - 1][i] == 1) {
					adjacentes.add(i + 1);
				}
			}

			Iterator<Integer> filaADJ = adjacentes.listIterator();

			while (filaADJ.hasNext()) {
				Integer vertice = filaADJ.next();

				if (!visitado[vertice - 1]) {
					visitado[vertice - 1] = true;

					fila.add(vertice);
				}
			}
		}
	}

	public String DFS_Visit(Integer i, String msg) {
		cor[i] = "Cinza";
		tempo += 1;
		d[i] = tempo;
		msg += (i + 1) + " ";

		List<Integer> adj = new ArrayList<>();

		for (Integer j = 0; j < numeroVertices; j++) {
			if (grafo[i][j] == 1) {
				adj.add(j);
			}
		}

		if (!adj.isEmpty()) {
			for (Integer j = 0; j < adj.size(); j++) {
				Integer vertice = adj.get(j);
				if (cor[vertice].equals("Branco")) {
					anterior[vertice] = i;
					msg = DFS_Visit(vertice, msg);
				}
			}
		}

		cor[i] = "Preto";
		tempo += 1;
		f[i] = tempo;
		return msg;
	}

	public void DFS() {
		String msg = "";
		if (this.isConexo().equals("O grafo ï¿½ conexo!")) {
			for (Integer i = 0; i < numeroVertices; i++) {
				cor[i] = "Branco";
				anterior[i] = null;
			}

			tempo = 0;
			System.out.println(DFS_Visit(0, msg));
		} else {
			System.out.println("Nï¿½o ï¿½ possï¿½vel executar o DFS para esse grafo, pois ele nï¿½o ï¿½ conexo!");
		}
	}

	public void adicionarVertice() {
		numeroVertices += 1;
		Byte[][] novoGrafo = new Byte[numeroVertices][numeroVertices];

		for (Integer i = 0; i < numeroVertices; i++) {
			for (Integer j = 0; j < numeroVertices; j++) {
				if (i == numeroVertices - 1) {
					novoGrafo[i][j] = 0;
				} else {
					if (j == numeroVertices - 1) {
						novoGrafo[i][j] = 0;
					} else {
						novoGrafo[i][j] = grafo[i][j];
					}
				}
			}
		}

		cor = new String[numeroVertices];
		d = new Integer[numeroVertices];
		f = new Integer[numeroVertices];
		anterior = new Integer[numeroVertices];
		grauEntrada = new Integer[numeroVertices];
		grauSaida = new Integer[numeroVertices];

		setGrafo(novoGrafo);
	}

	public String isConexo() {
		Integer index = 0;
		boolean parar = false;
		List<Integer> fila = new ArrayList<Integer>();
		List<Integer> conexao = new ArrayList<Integer>();

		conexao.add(index);

		while (parar == false) {
			for (Integer i = 0; i < numeroVertices; i++) {
				if ((grafo[index][i] == 1) && (!conexao.contains(i))) {
					fila.add(i);
					conexao.add(i);
				}
			}

			if (fila.isEmpty()) {
				parar = true;
			} else {
				index = fila.get(0);
				fila.remove(0);
			}
		}

		for (Integer i = 0; i < numeroVertices; i++) {
			if (!conexao.contains(i)) {
				return "O grafo nï¿½o ï¿½ conexo!" + i;
			}
		}

		return "O grafo ï¿½ conexo!";
	}

	public Integer[] maxGrau() {
		Integer[] maxGrau_index = { 0, 0 };
		Integer grau = 0;
		for (Integer i = 0; i < numeroVertices; i++) {
			for (Integer j = 0; j < numeroVertices; j++) {
				if (grafo[i][j] == 1) {
					grau++;
				}
			}
			if (maxGrau_index[0] < grau) {
				maxGrau_index[0] = grau;
				maxGrau_index[1] = i + 1;
			}
			grau = 0;
		}

		return maxGrau_index;
	}

	@SuppressWarnings("unchecked")
	public AdjacencyMatrixGraph(Integer numeroVertices) {
		this.numeroVertices = numeroVertices;
		grafo = new Byte[numeroVertices][numeroVertices];
		valores = new Integer[numeroVertices][numeroVertices];
		warshalG = new Byte[numeroVertices][numeroVertices];
		cor = new String[numeroVertices];
		d = new Integer[numeroVertices];
		f = new Integer[numeroVertices];
		anterior = new Integer[numeroVertices];
		grauEntrada = new Integer[numeroVertices];
		grauSaida = new Integer[numeroVertices];
		this.tempo = 0;
		contador = 0;

		for (Integer i = 0; i < numeroVertices; i++) {
			for (Integer j = 0; j < numeroVertices; j++) {
				grafo[i][j] = 0;
				valores[i][j] = Integer.MAX_VALUE;
				warshalG[i][j] = 0;
			}
			grauEntrada[i] = 0;
			grauSaida[i] = 0;
		}

		numeroArestas = 0;
		topologicalSort = new ArrayList<>();
		adj = new LinkedList[numeroVertices];

		for (int i = 0; i < numeroVertices; i++) {
			adj[i] = new LinkedList<>();
		}
	}

	public void adicionarAresta(Integer vertice_1, Integer vertice_2) {
		vertice_1 -= 1;
		vertice_2 -= 1;
		grafo[vertice_1][vertice_2] = 1;
		adj[vertice_1].add(vertice_2);

		grauEntrada[vertice_2]++;
		grauSaida[vertice_1]++;
		// grafo[vertice_2][vertice_1] = 1;
	}

	public void adicionarArestaValorada(Integer vertice_1, Integer vertice_2, Integer valor) {
		vertice_1 -= 1;
		vertice_2 -= 1;
		grafo[vertice_1][vertice_2] = 1;
		valores[vertice_1][vertice_2] = valor;
		adj[vertice_1].add(vertice_2);

		grauEntrada[vertice_2]++;
		grauSaida[vertice_1]++;
		// grafo[vertice_2][vertice_1] = 1;
	}

	public String isEuleriano() {
		for (Integer i = 0; i < numeroVertices; i++) {
			Byte grau = 0;
			for (Integer j = 0; j < numeroVertices; j++) {
				if (grafo[i][j] == 1) {
					grau++;
				}
			}
			if (grau % 2 != 0) {
				return "O grafo não é Euleriano!";
			}
		}
		return "O grafo é Euleriano!";
	}

	public String hasPercursoAbertoEuler() {
		Byte numeroVerticesGrauImpar = 0;

		for (Integer i = 0; i < numeroVertices; i++) {
			Byte grau = 0;
			for (Integer j = 0; j < numeroVertices; j++) {
				if (grafo[i][j] == 1) {
					grau++;
				}
			}
			if (grau % 2 != 0) {
				numeroVerticesGrauImpar++;
			}
		}

		if (numeroVerticesGrauImpar == 2) {
			return "O grafo tem um percurso aberto de Euler!";
		} else {
			return "O grafo nÃ©o tem um percurso aberto de Euler!";
		}
	}

	public Integer getNumeroVertices() {
		return numeroVertices;
	}

	public void setNumeroVertices(Integer numeroVertices) {
		this.numeroVertices = numeroVertices;
	}

	public Integer getNumeroArestas() {
		return numeroArestas;
	}

	public void setNumeroArestas(Integer numeroArestas) {
		this.numeroArestas = numeroArestas;
	}

	public Byte[][] getGrafo() {
		return grafo;
	}

	public void setGrafo(Byte[][] grafo) {
		this.grafo = grafo;
	}

	public Integer[] getD() {
		return d;
	}

	public void setD(Integer[] d) {
		this.d = d;
	}

	public Integer[] getF() {
		return f;
	}

	public void setF(Integer[] f) {
		this.f = f;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public Integer[] getAnterior() {
		return anterior;
	}

	public void setAnterior(Integer[] anterior) {
		this.anterior = anterior;
	}

	public String[] getCor() {
		return cor;
	}

	public void setCor(String[] cor) {
		this.cor = cor;
	}

	public Integer[] getId() {
		return id;
	}

	public void setId(Integer[] id) {
		this.id = id;
	}

	public boolean[] getMarcado() {
		return marcado;
	}

	public void setMarcado(boolean[] marcado) {
		this.marcado = marcado;
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

	public Integer[][] getValores() {
		return valores;
	}

	public void setValores(Integer[][] valores) {
		this.valores = valores;
	}

	@Override
	public String toString() {
		String msg = "Grafo:\n  ";
		for (Integer i = 0; i < numeroVertices; i++) {
			if (i == numeroVertices - 1) {
				msg += (i + 1) + "\n";
			} else {
				msg += (i + 1) + "  ";
			}

		}
		for (Integer i = 0; i < numeroVertices; i++) {
			msg += (i + 1) + "[";
			for (Integer j = 0; j < numeroVertices; j++) {
				if (j == numeroVertices - 1) {
					msg += grafo[i][j] + "]\n";
				} else {
					msg += grafo[i][j] + ", ";
				}
			}
		}

		return msg;
	}
	
	public String toStringValues() {
		String msg = "Valores:\n  ";
		for (Integer i = 0; i < numeroVertices; i++) {
			if (i == numeroVertices - 1) {
				msg += (i + 1) + "\n";
			} else {
				msg += (i + 1) + "  ";
			}

		}
		for (Integer i = 0; i < numeroVertices; i++) {
			msg += (i + 1) + "[";
			for (Integer j = 0; j < numeroVertices; j++) {
				if (j == numeroVertices - 1) {
					msg += valores[i][j] + "]\n";
				} else {
					msg += valores[i][j] + ", ";
				}
			}
		}

		return msg;
	}
	
	public String toStringWarshall() {
		String msg = "Grafo:\n  ";
		for (Integer i = 0; i < numeroVertices; i++) {
			if (i == numeroVertices - 1) {
				msg += (i + 1) + "\n";
			} else {
				msg += (i + 1) + "  ";
			}

		}
		for (Integer i = 0; i < numeroVertices; i++) {
			msg += (i + 1) + "[";
			for (Integer j = 0; j < numeroVertices; j++) {
				if (j == numeroVertices - 1) {
					msg += warshalG[i][j] + "]\n";
				} else {
					msg += warshalG[i][j] + ", ";
				}
			}
		}

		return msg;
	}

	public List<Integer> getTopologicalSort() {
		return topologicalSort;
	}

	public void setTopologicalSort(List<Integer> topologicalSort) {
		this.topologicalSort = topologicalSort;
	}

	public Integer[] getGrauEntrada() {
		return grauEntrada;
	}

	public void setGrauEntrada(Integer[] grauEntrada) {
		this.grauEntrada = grauEntrada;
	}

	public Integer[] getGrauSaida() {
		return grauSaida;
	}

	public void setGrauSaida(Integer[] grauSaida) {
		this.grauSaida = grauSaida;
	}

	public LinkedList<Integer>[] getAdj() {
		return adj;
	}

	public void setAdj(LinkedList<Integer>[] adj) {
		this.adj = adj;
	}

	public Boolean getController() {
		return controller;
	}

	public void setController(Boolean controller) {
		this.controller = controller;
	}

	public Byte[][] getWarshalG() {
		return warshalG;
	}

	public void setWarshalG(Byte[][] warshalG) {
		this.warshalG = warshalG;
	}

}
