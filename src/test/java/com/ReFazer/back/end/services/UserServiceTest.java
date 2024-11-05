package com.ReFazer.back.end.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    

@Test
public void TestarEmailJaCadastrado() {
    CreateUsuarioDTO userDummy = new CreateUsuarioDTO();
    userDummy.setNome("Maria");
    userDummy.setEmail("sr.jackdias@gmail.com");
    userDummy.setSenha("5432");
    userDummy.setTelefone("991834");
    userDummy.setCep("49328");
    userDummy.setTipoUsuario("Trabalhador");

    // Primeiro, cria o usuário para garantir que o email já está cadastrado
    usuarioService.createUsuario(userDummy);

    // Verifique se a exceção é lançada ao tentar criar o mesmo usuário novamente
    EmailJaCadastradoException exception = assertThrows(EmailJaCadastradoException.class, () -> {
        usuarioService.createUsuario(userDummy);
    });

    // Verifique a mensagem da exceção
    assertEquals("O e-mail já está cadastrado: " + userDummy.getEmail(), exception.getMessage());
}

    
}
