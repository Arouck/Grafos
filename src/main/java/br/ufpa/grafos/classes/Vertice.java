package br.ufpa.grafos.classes;

import java.util.ArrayList;
import java.util.List;

public class Vertice {
	private Integer d;
	private Integer f;
	private Vertice anterior;
	private String cor;
	private Integer numero;
	private Integer grau;
	private List<Vertice> adjacentes;
	private Boolean marcado;
	private Integer id;

	public Vertice(Integer i) {
		this.numero = i;
		this.grau = 0;
		this.adjacentes = new ArrayList<Vertice>();
	}

	public Integer getGrau() {
		return grau;
	}

	public void setGrau(Integer grau) {
		this.grau = grau;
	}

	public List<Vertice> getAdjacentes() {
		return adjacentes;
	}

	public void setAdjacentes(List<Vertice> adjacentes) {
		this.adjacentes = adjacentes;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
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

	public Integer getD() {
		return d;
	}

	public void setD(Integer d) {
		this.d = d;
	}

	public Integer getF() {
		return f;
	}

	public void setF(Integer f) {
		this.f = f;
	}

	public Boolean getMarcado() {
		return marcado;
	}

	public void setMarcado(Boolean marcado) {
		this.marcado = marcado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
