package br.com.fiap.paciente;

public class Paciente {
    private String nome;
    private int cpf;
    private StatusPaciente status;

    public Paciente(String nome, int cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.status = StatusPaciente.filaAtendimento;
    }

    public int getCpf() {return this.cpf;};

    public void setStatus(StatusPaciente status) { this.status = status; }

    public StatusPaciente getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\n CPF: " + this.cpf + "\n Status: " + this.status;
    }
}