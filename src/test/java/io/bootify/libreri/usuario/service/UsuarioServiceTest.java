package io.bootify.libreri.usuario.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

import io.bootify.libreri.usuario.domain.Usuario;
import io.bootify.libreri.usuario.model.UsuarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.bootify.libreri.fichado.repos.FichadoRepository;
import io.bootify.libreri.prestamo.repos.PrestamoRepository;
import io.bootify.libreri.roles.repos.RolesRepository;
import io.bootify.libreri.usuario.repos.UsuarioRepository;

import java.util.List;

class UsuarioServiceTest {

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        // Aquí puedes inicializar el servicio y mockear cualquier dependencia necesaria
        UsuarioRepository usuarioRepository = mock(UsuarioRepository.class);
        FichadoRepository fichadoRepository = mock(FichadoRepository.class);
        RolesRepository rolesRepository = mock(RolesRepository.class);
        PrestamoRepository prestamoRepository = mock(PrestamoRepository.class);

        usuarioService = new UsuarioService(usuarioRepository, fichadoRepository, rolesRepository, prestamoRepository);
    }

    @Test
    void testGet() {
        // Simula el comportamiento del repositorio
        when(usuarioService.get(anyInt()));

        // Llama al método que quieres probar
        UsuarioDTO usuarioDTO = usuarioService.get(1);

        // Realiza las aserciones
        assertNotNull(usuarioDTO);
        // Agrega más aserciones según tus necesidades
    }





    @Test
    void testCreate() {


        List<UsuarioDTO> usuarios = usuarioService.findAll();

        // Realiza las aserciones
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty());
        // Agrega más aserciones según tus necesidades
    }



    @Test
    void testUpdate() {
        // Simula el comportamiento del repositorio


        // Llama al método que quieres probar
        usuarioService.update(1, new UsuarioDTO());



        // No hay aserciones específicas para este método
    }

    @Test
    void testDelete() {
        // Llama al método que quieres probar
        usuarioService.delete(1);

        // Simula el comportamiento del repositorio y verifica que se llame al método deleteById

    }

    @Test
    void testFindByNameAndPassword() {
        // Simula el comportamiento del repositorio


        // Llama al método que quieres probar
        UsuarioDTO usuarioDTO = usuarioService.findByNameAndPassword("nombre", "contrasena");

        // Realiza las aserciones
        assertNotNull(usuarioDTO);

    }



    @Test
    void testGetReferencedWarning() {

        // Llama al método que quieres probar
        String warning = usuarioService.getReferencedWarning(1);

        // Realiza las aserciones
        assertNotNull(warning);

    }

    // Agrega más pruebas según sea necesario para otros métodos y casos especiales

}
