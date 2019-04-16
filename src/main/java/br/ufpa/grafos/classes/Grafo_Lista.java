package br.ufpa.grafos.classes;

import java.util.ArrayList;
import java.util.List;

public class Grafo_Lista {
    private int tempo;
	private List<Vertice> vertices;
	private int numeroVertices;
    private int numeroArestas;
    
    public String isConexo() {
        int index = 0;
        List<Vertice> conexao = new ArrayList<Vertice>();
        List<Vertice> fila = new ArrayList<Vertice>();
        conexao.add(vertices.get(0));

        boolean parar = false;

        while(parar == false) {
            for(Vertice vertice : vertices.get(index).getAdjacentes()) {
                if(!conexao.contains(vertice)) {
                    fila.add(vertice); 
                    conexao.add(vertice);
                }
            }

            if(fila.isEmpty()) {
                parar = true;
            } else {
                index = fila.get(0).getNumero() - 1;
                fila.remove(0);
            }
        }

        for(Vertice vertice : vertices) {
            if(!conexao.contains(vertice)) {
                return "O grafo não é conexo!";
            }
        }

        return "O grafo é conexo!";
    }

	public int[] maxGrau() {
		int[] maxGrau_index = {0, 0};
		for(Vertice vertice : vertices) {
			if(maxGrau_index[0] < vertice.getGrau()) {
				maxGrau_index[0] = vertice.getGrau();
				maxGrau_index[1] = vertice.getNumero() - 1;
			}
		}

		return maxGrau_index;
	}
	
	public void AdicionarVertice() {
		numeroVertices += 1;
		Vertice vertice = new Vertice(numeroVertices);
		
		vertices.add(vertice);
	}

	public Grafo_Lista(int numeroVertices) {

		this.numeroVertices = numeroVertices;
		vertices = new ArrayList<Vertice>();
		
		for (int i = 0; i < numeroVertices; i++) {
			Vertice vertice = new Vertice(i + 1);
			vertices.add(vertice);
		}

		this.numeroArestas = 0;
	}
	
	public String DFS_Visit(Vertice vertice, String msg) {
		vertice.setCor("Cinza");
		tempo += 1;
        vertice.setD(tempo);
        msg += " " + vertice.getNumero();
		for(Vertice verticeADJ : vertice.getAdjacentes()) {
			if(verticeADJ.getCor().equals("Branco")) {
				verticeADJ.setAnterior(vertice);
				DFS_Visit(verticeADJ, msg);
			}
		}
		vertice.setCor("Preto");
		tempo += 1;
        vertice.setF(tempo);
        
        return msg;
	}
	
	public void DFS() {
        String msg = "";
		for(int i = 0; i < numeroVertices; i++) {
			vertices.get(i).setCor("Branco");
			vertices.get(i).setAnterior(null);
		}
		tempo = 0;
		for(int i = 0; i < numeroVertices; i++) {
			if(vertices.get(i).getCor().equals("Branco")) {
				msg += DFS_Visit(vertices.get(i), msg);
			}
        }
        
        System.out.println(msg);
	}
	
	public String hasPercursoAbertoEuler() {
		int vertices_grau_impar = 0;
		
		for(Vertice vertice : vertices) {
			if(vertice.getGrau()%2 != 0) {
				vertices_grau_impar++;
			}
		}
		
		if(vertices_grau_impar == 2) {
			return "O grafo tem um percurso aberto de Euler!";
		} else {
			return "O grafo não tem um percurso aberto de Euler!";
		}
	}
	
	public String isEuleriano() {
		for(Vertice vertice : vertices) {
			if(vertice.getGrau()%2 != 0) {
				return "O grafo não é Euleriano!";
			}
		}
		
		return "O grafo é Euleriano!";
	}

	public void AdicionarAresta(int vertice_1, int vertice_2) {
		boolean encontrou = false;
		
		for(Vertice vertice_A : vertices) {
			if(vertice_A.getNumero() == vertice_1) {
				
				for(Vertice vertice_B : vertices) {
					if(vertice_B.getNumero() == vertice_2) {
						
						vertice_A.getAdjacentes().add(vertice_B);
						vertice_A.setGrau(vertice_A.getGrau() + 1);
						
						vertice_B.getAdjacentes().add(vertice_A);
						vertice_B.setGrau(vertice_B.getGrau() + 1);
						
						encontrou = true;
						
						this.numeroArestas++;
					}
					
				}
			}
		}

		if(encontrou == false) {
			System.out.println("Não foi possível adicionar a aresta! Vértice não encontrado.");
		}

	}

	@Override
	public String toString() {
		String msg = "Grafo {[Vértices = ";
		int i;
		
		for(i = 0; i < vertices.size(); i++) {
			if (i == vertices.size() - 1) {
				msg += vertices.get(i).getNumero() + "], [Arestas = ";
			} else {
				msg += vertices.get(i).getNumero() + ", ";
			}
		}
		
		i = 0;
		int temp = 0;
		for(Vertice vertice : vertices) {
			temp = 0;
			for(Vertice vertice_adjacente : vertice.getAdjacentes()) {
				if(i == vertices.size() - 1 && temp == vertice.getAdjacentes().size() - 1) {
					msg += "(" + vertice.getNumero() + ", " + vertice_adjacente.getNumero() + ")]}";
				} else {
					msg += "(" + vertice.getNumero() + ", " + vertice_adjacente.getNumero() + "), ";
				}
				temp++;
			}
			i++;
		}
		
		return msg;
	}

	public int getNumeroVertices() {
		return numeroVertices;
	}

	public void setNumeroVertices(int numeroVertices) {
		this.numeroVertices = numeroVertices;
	}

	public int getNumeroArestas() {
		return numeroArestas;
	}

	public void setNumeroArestas(int numeroArestas) {
		this.numeroArestas = numeroArestas;
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertice> vertices) {
		this.vertices = vertices;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
}