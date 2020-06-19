package br.com.fiap.fila;

import br.com.fiap.paciente.Paciente;

public class FilaPacientes {

    private class NO {
        Paciente dado;
        NO prox;
    }

    private NO ini;
    private NO fim;
    private int size;

    public void ini() {
        ini = fim = null;
        size = 0;
    }

    public boolean isEmpty() {
        if (ini == null && fim == null) {
            return true;
        } else {
            return false;
        }
    }

    public void add(Paciente e) {
        NO novo = new NO();
        novo.dado = e;
        novo.prox = null;
        if (isEmpty()) {
            ini = novo;
        } else {
            fim.prox = novo;
        }
        fim = novo;
        size++;
    }

    public Paciente remove() {
        Paciente paciente = ini.dado;
        ini = ini.prox;
        if (ini == null) {
            fim = null;
        }
        size --;
        return paciente;
    }

    public int size() {
        return this.size;
    }

    public Paciente getLast() {
        return this.fim.dado;
    }

    public Paciente get(int index) {
        NO p = ini;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                break;
            }
            p = p.prox;
        }
        return p.dado;
    }
}
