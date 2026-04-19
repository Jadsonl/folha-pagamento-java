import java.util.Scanner;
import java.util.ArrayList;
import java.util.Locale;
import java.text.NumberFormat;

abstract  class Funcionarios {
    String nome;
    int matricula;
    double salario = 2000;

    public Funcionarios(String nome, int matricula, double salario){
        this.nome = nome;
        this.matricula = matricula;
        this.salario = salario;
    }

    abstract public double calculoSalario();

    public void exibirDados(){
        NumberFormat SalarioReal = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String SalarioFormatado = SalarioReal.format(calculoSalario());
        System.out.println("-------------");
        System.out.println("Nome: " + nome);
        System.out.println("Matricula: " + matricula);
        System.out.println("Salario: " + SalarioFormatado);
    }
};

class FuncionariosPadrao extends Funcionarios{
    public FuncionariosPadrao(String nome, int matricula, double salario){
        super(nome, matricula, salario);
    }

    @Override
    public double calculoSalario(){
        return salario;
    }
};

class FuncionariosComissado extends Funcionarios {
    double percentual;
    int vendas;

    public FuncionariosComissado(String nome, int matricula, double salario, int vendas, double percentual) {
        super(nome, matricula, salario);
        this.vendas = vendas;
        this.percentual = percentual;
    }

    @Override
    public double calculoSalario() {
        return salario + (vendas * percentual / 100);
    }

}

class FuncionarioProducao extends Funcionarios {
    double valorPorPeça;
    int quantidadeProduzida;

    public FuncionarioProducao(String nome, int matricula, double salario, double valorPorPeça, int quantidadeProduzida) {
        super(nome, matricula, salario);
        this.valorPorPeça = valorPorPeça;
        this.quantidadeProduzida = quantidadeProduzida;
    }
    @Override
    public double calculoSalario() {
        return salario + (valorPorPeça * quantidadeProduzida);
    }
}

public class FolhaDePagamento {
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Funcionarios> funcionarios = new ArrayList<>();
    public static Double salarioBase = 2000.00;

    public static void main(String[] args) {
        int opcao;
        do{
            menuFolha();
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            switch (opcao){
                case 1:
                    cadastroFuncionariosPadrao();
                    break;
                case 2:
                    cadastroFuncionariosComissado();
                    break;
                case 3:
                    cadastroFuncionariosProducao();
                    break;
                case 4:
                    mostrarFuncionarios();
                    break;
                case 0:
                    System.out.println("saindo do programa...");
                    break;
                default:
                    System.out.println("opçao invalida");
            }
        }while(opcao != 0);
        sc.close();
    }

    public static void menuFolha(){
        System.out.println("=====================================");
        System.out.println("         🧾Folha de Pagamento");
        System.out.println("=====================================");
        System.out.println("1 - cadastrar funcionario padrao");
        System.out.println("2 - cadastrar funcionario comissado");
        System.out.println("3 - cadastrar funcionario Produção");
        System.out.println("4 - Gerar Folha de pagamento");
        System.out.println("0 - Sair do programa");
        System.out.println("=====================================");

        System.out.println();
    }
    public static void cadastroFuncionariosPadrao(){
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Matricula: ");
        int matricula = sc.nextInt();

        // Valide as entradas de dados (ex.: impedir números negativos).
        while(matricula < 0 ){
            System.out.println("Erro: A Matricula nao pode ser negativa: ");
            System.out.print("Matricula: ");
            matricula = sc.nextInt();
        }
        funcionarios.add(new FuncionariosPadrao(nome, matricula, salarioBase ));

        System.out.println("✅ Funcionario Padrao Cadastrado com Sucesso!");
        limparConsole();

    }
    public static void cadastroFuncionariosComissado(){
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Matricula: ");
        int matricula = sc.nextInt();

        while(matricula < 0 ){
            System.out.println("Erro: A Matricula nao pode ser negativa: ");
            System.out.print("Matricula: ");
            matricula = sc.nextInt();

        }
        System.out.print("Informe valor das vendas: ");
        int vendas = sc.nextInt();

        while(vendas < 0 ){
            System.out.println("Erro: As Vendas nao podem ser negativa: ");
            System.out.print("Vendas: ");
            vendas = sc.nextInt();

        }

        System.out.print("Informe comissão percentual:  ");
        double percentual  = sc.nextDouble();

        while(percentual < 0 ){
            System.out.println("Erro: O Percentual nao pode ser negativa: ");
            System.out.print("Percentual: ");
            percentual = sc.nextInt();
        }

        funcionarios.add(new FuncionariosComissado(nome, matricula, salarioBase, vendas, percentual ));

        System.out.println("✅ Funcionario Padrao Cadastrado com Sucesso!");
        limparConsole();

    }
    public static void cadastroFuncionariosProducao(){
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Matricula: ");
        int matricula = sc.nextInt();

        // Valide as entradas de dados (ex.: impedir números negativos).
        while(matricula < 0 ){
            System.out.println("Erro: A Matricula nao pode ser negativa: ");
            System.out.print("Matricula: ");
            matricula = sc.nextInt();
        }

        System.out.print("Informe qtde de peças:  ");
        int qtdPecas = sc.nextInt();

        while(matricula < 0 ){
            System.out.println("Erro: A pecas nao pode ser negativa: ");
            System.out.print("peças: ");
            qtdPecas = sc.nextInt();
        }

        System.out.print("Informe valor da peça: ");
        int valorPecas = sc.nextInt();

        while(matricula < 0 ){
            System.out.println("Erro: A pecas nao pode ser negativa: ");
            System.out.print("peças: ");
            valorPecas = sc.nextInt();
        }

        funcionarios.add(new FuncionarioProducao(nome, matricula, salarioBase, valorPecas, qtdPecas  ));

        System.out.println("✅ Funcionario Padrao Cadastrado com Sucesso!");
        limparConsole();

    }
    public static void mostrarFuncionarios(){
        for(Funcionarios f: funcionarios ){
            f.exibirDados();
        }
        sc.nextLine();
        aguardaUsuario();
    }
    public static void limparConsole(){
        for(int aleatorio = 1; aleatorio <= 50; aleatorio++){
            System.out.println();
        }
    }
    public static void aguardaUsuario(){
        System.out.println();
        System.out.println("=====================================");
        System.out.println("Pressione qualquer tecla para continuar...");
        System.out.println("=====================================");
        sc.nextLine();
        limparConsole();

    }
}