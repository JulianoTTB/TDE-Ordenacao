public class AlgoritmosOrdenacao {
    private static int qtdTrocas = 0;
    private static int qtdInteracoes = 0;
    private static int qtdBaldes = 20;
    private static No ultimo;

    public static int getQtdTrocas() {
        return qtdTrocas;
    }

    public static int getQtdInteracoes() {
        return qtdInteracoes;
    }

    public static int[] bucketSort(int[] vetor, int tamanhoDoVetor, int minimo, int maximo){
        qtdTrocas = 0; qtdInteracoes = 0;
        No lista = null;
        for(int valor : vetor)
            lista = lista.inserir(lista, valor);

        lista = algoritmoBucket(lista, minimo, maximo);
        int indice = 0;
        for(No i = lista; i != null; i = i.getProximo(), indice++)
            vetor[indice] = i.getValor();

        return vetor;
    }

    private static No algoritmoBucket(No lista, int minimo, int maximo){
        if(lista == null) return null;

        if(minimo == maximo){
            for(ultimo = lista; lista.getProximo() != null; ultimo = ultimo.getProximo());
            return lista;
        }

        int divisao = (maximo - minimo) / qtdBaldes;
        if (divisao == 0) divisao = 1;

        No[] cabeca = new No[qtdBaldes];
        int[]minimoCadaBucket = new int[qtdBaldes];
        int[]maximoCadaBucket = new int[qtdBaldes];

        for (int i = 0; i < qtdBaldes; i++) cabeca[i] = null;

        while(lista != null){
            qtdInteracoes++;
            int i = (lista.getValor() - minimo) / divisao;
            if (i < 0)i = 0;
            else if (i >= qtdBaldes) i = qtdBaldes - 1;

            No temporario = lista;
            lista = lista.getProximo();

            temporario.setProximo(cabeca[i]);
            cabeca[i] = temporario;
            qtdTrocas++;

            if(cabeca[i].getProximo() == null)
                minimoCadaBucket[i] = maximoCadaBucket[i] = temporario.getValor();

            if(temporario.getValor() > maximoCadaBucket[i]) maximoCadaBucket[i] = temporario.getValor();
            if(temporario.getValor() < minimoCadaBucket[i]) minimoCadaBucket[i] = temporario.getValor();

        }

        No auxiliar = new No(0);
        No temporario = auxiliar;

        for(int i = 0; i < qtdBaldes; i++){
            if(cabeca[i] !=null){
                qtdInteracoes++;
                temporario.setProximo(algoritmoBucket(cabeca[i], minimoCadaBucket[i], maximoCadaBucket[i]));
                temporario = ultimo;
            }
        }
        return  auxiliar.getProximo();
    }

    public static int[] gnomeSort(int[] vetor, int tamanhoDoVetor){
        qtdTrocas = 0; qtdInteracoes = 0;
        int i = 0, temp;
        while(i < tamanhoDoVetor){
            qtdInteracoes++;
            if (i == 0 || vetor[i -1] <= vetor[i]) i++;
            else{
                temp = vetor[i];
                vetor[i] = vetor[i - 1];
                vetor[i - 1] = temp;
                qtdTrocas++;
            }
        }
        return vetor;
    }


    public static int[] combSort(int[] vetor, int tamanhoDoVetor){
        qtdTrocas = 0; qtdInteracoes = 0; boolean ordenado = false; int espaco = 0;

        while(!ordenado){
            espaco = (espaco* 10) / 13;

            if (espaco <= 1){
                espaco = 1;
                ordenado = true;
            }

            for(int i = 0; i < tamanhoDoVetor - espaco; i++){
                qtdInteracoes++;
                int indice = i + espaco;
                if(vetor[i] > vetor[indice]){
                    int auxiliar = vetor[indice];
                    vetor[indice] = vetor[i];
                    vetor[i] = auxiliar;
                    ordenado = false;
                    qtdTrocas++;
                }
            }
        }
        return vetor;
    }
    public static int[] bubbleSortComFlag(int[] vetor, int tamanhoDoVetor){
        qtdTrocas = 0; qtdInteracoes = 0;
        int temp; boolean troca;
        for(int i = 0; i < tamanhoDoVetor; i++){
            troca = false;
            for (int j = 0; j < tamanhoDoVetor - i - 1; j++){
                if (vetor[j] > vetor[j + 1]){
                    temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    qtdTrocas++;
                    troca = true;
                }
                qtdInteracoes++;
            }
            if (!troca)break;
        }
        return vetor;
    }
    public static int[] selectionSort(int[] vetor, int tamanhoDoVetor){
        qtdTrocas = 0; qtdInteracoes = 0;
        int indiceMinimo, temp;

        for(int i = 0; i < tamanhoDoVetor -1; i++){
            indiceMinimo = i;
            for(int j = i +1; j < tamanhoDoVetor; j++){
                qtdInteracoes++;
                if(vetor[j] < vetor[indiceMinimo])
                    indiceMinimo = j;
            }

            if (indiceMinimo != i){
                temp = vetor[i];
                vetor[i] = vetor[indiceMinimo];
                vetor[indiceMinimo] = temp;
                qtdTrocas++;
            }
        }

        return vetor;
    }
    public static int[] cocktailSort(int[] vetor, int tamanhoDoVetor){
        qtdTrocas = 0; qtdInteracoes = 0;
        boolean troca = true;
        int inicio = 0, fim = tamanhoDoVetor - 1, temp;

        while(troca){
            troca = false;
            for(int i = inicio; i < fim; i++){
                qtdInteracoes++;
                if (vetor[i] > vetor[i + 1]){
                    temp = vetor[i];
                    vetor[i] = vetor[i + 1];
                    vetor[i + 1] = temp;
                    troca = true;
                    qtdTrocas++;
                }
            }
            if(!troca)break;

            troca = false;
            fim--;


            for(int i = fim; i > inicio; i--){
                qtdInteracoes++;
                if (vetor[i] < vetor[i - 1]){
                    temp = vetor[i];
                    vetor[i] = vetor[i - 1];
                    vetor[i - 1] = temp;
                    troca = true;
                    qtdTrocas++;
                }
            }
            inicio++;
        }

        return vetor;
    }
}
