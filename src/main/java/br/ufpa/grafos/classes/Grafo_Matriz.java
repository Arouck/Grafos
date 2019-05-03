package br.ufpa.grafos.classes;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Grafo_Matriz {

	private Integer numeroVertices;
	private Integer numeroArestas;
	private Byte[][] grafo;
	private String[] cor;
	private Integer[] d;
	private Integer tempo;
	private Integer[] f;
	private Integer[] anterior;
	private Integer[] id;
	private boolean[] marcado;
	private Integer contador;
	
	public void ComponentesConectados() {
		marcado = new boolean[numeroVertices];
		id = new Integer[numeroVertices];
		
		for(Integer s = 0; s < numeroVertices; s++) {
			if(!marcado[s]) {
				DFS(s + 1);
				contador++;
			}
		}
	}
	
	public void DFS(Integer fonte) {
		marcado[fonte - 1] = true;
		id[fonte - 1] = contador;
		
		for(Integer i = 0; i < numeroVertices; i++) {
			if(grafo[fonte - 1][i] == 1) {
				if(!marcado[i]) {
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
    	
    	while(!fila.isEmpty()) {
    		fonte = fila.poll();
    		
    		System.out.print(fonte + " ");
    		
    		for(Integer i = 0; i < numeroVertices; i++) {
    			if(grafo[fonte - 1][i] == 1) {
    				adjacentes.add(i + 1);
    			}
    		}
    		
    		Iterator<Integer> filaADJ = adjacentes.listIterator();
    		
    		while(filaADJ.hasNext()) {
    			Integer vertice = filaADJ.next();
    			
    			if(!visitado[vertice - 1]) {
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

		for(Integer j = 0; j < numeroVertices; j++) {
			if(grafo[i][j] == 1) {
				adj.add(j);
			}
		}
		
		if(!adj.isEmpty()) {
			for(Integer j = 0; j < adj.size(); j++) {
				Integer vertice = adj.get(j);
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
			for(Integer i = 0; i < numeroVertices; i++) {
				cor[i] = "Branco";
				anterior[i] = null;
			}

			tempo = 0;
			System.out.println(DFS_Visit(0, msg));
		} else {
			System.out.println("Não é possÃ­vel executar o DFS para esse grafo, pois ele não é conexo!");
		}
	}
	
	public void adicionarVertice() {
		numeroVertices += 1;
		Byte[][] novoGrafo = new Byte[numeroVertices][numeroVertices];
		
		for(Integer i = 0; i < numeroVertices; i++) {
			for(Integer j = 0; j < numeroVertices; j++) {
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
		d = new Integer[numeroVertices];
		f = new Integer[numeroVertices];
		anterior = new Integer[numeroVertices];
		
		setGrafo(novoGrafo);
    }
    
    public String isConexo() {
        Integer index = 0;
        boolean parar = false;
        List<Integer> fila = new ArrayList<Integer>();
        List<Integer> conexao = new ArrayList<Integer>();

        conexao.add(index);

        while(parar == false) {
            for(Integer i = 0; i < numeroVertices; i++) {
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

        for(Integer i = 0; i < numeroVertices; i++) {
            if(!conexao.contains(i)) {
                return "O grafo não é conexo!" + i;
            }
        }

        return "O grafo é conexo!";
    }

	public Integer[] maxGrau() {
		Integer[] maxGrau_index = {0, 0};
		Integer grau = 0;
		for(Integer i = 0; i < numeroVertices; i++) {
			for(Integer j = 0; j < numeroVertices; j++) {
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
	
	public Grafo_Matriz(Integer numeroVertices) {
		this.numeroVertices = numeroVertices;
		grafo = new Byte[numeroVertices][numeroVertices];
		cor = new String[numeroVertices];
		d = new Integer[numeroVertices];
		f = new Integer[numeroVertices];
		anterior = new Integer[numeroVertices];
		this.tempo = 0;
		contador = 0;
		
		for(Integer i = 0; i < numeroVertices; i++) {
			for(Integer j = 0; j < numeroVertices; j++) {
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
		for(Integer i = 0; i < numeroVertices; i++) {
			Byte grau = 0;
			for(Integer j = 0; j < numeroVertices; j++) {
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
		Byte numeroVerticesGrauImpar = 0;
		
		for(Integer i = 0; i < numeroVertices; i++) {
			Byte grau = 0;
			for(Integer j = 0; j < numeroVertices; j++) {
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

	@Override
	public String toString() {
		String msg = "Grafo:\n  ";
		for(Integer i = 0; i < numeroVertices; i++) {
			if(i == numeroVertices - 1) {
				msg += (i + 1) + "\n";
			} else {
				msg += (i + 1) + "  ";
			}
			
		}
		for(Integer i = 0; i < numeroVertices; i++) {
			msg += (i + 1) + "[";
			for(Integer j = 0; j < numeroVertices; j++) {
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
