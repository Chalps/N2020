package br.com.fiap;

import br.com.fiap.atendimento.Atendimento;
import br.com.fiap.controle.Controle;
import br.com.fiap.paciente.StatusPaciente;

import java.util.Optional;
import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;

public class Main {

    public static void main(String[] args) {
        Controle controle = new Controle(parseInt(showInputDialog("Quantos leitos estão disponíveis?")));

        final Object menu[] = {"Recepção de paciente", "Atendimento de paciente", "Liberação de vaga na unidade", "Consulta de registro de paciente", "Sair"};
        final Object motivoLiberacao[] = {"Alta", "Óbito"};
        Object opcao, motivo;
        int cpf;

        do {
            opcao = showInputDialog(null, "Escolha a opção desejada", "Clinica Médica", QUESTION_MESSAGE, null, menu, menu[0]);
            switch (opcao.toString()) {
                case "Recepção de paciente":
                        controle.recepcao();
                    break;
                case "Atendimento de paciente":
                    controle.atendimento();
                    break;
                case "Liberação de vaga na unidade":
                    try {
                        cpf = parseInt(showInputDialog("Digite o cpf do paciente que será liberado (apenas números)"));
                        motivo = showInputDialog(null, "Qual o motivo da liberação?", "Clinica Médica", QUESTION_MESSAGE, null, motivoLiberacao, motivoLiberacao[0]);
                        if (motivo.toString().equals("Alta")) {
                            controle.liberarVaga(cpf, StatusPaciente.emAlta);
                        } else {
                            controle.liberarVaga(cpf, StatusPaciente.obito);
                        }
                    } catch (Exception e) {
                        showMessageDialog(null, "Digite apenas números para o CPF!", "Erro", ERROR_MESSAGE);
                    }
                    break;
                case "Consulta de registro de paciente":
                    controle.consultarPaciente(parseInt(showInputDialog("Digite o cpf do paciente que será liberado (apenas números)")));
                    break;
            }
        } while (!opcao.equals(menu[4].toString()));

    }
}
