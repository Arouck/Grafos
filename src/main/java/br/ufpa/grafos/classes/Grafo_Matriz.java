package br.ufpa.grafos.classes;

import java.util.List;
import java.util.ArrayList;

public class Grafo_Matriz {

	private int numeroVertices;
	private int numeroArestas;
	private byte[][] grafo;
	private String[] cor;
	private int[] d;
	private int tempo;
	private int[] f;
	private Integer[] anterior;

	public String DFS_Visit(int i, String msg) {
		cor[i] = "Cinza";
		tempo += 1;
		d[i] = tempo;
		msg += (i + 1) + " ";

		List<Integer> adj = new ArrayList<>();

		for(int j = 0; j < numeroVertices; j++) {
			if(grafo[i][j] == 1) {
				adj.add(j);
			}
		}
		
		if(!adj.isEmpty()) {
			for(int j = 0; j < adj.size(); j++) {
				int vertice = adj.get(j);
				if(cor[vertice].equals("Branco")) {
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
		if(this.isConexo().equals("O grafo é conexo!")) {
			for(int i = 0; i < numeroVertices; i++) {
				cor[i] = "Branco";
				anterior[i] = null;
			}

			tempo = 0;
			System.out.println(DFS_Visit(0, msg));
		} else {
			System.out.println("Não é possível executar o DFS para esse grafo, pois ele não é conexo!");
		}
	}
	
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

		cor = new String[numeroVertices];
		d = new int[numeroVertices];
		f = new int[numeroVertices];
		anterior = new Integer[numeroVertices];
		
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
		cor = new String[numeroVertices];
		d = new int[numeroVertices];
		f = new int[numeroVertices];
		anterior = new Integer[numeroVertices];
		this.tempo = 0;
		
		for(int i = 0; i < numeroVertices; i++) {
			for(int j = 0; j < numeroVertices; j++) {
				grafo[i][j] = 0;
			}
		}
		
		numeroArestas = 0;
	}
	
	public void adicionarAresta(Integer vertice_1, Integer vertice_2) {
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

	public int[] getD() {
		return d;
	}

	public void setD(int[] d) {
		this.d = d;
	}

	public int[] getF() {
		return f;
	}

	public void setF(int[] f) {
		this.f = f;
	}

	public int getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
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
