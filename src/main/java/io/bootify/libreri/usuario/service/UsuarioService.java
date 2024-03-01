package io.bootify.libreri.usuario.service;

import io.bootify.libreri.fichado.domain.Fichado;
import io.bootify.libreri.fichado.repos.FichadoRepository;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.prestamo.repos.PrestamoRepository;
import io.bootify.libreri.roles.domain.Roles;
import io.bootify.libreri.roles.repos.RolesRepository;
import io.bootify.libreri.usuario.domain.Usuario;
import io.bootify.libreri.usuario.model.UsuarioDTO;
import io.bootify.libreri.usuario.repos.UsuarioRepository;
import io.bootify.libreri.errors.NotFoundException;
import io.bootify.libreri.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final FichadoRepository fichadoRepository;
    private final RolesRepository rolesRepository;
    private final PrestamoRepository prestamoRepository;


    public UsuarioService(final UsuarioRepository usuarioRepository,
            final FichadoRepository fichadoRepository, final RolesRepository rolesRepository,
            final PrestamoRepository prestamoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.fichadoRepository = fichadoRepository;
        this.rolesRepository = rolesRepository;
        this.prestamoRepository = prestamoRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("idUser"));
        return usuarios.stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final Integer idUser) {
        try {
            UsuarioDTO u =usuarioRepository.findById(idUser)
                    .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                    .orElseThrow(NotFoundException::new);

            return u;

        }catch (Exception e){
            return null;

        }
    }

    public Integer create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getIdUser();
    }

    public void update(final Integer idUser, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(idUser)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final Integer idUser) {
        usuarioRepository.deleteById(idUser);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setIdUser(usuario.getIdUser());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setDiaAlta(usuario.getDiaAlta());
        usuarioDTO.setDiaBaja(usuario.getDiaBaja());
        usuarioDTO.setContresena(usuario.getContresena());
        usuarioDTO.setDni(usuario.getDni());
        usuarioDTO.setEdad(usuario.getEdad());
        usuarioDTO.setIdSuper(usuario.getIdSuper());
        usuarioDTO.setFichadoUserFichadoes(usuario.getFichadoUserFichadoes().stream()
                .map(fichado -> fichado.getIdFichado())
                .toList());

        usuarioDTO.setRol(usuario.getRol().getIdRol());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setDiaAlta(usuarioDTO.getDiaAlta());
        usuario.setDiaBaja(usuarioDTO.getDiaBaja());
        usuario.setContresena(usuarioDTO.getContresena());
        usuario.setDni(usuarioDTO.getDni());
        usuario.setEdad(usuarioDTO.getEdad());
        usuario.setIdSuper(usuarioDTO.getIdSuper());
        final List<Fichado> fichadoUserFichadoes = fichadoRepository.findAllById(
                usuarioDTO.getFichadoUserFichadoes() == null ? Collections.emptyList() : usuarioDTO.getFichadoUserFichadoes());
        if (fichadoUserFichadoes.size() != (usuarioDTO.getFichadoUserFichadoes() == null ? 0 : usuarioDTO.getFichadoUserFichadoes().size())) {
            throw new NotFoundException("one of fichadoUserFichadoes not found");
        }
        usuario.setFichadoUserFichadoes(fichadoUserFichadoes.stream().collect(Collectors.toSet()));
        final Roles rol = usuarioDTO.getRol() == null ? null : rolesRepository.findById(usuarioDTO.getRol())
                .orElseThrow(() -> new NotFoundException("rol not found"));
        usuario.setRol(rol);
        return usuario;
    }

    public String getReferencedWarning(final Integer idUser) {
        final Usuario usuario = usuarioRepository.findById(idUser)
                .orElseThrow(NotFoundException::new);
        final Prestamo emplePrestamo = prestamoRepository.findFirstByEmple(usuario);
        if (emplePrestamo != null) {
            return WebUtils.getMessage("usuario.prestamo.emple.referenced", emplePrestamo.getIdPrestamo());
        }
        return null;
    }

    public UsuarioDTO findByNameAndPassword(String nombre, String contresena) {
        Usuario usuario = usuarioRepository.findByNombreAndContresena(nombre,contresena);
        if (usuario == null) {
            throw new NotFoundException("Usuario no encontrado");
        }
        return mapToDTO(usuario, new UsuarioDTO());
    }



}
