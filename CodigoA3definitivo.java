package codigoa3definitivo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class CodigoA3definitivo {

    static final String ARQUIVO = "pessoas.csv";

    public static void main(String[] args) {
        ArrayList<Pessoa> pessoas = carregar();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("===== CADASTRO DE PESSOAS =====");
            System.out.println("1. Cadastrar nova Pessoa");
            System.out.println("2. Listar pessoas cadastradas");
            System.out.println("3. Excluir pessoa");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1: {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Celular: ");
                    String celular = scanner.nextLine();
                    System.out.print("Data de Nascimento: ");
                    String dataNascimento = scanner.nextLine();
                    System.out.print("Sexo: ");
                    String sexo = scanner.nextLine();
                    System.out.print("CEP: ");
                    String cep = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    pessoas.add(new Pessoa(nome, cpf, celular, dataNascimento, sexo, cep, email, senha));
                    salvar(pessoas);
                    System.out.println("Pessoa cadastrada com sucesso!");
                    break;
                }
                case 2: {
                    if (pessoas.isEmpty()) {
                        System.out.println("Nenhuma pessoa cadastrada.");
                    } else {
                        for (int i = 0; i < pessoas.size(); i++) {
                            System.out.println("[" + i + "]");
                            pessoas.get(i).exibirInformacoes();
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.print("Indice da pessoa a excluir (veja em 'Listar'): ");
                    int indice = scanner.nextInt();
                    scanner.nextLine();
                    if (indice >= 0 && indice < pessoas.size()) {
                        pessoas.remove(indice);
                        salvar(pessoas);
                        System.out.println("Pessoa removida.");
                    } else {
                        System.out.println("Indice invalido.");
                    }
                    break;
                }
                case 0:
                    System.out.println("Encerrando programa...");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        } while (opcao != 0);

        scanner.close();
    }

    static void salvar(ArrayList<Pessoa> pessoas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Pessoa p : pessoas) {
                writer.println(String.join(";", p.nome, p.cpf, p.celular,
                        p.dataNascimento, p.sexo, p.cep, p.email, p.senha));
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    static ArrayList<Pessoa> carregar() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return pessoas;

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";", -1);
                if (dados.length == 8) {
                    pessoas.add(new Pessoa(dados[0], dados[1], dados[2], dados[3],
                            dados[4], dados[5], dados[6], dados[7]));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }
        return pessoas;
    }
}

class Pessoa {
    String nome, cpf, celular, dataNascimento, sexo, cep, email, senha;

    public Pessoa(String nome, String cpf, String celular, String dataNascimento,
                  String sexo, String cep, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.celular = celular;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cep = cep;
        this.email = email;
        this.senha = senha;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Cpf: " + cpf);
        System.out.println("Celular: " + celular);
        System.out.println("Data de Nascimento: " + dataNascimento);
        System.out.println("Sexo: " + sexo);
        System.out.println("CEP: " + cep);
        System.out.println("Email: " + email);
        System.out.println("-------------------------------");
    }
}