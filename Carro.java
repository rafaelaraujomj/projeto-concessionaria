public class Carro extends Veiculo{
    private int nPortas;
    private String tipoCombustivel;
    private int capacidadePortaMalas;

    public Carro(String marca, String modelo, int ano, String cor, double preco, 
        int nPortas, String tipoCombustivel, int capacidadePortaMalas) {

        super(marca, modelo, ano, cor, preco);
        this.nPortas = nPortas;
        this.tipoCombustivel = tipoCombustivel;
        this.capacidadePortaMalas = capacidadePortaMalas;
    }
    

    public void abrirPortaMalas(){
        System.out.println("Abrindo porta malas...");
    }
    public void fecharPortaMalas(){
        System.out.println("Fechando porta malas...");
    }

    // Getters
    public int getnPortas() {
        return nPortas;
    }
    public String getTipoCombustivel() {
        return tipoCombustivel;
    }
    public int getCapacidadePortaMalas() {
        return capacidadePortaMalas;
    }

    // Setters
    public void setnPortas(int nPortas) {
        this.nPortas = nPortas;
    }
    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }
    public void setCapacidadePortaMalas(int capacidadePortaMalas) {
        this.capacidadePortaMalas = capacidadePortaMalas;
    }


    @Override
    public void exibirInformacoes() {
        super.exibirInformacoes();
        System.out.println("Número de portas: "+nPortas);
        System.out.println("Tipo de combustível: "+tipoCombustivel);
        System.out.println("Capacidade do porta malas: "+capacidadePortaMalas+" litros");
    }
    

    
        
}
