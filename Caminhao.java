public class Caminhao extends Veiculo {
    private double capacidadeCarga; // em toneladas
    private int numeroEixos;

    public Caminhao(String marca, String modelo, int ano, String cor, double preco, double capacidadeCarga, int numeroEixos) {
        // Chamando o construtor da classe pai (Veiculo)
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

    // Sobrescrevendo o método para exibir os dados específicos do caminhão
    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Capacidade de Carga: " + capacidadeCarga + " toneladas");
        System.out.println("Número de Eixos: " + numeroEixos);
    }
    
    // Método específico (opcional, só para dar um charme extra na classe)
    public void descarregar() {
        System.out.println("O caminhão " + getModelo() + " está descarregando " + capacidadeCarga + " toneladas.");
    }
}