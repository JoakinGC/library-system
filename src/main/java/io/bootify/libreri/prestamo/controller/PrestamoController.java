package io.bootify.libreri.prestamo.controller;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.prestamo.model.PrestamoDTO;
import io.bootify.libreri.prestamo.service.PrestamoService;
import io.bootify.libreri.usuario.domain.Usuario;
import io.bootify.libreri.usuario.repos.UsuarioRepository;
import io.bootify.libreri.util.CustomCollectors;
import io.bootify.libreri.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final EjemplarRepository ejemplarRepository;
    private final UsuarioRepository usuarioRepository;

    public PrestamoController(final PrestamoService prestamoService,
            final EjemplarRepository ejemplarRepository,
            final UsuarioRepository usuarioRepository) {
        this.prestamoService = prestamoService;
        this.ejemplarRepository = ejemplarRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("ejemplarValues", ejemplarRepository.findAll(Sort.by("idEjemplar"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Ejemplar::getIdEjemplar, Ejemplar::getIdEjemplar)));
        model.addAttribute("empleValues", usuarioRepository.findAll(Sort.by("idUser"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuario::getIdUser, Usuario::getNombre)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("prestamoes", prestamoService.findAll());
        return "prestamo/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("prestamo") final PrestamoDTO prestamoDTO) {
        return "prestamo/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "prestamo/add";
        }
        prestamoService.create(prestamoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.create.success"));
        return "redirect:/prestamos";
    }

    @GetMapping("/edit/{idPrestamo}")
    public String edit(@PathVariable(name = "idPrestamo") final Integer idPrestamo,
            final Model model) {
        model.addAttribute("prestamo", prestamoService.get(idPrestamo));
        return "prestamo/edit";
    }

    @PostMapping("/edit/{idPrestamo}")
    public String edit(@PathVariable(name = "idPrestamo") final Integer idPrestamo,
            @ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "prestamo/edit";
        }
        prestamoService.update(idPrestamo, prestamoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.update.success"));
        return "redirect:/prestamos";
    }

    @PostMapping("/delete/{idPrestamo}")
    public String delete(@PathVariable(name = "idPrestamo") final Integer idPrestamo,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = prestamoService.getReferencedWarning(idPrestamo);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            prestamoService.delete(idPrestamo);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("prestamo.delete.success"));
        }
        return "redirect:/prestamos";
    }

}
