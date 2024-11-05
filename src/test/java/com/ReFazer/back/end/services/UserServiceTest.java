package com.ReFazer.back.end.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ReFazer.back.end.dtos.req.CreateUsuarioDTO;
import com.ReFazer.back.end.entities.UsuarioEntity;

import jakarta.transaction.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {
      @Autowired
    UsuarioService usuarioService;

    @Test
    public void testCriarUsuarioComSucesso() {
    
        CreateUsuarioDTO userDummy = new CreateUsuarioDTO();
        userDummy.setNome("jackson");
        userDummy.setEmail("sr.jackdias@gmail.com");
        userDummy.setSenha("1234");
        userDummy.setTelefone("12345");
        userDummy.setCep("88049317");
        userDummy.setTipoUsuario("cliente");
    
        var usuarioCriado = usuarioService.createUsuario(userDummy);
    
        assertNotNull(usuarioCriado);
        assertNotNull(usuarioCriado.getId_usuario());
        assertEquals(userDummy.getNome(), usuarioCriado.getNome());
        assertEquals(userDummy.getEmail(), usuarioCriado.getEmail());
        assertEquals(userDummy.getSenha(), usuarioCriado.getSenha());
        assertEquals(userDummy.getTelefone(), usuarioCriado.getTelefone());
        assertEquals(userDummy.getCep(), usuarioCriado.getCep());
        assertEquals(userDummy.getTipoUsuario(), usuarioCriado.getTipoUsuario());
    }
    
    
}
