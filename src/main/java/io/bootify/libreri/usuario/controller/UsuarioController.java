package io.bootify.libreri.usuario.controller;

import io.bootify.libreri.fichado.domain.Fichado;
import io.bootify.libreri.fichado.repos.FichadoRepository;
import io.bootify.libreri.roles.domain.Roles;
import io.bootify.libreri.roles.repos.RolesRepository;
import io.bootify.libreri.usuario.model.UsuarioDTO;
import io.bootify.libreri.usuario.service.UsuarioService;
import io.bootify.libreri.util.CustomCollectors;
import io.bootify.libreri.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final FichadoRepository fichadoRepository;
    private final RolesRepository rolesRepository;

    public UsuarioController(final UsuarioService usuarioService,
            final FichadoRepository fichadoRepository, final RolesRepository rolesRepository) {
        this.usuarioService = usuarioService;
        this.fichadoRepository = fichadoRepository;
        this.rolesRepository = rolesRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("fichadoUserFichadoesValues", fichadoRepository.findAll(Sort.by("idFichado"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Fichado::getIdFichado, Fichado::getIdFichado)));
        model.addAttribute("rolValues", rolesRepository.findAll(Sort.by("idRol"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Roles::getIdRol, Roles::getRol)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuario/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("usuario") final UsuarioDTO usuarioDTO) {
        return "usuario/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("usuario") @Valid final UsuarioDTO usuarioDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "usuario/add";
        }
        usuarioService.create(usuarioDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("usuario.create.success"));
        return "redirect:/usuarios";
    }

    @GetMapping("/edit/{idUser}")
    public String edit(@PathVariable(name = "idUser") final Integer idUser, final Model model) {
        model.addAttribute("usuario", usuarioService.get(idUser));
        return "usuario/edit";
    }

    @PostMapping("/edit/{idUser}")
    public String edit(@PathVariable(name = "idUser") final Integer idUser,
            @ModelAttribute("usuario") @Valid final UsuarioDTO usuarioDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "usuario/edit";
        }
        usuarioService.update(idUser, usuarioDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("usuario.update.success"));
        return "redirect:/usuarios";
    }

    @PostMapping("/delete/{idUser}")
    public String delete(@PathVariable(name = "idUser") final Integer idUser,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = usuarioService.getReferencedWarning(idUser);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            usuarioService.delete(idUser);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("usuario.delete.success"));
        }
        return "redirect:/usuarios";
    }

    @GetMapping("/productividad")
    public String getProductividad(final Model model) {
        List<Map<String, Object>> productividad = usuarioService.getProductividad();

        // Agregar la productividad al modelo para pasarla a la vista
        model.addAttribute("productividad", productividad);

        // Redirigir a la plantilla adecuada para mostrar la productividad
        return "usuario/productividad";
    }

}