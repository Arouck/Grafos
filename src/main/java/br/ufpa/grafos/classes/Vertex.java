package br.ufpa.grafos.classes;

import java.util.ArrayList;
import java.util.List;

/*
 * This class represents a vertex used in the graph represented by a adjacency list.
 * */
public class Vertex {
	/*
	 * This attribute represents the time of discovery of the vertex
	 * in the DFS algorithm.
	 * */
	private Integer discovery;
	
	/*
	 * This attribute represents the time of finalization of the vertex (when it's
	 * color is turned to "Black") in the DFS algorithm.
	 * */
	private Integer finalization;
	
	/*
	 * This attribute represents the previous vertex of this vertex in the DFS
	 * algorithm.
	 * */
	private Vertex previousVertex;
	
	/*
	 * This attribute represents the vertex color in the DFS algorithm. 
	 * */
	private String color;
	
	/*
	 * This attribute represents the vertex number in the graph (starting by 1).
	 * */
	private Integer number;
	
	/*
	 * This attribute represents the vertex degree (number of adjacent vertices that
	 * it has).
	 * */
	private Integer degree;
	
	/*
	 * This attribute represents the list of adjacent vertices of the vertex.
	 * */
	private List<Vertex> adjacentVertices;
	
	/*
	 * This attribute represents if the vertex was matched or not in the connected components
	 * method.
	 * */
	private Boolean matched;
	
	/*
	 * This attribute represents the connectedComponentId of the connected component that vertex belongs.
	 * */
	private Integer connectedComponentId;

	/*
	 * Class constructor.
	 * */
	public Vertex(Integer i) {
		this.number = i;
		this.degree = 0;
		this.adjacentVertices = new ArrayList<Vertex>();
	}
	
	/*
	 * Getters and Setters.
	 * */

	public Integer getDiscovery() {
		return discovery;
	}

	public void setDiscovery(Integer discovery) {
		this.discovery = discovery;
	}

	public Integer getFinalization() {
		return finalization;
	}

	public void setFinalization(Integer finalization) {
		this.finalization = finalization;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public List<Vertex> getAdjacentVertices() {
		return adjacentVertices;
	}

	public void setAdjacentVertices(List<Vertex> adjacentVertices) {
		this.adjacentVertices = adjacentVertices;
	}

	public Boolean getMatched() {
		return matched;
	}

	public void setMatched(Boolean matched) {
		this.matched = matched;
	}

	public Integer getConnectedComponentId() {
		return connectedComponentId;
	}

	public void setConnectedComponentId(Integer connectedComponentId) {
		this.connectedComponentId = connectedComponentId;
	}

	public Vertex getPreviousVertex() {
		return previousVertex;
	}

	public void setPreviousVertex(Vertex previousVertex) {
		this.previousVertex = previousVertex;
	}

}
