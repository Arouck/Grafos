package br.ufpa.grafos.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
 * This class represents a non-oriented graph, using
 * adjacency lists as it's listOfVertices.
 * */
public class AdjacencyListGraph {
	/*
	 * Attribute used to count the number of connected components on graph.
	 */
	private Integer connectedComponentsAccountant;

	/*
	 * Attribute used to define the time of discovering oh the vertex using DFS
	 * algorithm.
	 */
	private Integer dfsTime;

	/*
	 * List of all vertices on the graph.
	 */
	private List<Vertex> listOfVertices;

	/*
	 * Total number of vertices on the graph.
	 */
	private Integer numberOfVertices;

	/*
	 * Total number of edges on the graph.
	 */
	private Integer numberOfEdges;

	/*
	 * Class constructor used to initialize the graph with the number of vertices
	 * defined by the user. All vertices are with it's adjacency list empty, the
	 * user must insert the edges that he wants.
	 */
	public AdjacencyListGraph(Integer numberOfVertices) {

		this.numberOfVertices = numberOfVertices;
		listOfVertices = new ArrayList<Vertex>();

		for (Integer i = 0; i < numberOfVertices; i++) {
			Vertex vertex = new Vertex(i + 1);
			listOfVertices.add(vertex);
		}

		this.numberOfEdges = 0;
	}

	/*
	 * Method used to find all connected components on the graph.
	 */
	public void findConnectedComponents() {
		for (Integer s = 0; s < numberOfVertices; s++) {
			Vertex vertex = listOfVertices.get(s);
			if (!vertex.getMatched()) {
				DFS(vertex.getNumber());
				connectedComponentsAccountant++;
			}
		}
	}

	/*
	 * DFS algorithm used in the findConnectedComponents method. It was used to find
	 * the vertices that are in the connected component with there is any.
	 */
	public void DFS(Integer numeroFonte) {
		Vertex font = listOfVertices.get(numeroFonte - 1);

		font.setMatched(true);
		font.setConnectedComponentId(connectedComponentsAccountant);

		for (Vertex vertex : font.getAdjacentVertices()) {
			if (!vertex.getMatched()) {
				DFS(vertex.getNumber());
			}
		}
	}

	/*
	 * BFS algorithm used to find all vertices in the graph.
	 */
	public void BFS(Integer numeroFonte) {
		boolean visited[] = new boolean[numberOfVertices];

		Vertex font = listOfVertices.get(numeroFonte - 1);

		LinkedList<Vertex> queue = new LinkedList<Vertex>();

		visited[font.getNumber() - 1] = true;
		queue.add(font);

		while (!queue.isEmpty()) {
			font = queue.poll();

			System.out.print(font.getNumber() + " ");

			Iterator<Vertex> adjacencyQueue = font.getAdjacentVertices().listIterator();

			while (adjacencyQueue.hasNext()) {
				Vertex vertex = adjacencyQueue.next();

				if (!visited[vertex.getNumber() - 1]) {
					visited[vertex.getNumber() - 1] = true;

					queue.add(vertex);
				}
			}
		}
	}

	/*
	 * Method used to know if the graph is connected. From vertex number 1 it starts
	 * to get all it adjacent vertices and add them in a queue and starts to search
	 * for all adjacent vertices from the vertices in the queue, until it has no
	 * vertex to enqueue anymore.
	 */
	public Boolean isConnected() {
		Integer index = 0;
		List<Vertex> connection = new ArrayList<Vertex>();
		List<Vertex> queue = new ArrayList<Vertex>();
		connection.add(listOfVertices.get(0));

		boolean parar = false;

		while (parar == false) {
			for (Vertex vertex : listOfVertices.get(index).getAdjacentVertices()) {
				if (!connection.contains(vertex)) {
					queue.add(vertex);
					connection.add(vertex);
				}
			}

			if (queue.isEmpty()) {
				parar = true;
			} else {
				index = queue.get(0).getNumber() - 1;
				queue.remove(0);
			}
		}

		for (Vertex vertex : listOfVertices) {
			if (!connection.contains(vertex)) {
				return false;
			}
		}

		return true;
	}

	/*
	 * This method returns the maximum degree of a graph and the vertex that has
	 * this degree.
	 */
	public Integer[] maximumDegree() {
		Integer[] maximumDegreeAndVertex = { 0, 0 };
		for (Vertex vertex : listOfVertices) {
			if (maximumDegreeAndVertex[0] < vertex.getDegree()) {
				maximumDegreeAndVertex[0] = vertex.getDegree();
				maximumDegreeAndVertex[1] = vertex.getNumber() - 1;
			}
		}

		return maximumDegreeAndVertex;
	}

	/*
	 * This method is responsible for add a vertex in the graph.
	 */
	public void addVertex() {
		numberOfVertices += 1;
		Vertex vertex = new Vertex(numberOfVertices);

		listOfVertices.add(vertex);
	}

