package io.bootify.libreri.revista.controller;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.revista.model.RevistaDTO;
import io.bootify.libreri.revista.service.RevistaService;
import io.bootify.libreri.socio.domain.Socio;
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
@RequestMapping("/revistas")
public class RevistaController {

    private final RevistaService revistaService;
    private final EjemplarRepository ejemplarRepository;

    public RevistaController(final RevistaService revistaService,
                             final EjemplarRepository ejemplarRepository) {
        this.revistaService = revistaService;
        this.ejemplarRepository = ejemplarRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("ejemplarValues", ejemplarRepository.findAll(Sort.by("idEjemplar"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Ejemplar::getIdEjemplar, Ejemplar::getIdEjemplar)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("revistas", revistaService.findAll());
        System.out.println("Entra en list");
        for(RevistaDTO r:revistaService.findAll()){
            System.out.println(r.toString());
        }
        return "menuEmpleado/menuEmpleado";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("revista") final RevistaDTO revistaDTO) {
        return "revista/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("revista") @Valid final RevistaDTO revistaDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "revista/add";
        }
        revistaService.create(revistaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("revista.create.success"));
        return "redirect:/revistas";
    }

    @GetMapping("/edit/{idRevista}")
    public String edit(@PathVariable(name = "idRevista") final Integer idRevista,
                       final Model model) {
        model.addAttribute("revista", revistaService.get(idRevista));
        return "revista/edit";
    }

    @PostMapping("/edit/{idRevista}")
    public String edit(@PathVariable(name = "idRevista") final Integer idRevista,
                       @ModelAttribute("revista") @Valid final RevistaDTO revistaDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "revista/edit";
        }
        revistaService.update(idRevista, revistaDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("revista.update.success"));
        return "redirect:/revistas";
    }

    @PostMapping("/delete/{idRevista}")
    public String delete(@PathVariable(name = "idRevista") final Integer idRevista,
                         final RedirectAttributes redirectAttributes) {
        final String referencedWarning = revistaService.getReferencedWarning(idRevista);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            revistaService.delete(idRevista);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("revista.delete.success"));
        }
        return "redirect:/revistas";
    }

}
