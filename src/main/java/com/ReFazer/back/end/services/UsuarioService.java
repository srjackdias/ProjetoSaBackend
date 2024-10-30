package com.ReFazer.back.end.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ReFazer.back.end.dtos.req.CreateTrabalhoSolicitadoDTO;
import com.ReFazer.back.end.dtos.req.CreateUsuarioDTO;
import com.ReFazer.back.end.dtos.resp.ShowAvaliacaoDTO;
import com.ReFazer.back.end.dtos.resp.ShowTrabalhoSolicitadoDTO;
import com.ReFazer.back.end.dtos.resp.ShowUsuarioDTO;
import com.ReFazer.back.end.entities.AvaliacaoEntity;
import com.ReFazer.back.end.entities.TrabalhoSolicitadoEntity;
import com.ReFazer.back.end.entities.UsuarioEntity;
import com.ReFazer.back.end.repositories.AvaliacaoRepository;
import com.ReFazer.back.end.repositories.TrabalhoSolicitadoRepository;
import com.ReFazer.back.end.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired

    UsuarioRepository usuarioRepository;

    @Autowired

    TrabalhoSolicitadoRepository trabalhoSolicitadoRepository;

    @Autowired

    AvaliacaoRepository avaliacaoRepository;

    public void createUsuario(CreateUsuarioDTO dto) {

        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome(dto.getNome());
        usuarioEntity.setEmail(dto.getEmail());
        usuarioEntity.setSenha(dto.getSenha());
        usuarioEntity.setTelefone(dto.getTelefone());
        usuarioEntity.setCep(dto.getCep());
        usuarioEntity.setTipo_usuario(dto.getTipo_usuario());

        usuarioEntity = usuarioRepository.save(usuarioEntity);

        AvaliacaoEntity avaliacaoEntity = new AvaliacaoEntity();
        avaliacaoEntity.setNota_avaliacao(dto.getAvaliacao().getNota_avaliacao());
        avaliacaoEntity.setTexto_avaliativo(dto.getAvaliacao().getTexto_avaliativo());

        avaliacaoEntity = avaliacaoRepository.save(avaliacaoEntity);

        List<TrabalhoSolicitadoEntity> trabalhosSolicitadoEntity = new ArrayList<>();

        for (CreateTrabalhoSolicitadoDTO trabalhoSolicitadoDTO : dto.getTrabalhos()) {
            TrabalhoSolicitadoEntity trabalhoSolicitadoEntity = new TrabalhoSolicitadoEntity();
            trabalhoSolicitadoEntity.setTipo(trabalhoSolicitadoDTO.getTipo());
            trabalhoSolicitadoEntity.setValor(trabalhoSolicitadoDTO.getValor());
            trabalhoSolicitadoEntity.setLocalizacao(trabalhoSolicitadoDTO.getLocalizacao());
            trabalhoSolicitadoEntity.setDescricao(trabalhoSolicitadoDTO.getDescricao());
            trabalhoSolicitadoEntity.setStatus(trabalhoSolicitadoDTO.isStatus());
            trabalhoSolicitadoEntity.setUsuario(usuarioEntity);
            trabalhoSolicitadoEntity.setCliente(usuarioEntity);
            trabalhoSolicitadoEntity.setTrabalhador(usuarioEntity);

            trabalhosSolicitadoEntity.add(trabalhoSolicitadoEntity);

        }

        trabalhoSolicitadoRepository.saveAll(trabalhosSolicitadoEntity);
    }

    public List<ShowUsuarioDTO> getAllUsuarios() {
        List<UsuarioEntity> usuarioEntity = usuarioRepository.findAll();
    
        return usuarioEntity.stream()
                .map(usuario -> {
                    ShowUsuarioDTO usuarioDTO = new ShowUsuarioDTO();
                    ShowAvaliacaoDTO avaliacaoDTO = new ShowAvaliacaoDTO();
                    List<ShowTrabalhoSolicitadoDTO> trabalhosSolicitadoDTO = new ArrayList<>();
    
                    for (TrabalhoSolicitadoEntity trabalhoSolicitadoEntity : usuario.getTrabalhos()) {
                        ShowTrabalhoSolicitadoDTO trabalhoSolicitadoDTO = new ShowTrabalhoSolicitadoDTO();
                        trabalhoSolicitadoDTO.setTipo(trabalhoSolicitadoEntity.getTipo());
                        trabalhoSolicitadoDTO.setValor(trabalhoSolicitadoEntity.getValor());
                        trabalhoSolicitadoDTO.setLocalizacao(trabalhoSolicitadoEntity.getLocalizacao());
                        trabalhoSolicitadoDTO.setDescricao(trabalhoSolicitadoEntity.getDescricao());
                        trabalhoSolicitadoDTO.setStatus(trabalhoSolicitadoEntity.isStatus());
                        trabalhosSolicitadoDTO.add(trabalhoSolicitadoDTO);
                    }
    
                    // Verifique se avaliacao não é nulo antes de acessar seus métodos
                    if (usuario.getAvaliacao() != null) {
                        avaliacaoDTO.setNota_avaliacao(usuario.getAvaliacao().getNota_avaliacao());
                        avaliacaoDTO.setTexto_avaliativo(usuario.getAvaliacao().getTexto_avaliativo());
                    }
    
                    usuarioDTO.setId_usuario(usuario.getId_usuario());
                    usuarioDTO.setNome(usuario.getNome());
                    usuarioDTO.setEmail(usuario.getEmail());
                    usuarioDTO.setSenha(usuario.getSenha());
                    usuarioDTO.setTelefone(usuario.getTelefone());
                    usuarioDTO.setCep(usuario.getCep());
                    usuarioDTO.setTipo_usuario(usuario.getTipo_usuario());
                    usuarioDTO.setTrabalhos(trabalhosSolicitadoDTO);
                    usuarioDTO.setAvaliacao(avaliacaoDTO);
    
                    return usuarioDTO;
                }).toList();
    }
    

}
