import java.time.LocalDateTime;

public class Venda {
    private Veiculo veiculo;
    private Pessoa comprador;
    private double valor;
    private LocalDateTime dataVenda;

    // Construtor validando o valor com a exception personalizada
    public Venda(Veiculo veiculo, Pessoa comprador, double valor, LocalDateTime dataVenda) {
        if (valor <= 0) {
            throw new VendaInvalidaException("Erro: O valor da venda deve ser maior que zero!");
        }
        
        this.veiculo = veiculo;
        this.comprador = comprador;
        this.valor = valor;
        this.dataVenda = dataVenda;
    }

    // Getters
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public Pessoa getComprador() {
        return comprador;
    }
    public double getValor() {
        return valor;
    }
    public LocalDateTime getDataVenda() {
        return dataVenda;
    }

    // Setters
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public void setComprador(Pessoa comprador) {
        this.comprador = comprador;
    }
    
    // Setter validado
    public void setValor(double valor) {
        if (valor <= 0) {
            throw new VendaInvalidaException("Erro: O valor da venda deve ser maior que zero!");
        }
        this.valor = valor;
    }
    public void setDataVenda(LocalDateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    // Método de negócio

    public double calcularLucroOuPerda() {
        return this.valor - this.veiculo.getPreco(); 
    }

    public void exibirDetalhesVenda() {
        System.out.println("Veículo: " + veiculo.getMarca() + " " + veiculo.getModelo() + " - Ano: " + veiculo.getAno());
        System.out.println("Comprador: " + comprador.getNome() + " - Contato: " + comprador.getTelefone());
        System.out.println("Data da venda: " + dataVenda);
        System.out.println("Valor original (custo): R$" + veiculo.getPreco());
        System.out.println("Valor fechado na venda: R$" + valor);
        
        double diferenca = calcularLucroOuPerda();
        if (diferenca > 0) {
            System.out.println("Resultado: Lucro de R$" + diferenca);
        } else if (diferenca < 0) {
            System.out.println("Resultado: Perda/Prejuízo de R$" + Math.abs(diferenca));
        } else {
            System.out.println("Resultado: Venda a preço de custo (Sem lucro ou perda).");
        }
    }
}