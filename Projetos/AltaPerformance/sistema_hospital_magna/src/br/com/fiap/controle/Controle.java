package br.com.fiap.controle;

import br.com.fiap.atendimento.Atendimento;
import br.com.fiap.fila.FilaPacientes;
import br.com.fiap.paciente.Paciente;
import br.com.fiap.paciente.StatusPaciente;

import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.*;

public class Controle {
    private FilaPacientes filaAtendimento;
    private FilaPacientes filaInternacao;
    private FilaPacientes listaPacientes;
    private int leitosTotal, leitosUso;
    private Atendimento atendimento;

    public Controle(int leitos) {
        this.filaAtendimento = new FilaPacientes();
        this.filaInternacao = new FilaPacientes();
        this.listaPacientes = new FilaPacientes();
        this.leitosTotal = leitos;
    }

    public void recepcao() {
        String nome = null;
        int cpf = 0;
        try {
            nome = showInputDialog(null, "Qual o nome do paciente?", "Nome", QUESTION_MESSAGE);
            cpf = parseInt(showInputDialog(null, "Qual o CPF do paciente?", "CPF", QUESTION_MESSAGE));
            listaPacientes.add(new Paciente(nome, cpf));
            filaAtendimento.add(listaPacientes.getLast());
        } catch (Exception e) {
            erro("Digite apenas números nos campos de telefone e CPF!");
        }
    }

    public void atendimento() {
        if (filaAtendimento.isEmpty()) {
            showMessageDialog(null, "Ninguém esperando para atendimento!", "Atendimento", INFORMATION_MESSAGE);
        } else {
            atendimento = new Atendimento(filaAtendimento.remove());
            if (atendimento.questionario()) {
                if (temVaga()) {
                    atendimento.getPaciente().setStatus(StatusPaciente.internado);
                    leitosUso++;
                    showMessageDialog(null, "O paciente com o cpf: " + atendimento.getPaciente().getCpf() + " foi internado com sucesso!", "Atendimento", INFORMATION_MESSAGE);
                } else {
                    filaInternacao.add(atendimento.getPaciente());
                    atendimento.getPaciente().setStatus(StatusPaciente.filaInternacao);
                    showMessageDialog(null, "O paciente com o cpf: " + atendimento.getPaciente().getCpf() + " está na fila de espera para internação", "Atendimento", INFORMATION_MESSAGE);
                }
            } else {
                showMessageDialog(null, "O paciente com o cpf: " + atendimento.getPaciente().getCpf() + " não precisa de internação e está liberado!", "Atendimento", INFORMATION_MESSAGE);
                atendimento.getPaciente().setStatus(StatusPaciente.liberado);
            }
        }
    }

    public void liberarVaga(int cpf, StatusPaciente status) {
        boolean nAchou = true;
        for (int i = 0; i < listaPacientes.size(); i++) {
            if (listaPacientes.get(i).getStatus() == StatusPaciente.internado) {
                if (listaPacientes.get(i).getCpf() == cpf) {
                    listaPacientes.get(i).setStatus(status);
                    showMessageDialog(null, "O paciente com o cpf: " + cpf + " foi liberado com sucesso!", "Liberação de vagas", INFORMATION_MESSAGE);
                    leitosUso--;
                    if (!filaInternacao.isEmpty()) {
                        filaInternacao.remove().setStatus(StatusPaciente.internado);
                    }
                    nAchou = false;
                    break;
                }
            }
        }
        if (nAchou) showMessageDialog(null, "Paciente não encontrado", "Liberação de vagas", ERROR_MESSAGE);
    }

    public void consultarPaciente(int cpf) {
        boolean nAchou = true;
        for (int i = 0; i < listaPacientes.size(); i++) {
            if (listaPacientes.get(i).getCpf() == cpf) {
                showMessageDialog(null, listaPacientes.get(i).toString(), "Consulta de CPF", INFORMATION_MESSAGE);
                nAchou = false;
                break;
            }
        }
        if (nAchou) showMessageDialog(null, "Paciente não encontrado", "Liberação de vagas", ERROR_MESSAGE);
    }

    public boolean temVaga() {
        if (leitosTotal - leitosUso > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void erro(String msg) {
        showMessageDialog(null, msg, "Erro", ERROR_MESSAGE);
    }
}
