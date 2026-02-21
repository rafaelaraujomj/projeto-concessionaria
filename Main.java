import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    // 1. REFATORAÇÃO: Unificamos as listas usando Polimorfismo!
    private static List<Veiculo> veiculos = new ArrayList<>();
    private static List<Pessoa> clientes = new ArrayList<>();
    private static List<Venda> vendas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int escolha = 0;
        
        do {
            try {
                System.out.println("\n- - - Menu Concessionária - - -");
                System.out.println("1 - Mostrar Veículos em Estoque");
                System.out.println("2 - Comprar Veículo (Entrada no Estoque)");
                System.out.println("3 - Vender Veículo (Saída do Estoque)");
                System.out.println("4 - Cadastrar um cliente");
                System.out.println("5 - Gerar um relatório de Vendas (Lucro/Perda)");
                System.out.println("6 - Exibir clientes cadastrados"); 
                System.out.println("7 - Sugerir veículo por biotipo do cliente"); 
                System.out.println("0 - Sair");
                System.out.print("\nEscolha uma opção: ");

                escolha = scanner.nextInt();
                scanner.nextLine();

                switch (escolha) {
                    case 1: mostrarVeiculos(); break;
                    case 2: comprarVeiculo(scanner); break;
                    case 3: venderVeiculo(scanner); break;
                    case 4: cadastrarCliente(scanner); break;
                    case 5: gerarRelatorio(); break;
                    case 6: mostrarClientes(); break;
                    case 7: sugerirVeiculoPorBiotipo(scanner); break;
                    case 0:
                        System.out.println("Até a próxima!");
                        break;
                    default:
                        System.out.println("Opção inválida. Escolha outra opção.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida! Digite números onde for solicitado.");
                scanner.nextLine(); // Limpa o buffer do scanner para não entrar em loop
            } catch (Exception e) {
                System.out.println("Erro desconhecido: " + e.getMessage());
            }
        } while (escolha != 0);
        
        scanner.close();
    }

    // REFATORAÇÃO: Método unificado usando a herança da classe Veiculo
    private static void mostrarVeiculos() {
        if (!veiculos.isEmpty()) {
            System.out.println("\n- - - Estoque de Veículos - - -");
            int x = 1;
            for (Veiculo v : veiculos) {
                // Aqui podemos chamar v.exibirInformacoes() que vem da Interface, 
                // ou fazer uma listagem limpa para o menu:
                System.out.println(x + " - " + v.getMarca() + " " + v.getModelo() + " | Ano: " + v.getAno() + " | Tipo: " + v.getClass().getSimpleName());
                x++;
            }
        } else {
            System.out.println("Nenhum veículo no estoque no momento!");
        }
    }

    private static void comprarVeiculo(Scanner scanner) {
        System.out.println("\n--- Compra de Veículo (Entrada) ---");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        System.out.println("3 - Caminhão"); // Novo veículo implementado
        System.out.println("0 - Voltar");
        System.out.print("Escolha o tipo: ");
        
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha == 0) return;
        if (escolha < 1 || escolha > 3) throw new InputMismatchException();

        System.out.print("Marca: "); String marca = scanner.nextLine();
        System.out.print("Modelo: "); String modelo = scanner.nextLine();
        System.out.print("Ano: "); int ano = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Cor: "); String cor = scanner.nextLine();
        System.out.print("Preço de custo: R$ "); double preco = scanner.nextDouble();
        scanner.nextLine();

        switch (escolha) {
            case 1:
                System.out.print("Número de portas: "); int nPortas = scanner.nextInt(); scanner.nextLine();
                System.out.print("Combustível: "); String tipoCombustivel = scanner.nextLine();
                System.out.print("Porta malas (litros): "); int portaMalas = scanner.nextInt(); scanner.nextLine();
                veiculos.add(new Carro(marca, modelo, ano, cor, preco, nPortas, tipoCombustivel, portaMalas));
                System.out.println("Carro cadastrado com sucesso!");
                break;
            case 2:
                System.out.print("Cilindradas: "); int cilindradas = scanner.nextInt(); scanner.nextLine();
                System.out.print("Partida elétrica (S/N): "); String partida = scanner.nextLine();
                boolean partidaEletrica = partida.equalsIgnoreCase("S");
                System.out.print("Categoria: "); String categoria = scanner.nextLine();
                veiculos.add(new Moto(marca, modelo, ano, cor, preco, categoria, cilindradas, partidaEletrica));
                System.out.println("Moto cadastrada com sucesso!");
                break;
            case 3:
                System.out.print("Capacidade de Carga (toneladas): "); double carga = scanner.nextDouble(); scanner.nextLine();
                System.out.print("Número de Eixos: "); int eixos = scanner.nextInt(); scanner.nextLine();
                veiculos.add(new Caminhao(marca, modelo, ano, cor, preco, carga, eixos));
                System.out.println("Caminhão cadastrado com sucesso!");
                break;
        }
    }

    private static void venderVeiculo(Scanner scanner) {
        // Uso da nossa Exception Personalizada
        if (veiculos.isEmpty()) {
            throw new EstoqueVazioException("Erro: Não há veículos no estoque para vender!");
        }
        if (clientes.isEmpty()) {
            System.out.println("Erro: Não há clientes cadastrados para realizar a venda!");
            return;
        }

        mostrarClientes();
        System.out.print("Digite o número do cliente comprador: ");
        Pessoa comprador = clientes.get(scanner.nextInt() - 1);
        scanner.nextLine();

        mostrarVeiculos();
        System.out.print("Digite o número do veículo para venda: ");
        Veiculo veiculoParaVenda = veiculos.get(scanner.nextInt() - 1);
        scanner.nextLine();

        System.out.print("Informe o valor final fechado na venda: R$");
        double valorVenda = scanner.nextDouble();
        scanner.nextLine();

        // Isso pode estourar a VendaInvalidaException se o valor for zero/negativo
        Venda novaVenda = new Venda(veiculoParaVenda, comprador, valorVenda, LocalDateTime.now());
        
        vendas.add(novaVenda);
        veiculos.remove(veiculoParaVenda); // Retira do estoque unificado
        System.out.println("Venda executada com sucesso!");
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("Idade: "); int idade = scanner.nextInt(); scanner.nextLine();
        System.out.print("Endereço: "); String endereco = scanner.nextLine();
        System.out.print("Telefone: "); String telefone = scanner.nextLine();
        System.out.print("E-mail: "); String email = scanner.nextLine();
        System.out.print("Altura (ex: 1.75): "); double altura = scanner.nextDouble(); scanner.nextLine();
        System.out.print("Peso (kg): "); double peso = scanner.nextDouble(); scanner.nextLine();

        clientes.add(new Pessoa(nome, idade, endereco, telefone, email, altura, peso));
        System.out.println("Cliente " + nome + " cadastrado com sucesso!");
    }

    private static void mostrarClientes() {
        if (!clientes.isEmpty()) {
            System.out.println("\n- - - Lista de Clientes - - -");
            int x = 1;
            for (Pessoa cliente : clientes) {
                System.out.println(x + " - " + cliente.getNome() + " | Tel: " + cliente.getTelefone());
                x++;
            }
        } else {
            System.out.println("Nenhum cliente foi encontrado!");
        }
    }

    // NOVO: Sugestão por biotipo usando os dados da Pessoa
    private static void sugerirVeiculoPorBiotipo(Scanner scanner) {
        if (clientes.isEmpty()) {
            System.out.println("Cadastre um cliente primeiro para sugerir um veículo!");
            return;
        }
        
        mostrarClientes();
        System.out.print("Escolha o número do cliente: ");
        Pessoa cliente = clientes.get(scanner.nextInt() - 1);
        scanner.nextLine();

        System.out.println("\n--- Análise de Biotipo ---");
        System.out.println("Cliente: " + cliente.getNome() + " | Altura: " + cliente.getAltura() + "m | Peso: " + cliente.getPeso() + "kg");
        
        // Lógica simples de biotipo
        if (cliente.getAltura() > 1.85 || cliente.getPeso() > 100) {
            System.out.println("Sugestão: Veículos com amplo espaço interno (SUVs, Caminhonetes ou Sedans Grandes).");
        } else if (cliente.getAltura() < 1.60) {
            System.out.println("Sugestão: Veículos compactos com boa visibilidade (Hatches compactos ou Scooters automáticas).");
        } else {
            System.out.println("Sugestão: Biotipo padrão. A maioria dos Sedans médios, Hatches e Motos atenderão perfeitamente.");
        }
    }

    private static void gerarRelatorio() {
        if (vendas.isEmpty()) {
            System.out.println("Não existem vendas cadastradas para gerar relatório!");
        } else {
            System.out.println("\n- - - Relatório Geral de Vendas - - -");
            for (Venda venda : vendas) {
                // O método da classe Venda agora cuida de exibir tudo, incluindo Lucro/Perda
                venda.exibirDetalhesVenda();
                System.out.println("----------------------------------------");
            }
        }
    }
}