	/*
	 * Auxiliary method for the DFS algorithm. It paints the vertices of "Gray" when
	 * the vertex was already discovered and "Black" if it discovered all vertex
	 * adjacent vertices, it saves the time of discovering of the vertex and the
	 * previous vertex, too.
	 */
	public String visitDFS(Vertex vertex, String message) {
		vertex.setColor("Gray");
		dfsTime += 1;
		vertex.setDiscovery(dfsTime);
		message += vertex.getNumber() + " ";

		Iterator<Vertex> adjacentVertex = vertex.getAdjacentVertices().listIterator();
		while (adjacentVertex.hasNext()) {
			Vertex auxiliaryVertex = adjacentVertex.next();
			if (listOfVertices.get(auxiliaryVertex.getNumber() - 1).getColor().equals("White")) {
				auxiliaryVertex.setPreviousVertex(vertex);
				message = visitDFS(auxiliaryVertex, message);
			}
		}
		vertex.setColor("Preto");
		dfsTime += 1;
		vertex.setFinalization(dfsTime);
		return message;
	}

	/*
	 * Second DFS algorithm, used to discover the vertices in a connected graph.
	 */
	public void DFS() {
		String message = "";
		if (this.isConnected() == true) {
			for (Integer i = 0; i < numberOfVertices; i++) {
				listOfVertices.get(i).setColor("White");
				listOfVertices.get(i).setPreviousVertex(null);
			}
			dfsTime = 0;
			System.out.println(visitDFS(listOfVertices.get(0), message));
		} else {
			System.out.println("It's not possible to execute the DFS correctly, because this graph is not connected!");
		}
	}

	/*
	 * This method identifies if the graph has an eulerian path.
	 * For a graph have an eulerian path, it must have exactly two
	 * odd degree vertices, and it has to be connected.
	 * */
	public String hasEulerianPath() {
		Integer oddDegreeVertices = 0;

		if(this.isConnected() == true) {
			for (Vertex vertex : listOfVertices) {
				if (vertex.getDegree() % 2 != 0) {
					oddDegreeVertices++;
				}
			}

			if (oddDegreeVertices == 2) {
				return "This graph has an eulerian path!";
			} else {
				return "This graph doesn't have an eulerian path!";
			}
		} else {
			return "This graph doesn't have an eulerian path!";
		}
		
	}

	/*
	 * This method identifies if the graph has an eulerian circuit.
	 * The conditions to have an eulerian circuit, all graph vertices
	 * must have an even degree and the graph must be connected.
	 * */
	public String hasEulerianCircuit() {
		for (Vertex vertex : listOfVertices) {
			if (vertex.getDegree() % 2 != 0) {
				return "This graph doesn't have an eulerian circuit!";
			}
		}

		return "This graph has an eulerian circuit!";
	}

	/*
	 * This method is used to add an edge between two vertices, if the vertices
	 * exist in the graph, of course.
	 * */
	public void addEdge(Integer vertexNumber1, Integer vertexNumber2) {
		boolean found = false;

		for (Vertex vertexA : listOfVertices) {
			if (vertexA.getNumber() == vertexNumber1) {

				for (Vertex vertexB : listOfVertices) {
					if (vertexB.getNumber() == vertexNumber2) {

						vertexA.getAdjacentVertices().add(vertexB);
						vertexA.setDegree(vertexA.getDegree() + 1);

						vertexB.getAdjacentVertices().add(vertexA);
						vertexB.setDegree(vertexB.getDegree() + 1);

						found = true;

						this.numberOfEdges++;
					}

				}
			}
		}

		if (found == false) {
			System.out.println("Vertices not found, it's not possible to add the edge!");
		}

	}

	/*
	 * Method to represent the graph by an string.
	 * */
	@Override
	public String toString() {
		String message = "Graph {[Vertices = ";
		Integer i;

		for (i = 0; i < listOfVertices.size(); i++) {
			if (i == listOfVertices.size() - 1) {
				message += listOfVertices.get(i).getNumber() + "], [Edges = ";
			} else {
				message += listOfVertices.get(i).getNumber() + ", ";
			}
		}

		i = 0;
		Integer temp = 0;
		for (Vertex vertex : listOfVertices) {
			temp = 0;
			for (Vertex vertice_adjacente : vertex.getAdjacentVertices()) {
				if (i == listOfVertices.size() - 1 && temp == vertex.getAdjacentVertices().size() - 1) {
					message += "(" + vertex.getNumber() + ", " + vertice_adjacente.getNumber() + ")]}";
				} else {
					message += "(" + vertex.getNumber() + ", " + vertice_adjacente.getNumber() + "), ";
				}
				temp++;
			}
			i++;
		}

		return message;
	}

	/*
	 * Getters and Setters.
	 * */
	public Integer getNumeroVertices() {
		return numberOfVertices;
	}

	public void setNumeroVertices(Integer numberOfVertices) {
		this.numberOfVertices = numberOfVertices;
	}

	public Integer getNumeroArestas() {
		return numberOfEdges;
	}

	public void setNumeroArestas(Integer numberOfEdges) {
		this.numberOfEdges = numberOfEdges;
	}

	public List<Vertex> getVertices() {
		return listOfVertices;
	}

	public void setVertices(List<Vertex> listOfVertices) {
		this.listOfVertices = listOfVertices;
	}

	public Integer getTempo() {
		return dfsTime;
	}

	public void setTempo(Integer dfsTime) {
		this.dfsTime = dfsTime;
	}

	public Integer getconnectedComponentsAccountant() {
		return connectedComponentsAccountant;
	}

	public void setconnectedComponentsAccountant(Integer connectedComponentsAccountant) {
		this.connectedComponentsAccountant = connectedComponentsAccountant;
	}
}