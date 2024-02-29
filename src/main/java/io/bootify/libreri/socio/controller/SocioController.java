package io.bootify.libreri.socio.controller;

import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.prestamo.repos.PrestamoRepository;
import io.bootify.libreri.socio.model.SocioDTO;
import io.bootify.libreri.socio.service.SocioService;
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
@RequestMapping("/socios")
public class SocioController {

    private final SocioService socioService;
    private final PrestamoRepository prestamoRepository;

    public SocioController(final SocioService socioService,
            final PrestamoRepository prestamoRepository) {
        this.socioService = socioService;
        this.prestamoRepository = prestamoRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("prestamoValues", prestamoRepository.findAll(Sort.by("idPrestamo"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Prestamo::getIdPrestamo, Prestamo::getTipo)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("socios", socioService.findAll());
        return "socio/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("socio") final SocioDTO socioDTO) {
        return "socio/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("socio") @Valid final SocioDTO socioDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "socio/add";
        }
        socioService.create(socioDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("socio.create.success"));
        return "redirect:/socios";
    }

    @GetMapping("/edit/{idSocio}")
    public String edit(@PathVariable(name = "idSocio") final Integer idSocio, final Model model) {
        model.addAttribute("socio", socioService.get(idSocio));
        return "socio/edit";
    }

    @PostMapping("/edit/{idSocio}")
    public String edit(@PathVariable(name = "idSocio") final Integer idSocio,
            @ModelAttribute("socio") @Valid final SocioDTO socioDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "socio/edit";
        }
        socioService.update(idSocio, socioDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("socio.update.success"));
        return "redirect:/socios";
    }

    @PostMapping("/delete/{idSocio}")
    public String delete(@PathVariable(name = "idSocio") final Integer idSocio,
            final RedirectAttributes redirectAttributes) {
        socioService.delete(idSocio);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("socio.delete.success"));
        return "redirect:/socios";
    }

}
