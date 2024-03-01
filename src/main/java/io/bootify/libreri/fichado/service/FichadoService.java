package io.bootify.libreri.fichado.service;

import io.bootify.libreri.fichado.domain.Fichado;
import io.bootify.libreri.fichado.model.FichadoDTO;
import io.bootify.libreri.fichado.repos.FichadoRepository;
import io.bootify.libreri.usuario.domain.Usuario;
import io.bootify.libreri.usuario.repos.UsuarioRepository;
import io.bootify.libreri.errors.NotFoundException;
import io.bootify.libreri.util.WebUtils;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class FichadoService {

    private final FichadoRepository fichadoRepository;
    private final UsuarioRepository usuarioRepository;

    public FichadoService(final FichadoRepository fichadoRepository,
            final UsuarioRepository usuarioRepository) {

        this.fichadoRepository = fichadoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<FichadoDTO> findAll() {
        final List<Fichado> fichadoes = fichadoRepository.findAll(Sort.by("idFichado"));
        return fichadoes.stream()
                .map(fichado -> mapToDTO(fichado, new FichadoDTO()))
                .toList();
    }

    public FichadoDTO get(final Integer idFichado) {
        return fichadoRepository.findById(idFichado)
                .map(fichado -> mapToDTO(fichado, new FichadoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final FichadoDTO fichadoDTO) {
        final Fichado fichado = new Fichado();
        mapToEntity(fichadoDTO, fichado);
        return fichadoRepository.save(fichado).getIdFichado();
    }

    public void update(final Integer idFichado, final FichadoDTO fichadoDTO) {
        final Fichado fichado = fichadoRepository.findById(idFichado)
                .orElseThrow(NotFoundException::new);
        mapToEntity(fichadoDTO, fichado);
        fichadoRepository.save(fichado);
    }

    public void delete(final Integer idFichado) {
        final Fichado fichado = fichadoRepository.findById(idFichado)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        usuarioRepository.findAllByFichadoUserFichadoes(fichado)
                .forEach(usuario -> usuario.getFichadoUserFichadoes().remove(fichado));
        fichadoRepository.delete(fichado);
    }

    private FichadoDTO mapToDTO(final Fichado fichado, final FichadoDTO fichadoDTO) {
        fichadoDTO.setIdFichado(fichado.getIdFichado());
        fichadoDTO.setFechaFichaje(fichado.getFechaFichaje());
        fichadoDTO.setHoraEntrada(fichado.getHoraEntrada());
        fichadoDTO.setHoraSalida(fichado.getHoraSalida());
        fichadoDTO.setTiempoTotalDia(fichado.getTiempoTotalDia());
        return fichadoDTO;
    }

    private Fichado mapToEntity(final FichadoDTO fichadoDTO, final Fichado fichado) {
        fichado.setFechaFichaje(fichadoDTO.getFechaFichaje());
        fichado.setHoraEntrada(fichadoDTO.getHoraEntrada());
        fichado.setHoraSalida(fichadoDTO.getHoraSalida());
        fichado.setTiempoTotalDia(fichadoDTO.getTiempoTotalDia());
        return fichado;
    }

    public String getReferencedWarning(final Integer idFichado) {
        final Fichado fichado = fichadoRepository.findById(idFichado)
                .orElseThrow(NotFoundException::new);
        final Usuario fichadoUserFichadoesUsuario = usuarioRepository.findFirstByFichadoUserFichadoes(fichado);
        if (fichadoUserFichadoesUsuario != null) {
            return WebUtils.getMessage("fichado.usuario.fichadoUserFichadoes.referenced", fichadoUserFichadoesUsuario.getIdUser());
        }
        return null;
    }

}
