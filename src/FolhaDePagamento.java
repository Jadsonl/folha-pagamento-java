import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;
import java.text.NumberFormat;

abstract class Funcionarios {
    String nome;
    int matricula;
    static final double SALARIO_BASE = 2000.0;

    public Funcionarios(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public abstract double calculoSalario();
    public abstract void exibirDados();

    protected String formatarMoeda(double valor) {
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valor);
    }
}

class FuncionariosPadrao extends Funcionarios {
    public FuncionariosPadrao(String nome, int matricula) {
        super(nome, matricula);
    }

    @Override
    public double calculoSalario() {
        return SALARIO_BASE;
    }

    @Override
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário Fixo: " + formatarMoeda(SALARIO_BASE));
        System.out.println("Extras: " + formatarMoeda(0));
        System.out.println("Salário Final: " + formatarMoeda(calculoSalario()));
        System.out.println("-------------------------");
    }
}

class FuncionariosComissado extends Funcionarios {
    double percentual, vendas;

    public FuncionariosComissado(String nome, int matricula, double vendas, double percentual) {
        super(nome, matricula);
        this.vendas = vendas;
        this.percentual = percentual;
    }

    @Override
    public double calculoSalario() {
        return SALARIO_BASE + (vendas * percentual / 100);
    }

    @Override
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário Fixo: " + formatarMoeda(SALARIO_BASE));
        System.out.println("Comissão: " + formatarMoeda(vendas * percentual / 100));
        System.out.println("Salário Final: " + formatarMoeda(calculoSalario()));
        System.out.println("-------------------------");
    }
}

class FuncionarioProducao extends Funcionarios {
    double valorPorPeca;
    int quantidadeProduzida;

    public FuncionarioProducao(String nome, int matricula, int quantidadeProduzida, double valorPorPeca) {
        super(nome, matricula);
        this.quantidadeProduzida = quantidadeProduzida;
        this.valorPorPeca = valorPorPeca;
    }

    @Override
    public double calculoSalario() {
        return SALARIO_BASE + (valorPorPeca * quantidadeProduzida);
    }

    @Override
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("Matrícula: " + matricula);
        System.out.println("Salário Fixo: " + formatarMoeda(SALARIO_BASE));
        System.out.println("Produtividade: " + formatarMoeda(valorPorPeca * quantidadeProduzida));
        System.out.println("Salário Final: " + formatarMoeda(calculoSalario()));
        System.out.println("-------------------------");
    }
}

public class FolhaDePagamento {
    private static Scanner sc = new Scanner(System.in).useLocale(Locale.US);
    private static ArrayList<Funcionarios> funcionarios = new ArrayList<>();

    public static void main(String[] args) {
        int opcao = -1;
        limparConsole();

        do {
            menuFolha();
            System.out.print("Escolha uma opção: ");

            if (!sc.hasNextInt()) {
                System.out.println("Erro: Digite apenas números!");
                sc.next();
                sc.nextLine();
                continue;
            }

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastroFuncionariosPadrao();
                    aguardeParaContinuar();
                    break;
                case 2:
                    cadastroFuncionariosComissado();
                    aguardeParaContinuar();
                    break;
                case 3:
                    cadastroFuncionariosProducao();
                    aguardeParaContinuar();
                    break;
                case 4:
                    mostrarFuncionarios();
                    aguardeParaContinuar();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    aguardeParaContinuar();
                    break;
            }
        } while (opcao != 0);
        sc.close();
    }

    public static void menuFolha() {
        System.out.println("========================================");
        System.out.println("           📌 Folha de Pagamento");
        System.out.println("========================================");
        System.out.println("1 - Cadastrar Funcionário Padrão");
        System.out.println("2 - Cadastrar Funcionário Comissionado");
        System.out.println("3 - Cadastrar Funcionário Produção");
        System.out.println("4 - Gerar Folha de Pagamento");
        System.out.println("0 - Sair do Programa");
        System.out.println("======================================== \n");
    }

    public static void limparConsole() {
        for (int i = 0; i <= 50; i++) {
            System.out.println();
        }
    }

    public static void aguardeParaContinuar() {
        System.out.println("\n========================================");
        System.out.println("Pressione ENTER para continuar...");
        System.out.println("========================================");
        sc.nextLine();
        limparConsole();
    }

    public static void cadastroFuncionariosPadrao() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        int matricula = lerIntPositivo("Matrícula: ");
        sc.nextLine();

        funcionarios.add(new FuncionariosPadrao(nome, matricula));
        System.out.println("\n✅ Funcionário Padrão cadastrado com sucesso!");
    }

    public static void cadastroFuncionariosComissado() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        int matricula = lerIntPositivo("Matrícula: ");
        double vendas = lerDoublePositivo("Informe valor das vendas: ");
        double percentual = lerDoublePositivo("Informe comissão percentual (%): ");
        sc.nextLine();

        funcionarios.add(new FuncionariosComissado(nome, matricula, vendas, percentual));
        System.out.println("\n✅ Funcionário Comissionado cadastrado com sucesso!");
    }

    public static void cadastroFuncionariosProducao() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        int matricula = lerIntPositivo("Matrícula: ");
        int qtd = lerIntPositivo("Informe qtde de peças: ");
        double valor = lerDoublePositivo("Informe valor da peça: ");
        sc.nextLine();

        funcionarios.add(new FuncionarioProducao(nome, matricula, qtd, valor));
        System.out.println("\n✅ Funcionário Produção cadastrado com sucesso!");
    }

    public static void mostrarFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado no sistema.");
        } else {
            System.out.println("\n--- RELATÓRIO DE PAGAMENTOS ---");
            for (Funcionarios f : funcionarios) {
                f.exibirDados();
            }
        }
    }

    private static int lerIntPositivo(String msg) {
        int valor;
        do {
            System.out.print(msg);
            valor = sc.nextInt();
            if (valor < 0) System.out.println("Erro: Não pode ser negativo!");
        } while (valor < 0);
        return valor;
    }

    private static double lerDoublePositivo(String msg) {
        double valor;
        do {
            System.out.print(msg);
            valor = sc.nextDouble();
            if (valor < 0) System.out.println("Erro: Não pode ser negativo!");
        } while (valor < 0);
        return valor;
    }
}
