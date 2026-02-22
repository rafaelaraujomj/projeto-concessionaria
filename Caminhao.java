public class Caminhao extends Veiculo {
    private double capacidadeCarga;
    private int numeroEixos;

    public Caminhao(String marca, String modelo, int ano, String cor, double preco, double capacidadeCarga, int numeroEixos) {
        super(marca, modelo, ano, cor, preco); 
        this.capacidadeCarga = capacidadeCarga;
        this.numeroEixos = numeroEixos;
    }

    // Getters
    public double getCapacidadeCarga() {
        return capacidadeCarga;
    }
    public int getNumeroEixos() {
        return numeroEixos;
    }

    // Setters
    public void setCapacidadeCarga(double capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }
    public void setNumeroEixos(int numeroEixos) {
        this.numeroEixos = numeroEixos;
    }

    
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Capacidade de Carga: " + capacidadeCarga + " toneladas");
        System.out.println("Número de Eixos: " + numeroEixos);
    }
    
}