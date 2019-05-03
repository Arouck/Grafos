package br.ufpa.grafos.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Grafo_Lista {
	private Integer contador;
    private Integer tempo;
	private List<Vertice> vertices;
	private Integer numeroVertices;
    private Integer numeroArestas;
    
    public Grafo_Lista(Integer numeroVertices) {

		this.numeroVertices = numeroVertices;
		vertices = new ArrayList<Vertice>();
		
		for (Integer i = 0; i < numeroVertices; i++) {
			Vertice vertice = new Vertice(i + 1);
			vertices.add(vertice);
		}

		this.numeroArestas = 0;
	}
    
    public void ComponentesConectados() {
    	for(Integer s = 0; s < numeroVertices; s++) {
    		Vertice vertice = vertices.get(s);
    		if(!vertice.getMarcado()) {
    			DFS(vertice.getNumero());
    			contador++;
    		}
    	}
    }
    
    public void DFS(Integer numeroFonte) {
    	Vertice fonte = vertices.get(numeroFonte - 1);
    	
    	fonte.setMarcado(true);
    	fonte.setId(contador);
    	
    	for(Vertice vertice : fonte.getAdjacentes()) {
    		if(!vertice.getMarcado()) {
    			DFS(vertice.getNumero());
    		}
    	}
    }
    
    public void BFS(Integer numeroFonte) {
    	boolean visitado[] = new boolean[numeroVertices];
    	
    	Vertice fonte = vertices.get(numeroFonte - 1);
    	
    	LinkedList<Vertice> fila = new LinkedList<Vertice>();
    	
    	visitado[fonte.getNumero() - 1] = true;
    	fila.add(fonte);
    	
    	while(!fila.isEmpty()) {
    		fonte = fila.poll();
    		
    		System.out.print(fonte.getNumero() + " ");
    		
    		Iterator<Vertice> filaADJ = fonte.getAdjacentes().listIterator();
    		
    		while(filaADJ.hasNext()) {
    			Vertice vertice = filaADJ.next();
    			
    			if(!visitado[vertice.getNumero() - 1]) {
    				visitado[vertice.getNumero() - 1] = true;
    				
    				fila.add(vertice);
    			}
    		}
    	}
    }
    
    public String isConexo() {
        Integer index = 0;
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

	public Integer[] maxGrau() {
		Integer[] maxGrau_index = {0, 0};
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
	
	public String DFS_Visit(Vertice vertice, String msg) {
		vertice.setCor("Cinza");
		tempo += 1;
        vertice.setD(tempo);
		msg += vertice.getNumero() + " ";
		
		Iterator<Vertice> verticeAdj = vertice.getAdjacentes().listIterator();
		while(verticeAdj.hasNext()) {
			Vertice verticeAux = verticeAdj.next();
			if(vertices.get(verticeAux.getNumero() - 1).getCor().equals("Branco")) {
				verticeAux.setAnterior(vertice);
				msg = DFS_Visit(verticeAux, msg);
			}
		}
		vertice.setCor("Preto");
		tempo += 1;
        vertice.setF(tempo);
		return msg;
	}
	
	public void DFS() {
		String msg = "";
		if(this.isConexo().equals("O grafo é conexo!")) {
			for(Integer i = 0; i < numeroVertices; i++) {
				vertices.get(i).setCor("Branco");
				vertices.get(i).setAnterior(null);
			}
			tempo = 0;
			System.out.println(DFS_Visit(vertices.get(0), msg));
		} else {
			System.out.println("Não é possível executar o DFS para esse grafo, pois ele não é conexo!");
		}
	}
	
	public String hasPercursoAbertoEuler() {
		Integer vertices_grau_impar = 0;
		
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

	public void AdicionarAresta(Integer vertice_1, Integer vertice_2) {
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
		Integer i;
		
		for(i = 0; i < vertices.size(); i++) {
			if (i == vertices.size() - 1) {
				msg += vertices.get(i).getNumero() + "], [Arestas = ";
			} else {
				msg += vertices.get(i).getNumero() + ", ";
			}
		}
		
		i = 0;
		Integer temp = 0;
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

	public List<Vertice> getVertices() {
		return vertices;
	}

	public void setVertices(List<Vertice> vertices) {
		this.vertices = vertices;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}
}