package io.bootify.libreri.roles.service;

import io.bootify.libreri.roles.domain.Roles;
import io.bootify.libreri.roles.model.RolesDTO;
import io.bootify.libreri.roles.repos.RolesRepository;
import io.bootify.libreri.usuario.domain.Usuario;
import io.bootify.libreri.usuario.repos.UsuarioRepository;
import io.bootify.libreri.errors.NotFoundException;
import io.bootify.libreri.util.WebUtils;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class RolesService {

    private final RolesRepository rolesRepository;
    private final UsuarioRepository usuarioRepository;

    public RolesService(final RolesRepository rolesRepository,
            final UsuarioRepository usuarioRepository) {
        this.rolesRepository = rolesRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<RolesDTO> findAll() {
        final List<Roles> roleses = rolesRepository.findAll(Sort.by("idRol"));
        return roleses.stream()
                .map(roles -> mapToDTO(roles, new RolesDTO()))
                .toList();
    }

    public RolesDTO get(final Integer idRol) {
        try {
            RolesDTO r = rolesRepository.findById(idRol)
                    .map(roles -> mapToDTO(roles, new RolesDTO()))
                    .orElse(null);

            return r;
        } catch (Exception e) {
            return null;
        }
    }


    public Integer create(final RolesDTO rolesDTO) {
        final Roles roles = new Roles();
        mapToEntity(rolesDTO, roles);
        return rolesRepository.save(roles).getIdRol();
    }

    public void update(final Integer idRol, final RolesDTO rolesDTO) {
        final Roles roles = rolesRepository.findById(idRol)
                .orElseThrow(NotFoundException::new);
        mapToEntity(rolesDTO, roles);
        rolesRepository.save(roles);
    }

    public void delete(final Integer idRol) {
        rolesRepository.deleteById(idRol);
    }

    private RolesDTO mapToDTO(final Roles roles, final RolesDTO rolesDTO) {
        rolesDTO.setIdRol(roles.getIdRol());
        rolesDTO.setRol(roles.getRol());
        return rolesDTO;
    }

    private Roles mapToEntity(final RolesDTO rolesDTO, final Roles roles) {
        roles.setRol(rolesDTO.getRol());
        return roles;
    }

    public String getReferencedWarning(final Integer idRol) {
        final Roles roles = rolesRepository.findById(idRol)
                .orElseThrow(NotFoundException::new);
        final Usuario rolUsuario = usuarioRepository.findFirstByRol(roles);
        if (rolUsuario != null) {
            return WebUtils.getMessage("roles.usuario.rol.referenced", rolUsuario.getIdUser());
        }
        return null;
    }

}
