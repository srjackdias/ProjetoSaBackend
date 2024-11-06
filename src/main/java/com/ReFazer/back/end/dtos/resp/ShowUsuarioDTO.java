package com.ReFazer.back.end.dtos.resp;

import java.util.List;


public class ShowUsuarioDTO {
    

    private Long id_usuario;
    private String nome;
    private String especialidade;

    private String email;
    private String senha;
    private String telefone;
    private String cep;
    private String tipoUsuario;


    private ShowAvaliacaoDTO avaliacao;
    private List<ShowTrabalhoSolicitadoDTO> trabalhos;
    public Long getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    public ShowAvaliacaoDTO getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(ShowAvaliacaoDTO avaliacao) {
        this.avaliacao = avaliacao;
    }
    public List<ShowTrabalhoSolicitadoDTO> getTrabalhos() {
        return trabalhos;
    }
    public void setTrabalhos(List<ShowTrabalhoSolicitadoDTO> trabalhos) {
        this.trabalhos = trabalhos;
    }
  
   


    
}
