package br.ufpa.grafos.classes;

import java.util.List;
import java.util.ArrayList;

public class Grafo_Matriz {

	private int numeroVertices;
	private int numeroArestas;
	private byte[][] grafo;
	
	public void adicionarVertice() {
		numeroVertices += 1;
		byte[][] novoGrafo = new byte[numeroVertices][numeroVertices];
		
		for(int i = 0; i < numeroVertices; i++) {
			for(int j = 0; j < numeroVertices; j++) {
				if(i == numeroVertices - 1) {
					novoGrafo[i][j] = 0;
				} else {
					if(j == numeroVertices - 1) {
						novoGrafo[i][j] = 0;
					} else {
						novoGrafo[i][j] = grafo[i][j];
					}
				}
			}
		}
		
		setGrafo(novoGrafo);
    }
    
    public String isConexo() {
        int index = 0;
        boolean parar = false;
        List<Integer> fila = new ArrayList<Integer>();
        List<Integer> conexao = new ArrayList<Integer>();

        conexao.add(index);

        while(parar == false) {
            for(int i = 0; i < numeroVertices; i++) {
                if((grafo[index][i] == 1) && (!conexao.contains(i))) {
                    fila.add(i);
                    conexao.add(i);
                }
            }

            if(fila.isEmpty()) {
                parar = true;
            } else {
                index = fila.get(0);
                fila.remove(0);
            }
        }

        for(int i = 0; i < numeroVertices; i++) {
            if(!conexao.contains(i)) {
                return "O grafo não é conexo!" + i;
            }
        }

        return "O grafo é conexo!";
    }

	public int[] maxGrau() {
		int[] maxGrau_index = {0, 0};
		int grau = 0;
		for(int i = 0; i < numeroVertices; i++) {
			for(int j = 0; j < numeroVertices; j++) {
				if(grafo[i][j] == 1) {
					grau++;
				}
			}
			if(maxGrau_index[0] < grau) {
				maxGrau_index[0] = grau;
				maxGrau_index[1] = i + 1;
			}
			grau = 0;
		}

		return maxGrau_index;
	}
	
	public Grafo_Matriz(int numeroVertices) {
		this.numeroVertices = numeroVertices;
		grafo = new byte[numeroVertices][numeroVertices];
		
		for(int i = 0; i < numeroVertices; i++) {
			for(int j = 0; j < numeroVertices; j++) {
				grafo[i][j] = 0;
			}
		}
		
		numeroArestas = 0;
	}
	
	public void adicionarAresta(int vertice_1, int vertice_2) {
		vertice_1 -= 1;
		vertice_2 -= 1;
		grafo[vertice_1][vertice_2] = 1;
		grafo[vertice_2][vertice_1] = 1;
	}

	public String isEuleriano() {
		for(int i = 0; i < numeroVertices; i++) {
			byte grau = 0;
			for(int j = 0; j < numeroVertices; j++) {
				if(grafo[i][j] == 1) {
					grau++;
				}
			}
			if(grau%2 != 0) {
				return "O grafo não é Euleriano!";
			}
		}
		return "O grafo é Euleriano!";
	}
	
	public String hasPercursoAbertoEuler() {
		byte numeroVerticesGrauImpar = 0;
		
		for(int i = 0; i < numeroVertices; i++) {
			byte grau = 0;
			for(int j = 0; j < numeroVertices; j++) {
				if(grafo[i][j] == 1) {
					grau++;
				}
			}
			if(grau%2 != 0) {
				numeroVerticesGrauImpar++;
			}
		}
		
		if(numeroVerticesGrauImpar == 2) {
			return "O grafo tem um percurso aberto de Euler!";
		} else {
			return "O grafo não tem um percurso aberto de Euler!";
		}
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

	public byte[][] getGrafo() {
		return grafo;
	}

	public void setGrafo(byte[][] grafo) {
		this.grafo = grafo;
	}

	@Override
	public String toString() {
		String msg = "Grafo:\n  ";
		for(int i = 0; i < numeroVertices; i++) {
			if(i == numeroVertices - 1) {
				msg += (i + 1) + "\n";
			} else {
				msg += (i + 1) + "  ";
			}
			
		}
		for(int i = 0; i < numeroVertices; i++) {
			msg += (i + 1) + "[";
			for(int j = 0; j < numeroVertices; j++) {
				if(j == numeroVertices - 1) {
					msg += grafo[i][j] + "]\n";
				} else {
					msg += grafo[i][j] + ", ";
				}
			}
		}
		
		return msg;
	}

}
