package com.ReFazer.back.end.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ReFazer.back.end.dtos.req.CreateUsuarioDTO;
import com.ReFazer.back.end.dtos.resp.ShowUsuarioDTO;
import com.ReFazer.back.end.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    

    @Autowired

    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody CreateUsuarioDTO dto) {

        System.out.println(dto.getNome());
        System.out.println(dto.getEmail());
        System.out.println(dto.getSenha());
        System.out.println(dto.getTelefone());
        System.out.println(dto.getCep());
        System.out.println(dto.getTipo_usuario());

        System.out.println(dto.getAvaliacao().getNota_avaliacao());
        System.out.println(dto.getAvaliacao().getTexto_avaliativo());


        System.out.println(dto.getTrabalhos().get(0).getTipo());
        System.out.println(dto.getTrabalhos().get(0).getValor());
        System.out.println(dto.getTrabalhos().get(0).getLocalizacao());
        System.out.println(dto.getTrabalhos().get(0).getDescricao());
        System.out.println(dto.getTrabalhos().get(0).isStatus());




        usuarioService.createUsuario(dto);

        return ResponseEntity.status(201).build();
    }


    @GetMapping
    public ResponseEntity<?> getAllUsuarios() {

        List<ShowUsuarioDTO> usuarios = usuarioService.getAllUsuarios();

        return ResponseEntity.status(200).body(usuarios);
    }
}
