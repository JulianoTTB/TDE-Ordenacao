public class No {
    private int valor;
    private No proximo;

    public No(int valor){
        this.valor = valor;
        this.proximo = null;
    }

    public static No inserir(No anterior, int valor){
        No novo_no = new No(valor);
        novo_no.setProximo(anterior);
        return novo_no;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }
}
