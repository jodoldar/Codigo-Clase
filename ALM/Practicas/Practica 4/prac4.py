import numpy as np

def create_graph(N,maxvalue=1000):
    G = np.random.randint(maxvalue, size=(N, N))
    for i in range(N):
        G[i][i] = 0
    return G

def process_graph(G):
    # EJERCICIO 2
    N = G.shape[0]

    for i in range(N):
        for j in range(i):
            ida = G[i,j]
            vuelta = G[j,i]

            minVal = min(ida,vuelta)

            G[i,j] -= minVal
            G[j,i] -= minVal
    return G

def generate_random_ordering(G):
    # let's assume that G is a square Numpy matrix of integers
    N = G.shape[0]
    return np.random.permutation(N)

def generate_greedy_ordering(G):
    # let's assume that G is a square Numpy matrix of integers
    N = G.shape[0]
    #N = len(G[0])
    #print(N)
    # EJERCICIO 1
    # REEMPLAZAR:
    fijados = []
    quedan = list(range(0,N))
    #print("Inicio: Fijados-> ",fijados, " Quedan-> ", quedan);
    listaLocal = []

    for i in range(N):
        if i in quedan:
            fila = sum(G[i,j] for j in range(N) if j not in fijados)
            columna = sum(G[j,i] for j in range(N) if j not in fijados)
            listaLocal.append((fila-columna,i))
    #print("Fijados: ", fijados, "\tEligo el maximo entre", listaLocal)
    maxVal = max(listaLocal)
    fijados.append(maxVal[1])
    quedan.remove(maxVal[1])
    listaLocal = []


    while len(quedan)>0:
        j = fijados[-1]
        #print("Pivote: ", j)
        for i in quedan:
            fila1 = G[i,j]
            columna1 = G[j,i]
            listaLocal.append((columna1-fila1,i))
        #fila = sum(G[i,j] for i in range(N) if i in quedan)
        #columna = sum(G[j,i] for i in range(N) if i in quedan)
        #listaLocal.append((fila-columna,i))
        #print("Fijados: ", fijados, "\tEligo el maximo entre", listaLocal)
        maxVal = max(listaLocal)
        fijados.append(maxVal[1])
        quedan.remove(maxVal[1])
        listaLocal = []
        #print("Fijados ", fijados, " Quedan ", quedan)
    return fijados

def evaluate(G,ordering):
    # assume that G.shape is of type (N,N) and ordering.shape is of type
    # (N) and is a permutation of values 0,...,N-1
    N = G.shape[0]
    return sum(G[ordering[i]][ordering[j]]-G[ordering[j]][ordering[i]] for i in range(N) for j in range(i+1,N))

def show_evaluate(G,ordering):
    N = G.shape[0]
    positivos = list(G[ordering[i]][ordering[j]] for i in range(N) for j in range(i+1,N))
    negativos = list(G[ordering[j]][ordering[i]] for i in range(N) for j in range(i+1,N))
    vpos = sum(positivos)
    vneg = sum(negativos)
    resul = vpos-vneg
    print("(" + ",".join(map(str,positivos))+") - (" + ",".join(map(str,negativos))+
          ") = ",vpos, "-", vneg, "=", resul)
    return resul


# si pruebas con este grafo:
G= np.asarray([[0, 8, 3, 2, 9],
               [3, 0, 3, 8, 2],
               [0, 2, 0, 6, 2],
               [4, 4, 8, 0, 0],
               [7, 7, 6, 2, 0]],dtype=np.int)

G2 = np.asarray([[0, 1, 0, 1],
                 [0, 0, 2, 2],
                 [1, 0, 0, 0],
                 [0, 0, 1, 0]],dtype=np.int)

# el algoritmo voraz hace estos pasos:
# [] [(8, 0), (-5, 1), (-10, 2), (-2, 3), (9, 4)]
# [4] [(4, 0), (5, 1), (-2, 2), (2, 3)]
# [4, 1] [(-6, 0), (0, 2), (10, 3)]
# [4, 1, 3] [(-2, 0), (4, 2)]
# [4, 1, 3, 2] [(-8, 0)]
# y termina dando como resultado:
# [4, 1, 3, 2, 0]
# con valor 10

# este trozo prueba ejemplos aleatorios:
N = 30
#G = create_graph(N,10)
G = process_graph(G)
print("G=",G)
random_ordering = generate_random_ordering(G)
print("Random ordering = ", random_ordering)
greedy_ordering = generate_greedy_ordering(G)
print("Greedy ordering = ", greedy_ordering)
print("random",evaluate(G,random_ordering))
print("greedy",evaluate(G,greedy_ordering))

