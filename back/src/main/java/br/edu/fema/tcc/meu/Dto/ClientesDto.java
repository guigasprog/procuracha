package br.edu.fema.tcc.meu.Dto;

import br.edu.fema.tcc.meu.model.Cliente;

public class ClientesDto {
    private int idCliente;
    private String nome;
    private String telefone;
    private String endereco;

    public ClientesDto(){

    }

    public ClientesDto(int idCliente, String nome, String telefone, String endereco) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }
    public ClientesDto(Cliente cliente) {
        idCliente = cliente.getId();
        nome = cliente.getNome();
        telefone = cliente.getTelefone();
        endereco = cliente.getEndereco();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
