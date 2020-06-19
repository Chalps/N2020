package br.com.fiap.atendimento;

import br.com.fiap.paciente.Paciente;
import static javax.swing.JOptionPane.*;

public class Atendimento {
    private Paciente paciente;

    public Atendimento(Paciente paciente) {
        this.paciente = paciente;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public boolean questionario() {
        boolean res = false;
        int pesoResposta = 0;
        boolean febre = false;
        String resposta;

        resposta = showInputDialog("Você está com tosse (seca ou secretiva)?");
        if (resposta.equalsIgnoreCase("sim")) pesoResposta += 30;

        resposta = showInputDialog("Você está com febre?");
        if (resposta.equalsIgnoreCase("sim")) febre = true; pesoResposta += 30;

        resposta = showInputDialog("Você está com falta de ar?");
        if (resposta.equalsIgnoreCase("sim")) {
            if(febre) {
                pesoResposta += 80;
            } else {
                pesoResposta += 50;
            }
        };

        resposta = showInputDialog("Você está com dor de garganta?");
        if (resposta.equalsIgnoreCase("sim"))pesoResposta += 20;

        resposta = showInputDialog("Você está com cansaço?");
        if (resposta.equalsIgnoreCase("sim")) pesoResposta += 10;

        resposta = showInputDialog("Você está com coriza?");
        if (resposta.equalsIgnoreCase("sim")) pesoResposta += 10;

        resposta = showInputDialog("Você está com dores de cabeça e náuseas?");
        if (resposta.equalsIgnoreCase("sim")) pesoResposta += 5;

        resposta = showInputDialog("Você está com vômito e diarréia?");
        if (resposta.equalsIgnoreCase("sim")) pesoResposta += 5;

        if (pesoResposta >= 100) res = true;

        return res;
    }
}
