/****************************************
nome: [Mirela Mei Costa]
nusp: [11208392]
*****************************************/

import java.util.Scanner;

class Ep1{
    public static final String[] caracteristicas = {
        "Formação Atualizada",
        "Pensamento sistêmico",
        "Atenção a detalhes",
        "Liderança",
        "Respeito à hierarquia",
        "Relacionamento com colegas",
        "Relacionamento com público",
        "Criatividade",
        "Foco no objetivo",
        "Resiliência",
        "Disponibilidade",
        "Precisão na comunicação",
        "Flexibilidade no entendimento",
        "Adequação da aparência à situação",
    };

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int quantidadeDeCargos = scanner.nextInt();

        String[] nomesDosCargos = new String[quantidadeDeCargos];
        float[][] caracteristicasDosCargos = new float[quantidadeDeCargos][caracteristicas.length];

        // Para cada cargo
        for (int i = 0; i < quantidadeDeCargos; i++){
            scanner.nextLine();
            nomesDosCargos[i] = scanner.nextLine();

            // Para cada característica
            for (int j = 0; j < caracteristicas.length; j++){
                caracteristicasDosCargos[i][j] = scanner.nextFloat();
            }
        }

        int quantidadeDePessoas = scanner.nextInt();
        String[] nomesDasPessoas = new String[quantidadeDePessoas];
        float[][] caracteristicasDasPessoas = new float[quantidadeDePessoas][caracteristicas.length];

        // Para cada pessoa
        for (int i = 0; i < quantidadeDePessoas; i++){
            scanner.nextLine();
            nomesDasPessoas[i] = scanner.nextLine();

            for (int j = 0; j < caracteristicas.length; j++){
                caracteristicasDasPessoas[i][j] = scanner.nextFloat();
            }
        }

        for (int i = 0; i < quantidadeDeCargos; i++){
            System.out.println();
            System.out.println(nomesDosCargos[i]);
            int[] ordemDePessoas = escolhePessoas(caracteristicasDosCargos[i], caracteristicasDasPessoas);

            for (int j = 0; j < ordemDePessoas.length; j++){
                System.out.println(nomesDasPessoas[ordemDePessoas[j]]);
            }
        }
    }

    public static int[] escolhePessoas(float[] caracteristicasDoCargo, float[][] caracteristicasDasPessoas){
        float[] scoresDasPessoasNesteCargo = new float[caracteristicasDasPessoas.length];
        for (int i = 0; i < caracteristicasDasPessoas.length; i++){
            for (int j = 0; j < caracteristicasDoCargo.length; j++){
                scoresDasPessoasNesteCargo[i] += caracteristicasDoCargo[j] * caracteristicasDasPessoas[i][j];
            }
        }

        int[] pessoas = new int[caracteristicasDasPessoas.length];
        for (int i = 0; i < caracteristicasDasPessoas.length; i++){
            int maior = -1;
            for (int j = 0; j < caracteristicasDasPessoas.length; j++){
                if (foiUsado(pessoas, i, j)){
                    continue;
                }

                if (maior == -1){
                    maior = j;
                    continue;
                }

                if (scoresDasPessoasNesteCargo[j] > scoresDasPessoasNesteCargo[maior]){
                    maior = j;
                }
            }

            pessoas[i] = maior;
        }

        return pessoas;
    }

    // Verifica se já foi usado
    static boolean foiUsado(int[] pessoas, int limite, int idPessoa){
        for (int i = 0; i < limite; i++) {
            if (idPessoa == pessoas[i]) {
                return true;
            }
        }

        return false;
    }
}