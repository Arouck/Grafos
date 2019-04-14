package br.ufpa.grafos.classes;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
	private int d;
	private int f;
	private Vertice anterior;
	private String cor;
	private int numero;
	private int grau;
	private List<Vertice> adjacentes;
	
	public Vertice(int i) {
		this.numero = i;
		this.grau = 0;
		this.adjacentes = new ArrayList<Vertice>();
	}
	
	public int getGrau() {
		return grau;
	}
	public void setGrau(int grau) {
		this.grau = grau;
	}
	public List<Vertice> getAdjacentes() {
		return adjacentes;
	}
	public void setAdjacentes(List<Vertice> adjacentes) {
		this.adjacentes = adjacentes;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Vertice getAnterior() {
		return anterior;
	}
	public void setAnterior(Vertice anterior) {
		this.anterior = anterior;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	public int getF() {
		return f;
	}
	public void setF(int f) {
		this.f = f;
	}
	
}
