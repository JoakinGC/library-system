package io.bootify.libreri.genero.controller;

import io.bootify.libreri.genero.model.GeneroDTO;
import io.bootify.libreri.genero.service.GeneroService;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.repos.LibrosRepository;
import io.bootify.libreri.revista.domain.Revista;
import io.bootify.libreri.revista.repos.RevistaRepository;
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
@RequestMapping("/generos")
public class GeneroController {

    private final GeneroService generoService;
    private final LibrosRepository librosRepository;
    private final RevistaRepository revistaRepository;

    public GeneroController(final GeneroService generoService,
            final LibrosRepository librosRepository, final RevistaRepository revistaRepository) {
        this.generoService = generoService;
        this.librosRepository = librosRepository;
        this.revistaRepository = revistaRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("libroGeneroLibrosesValues", librosRepository.findAll(Sort.by("isbn"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Libros::getIsbn, Libros::getTitulo)));
        model.addAttribute("generoRevistaRevistasValues", revistaRepository.findAll(Sort.by("idRevista"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Revista::getIdRevista, Revista::getTitulo)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("generoes", generoService.findAll());
        return "genero/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("genero") final GeneroDTO generoDTO) {
        return "genero/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("genero") @Valid final GeneroDTO generoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "genero/add";
        }
        generoService.create(generoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("genero.create.success"));
        return "redirect:/generos";
    }

    @GetMapping("/edit/{idGenero}")
    public String edit(@PathVariable(name = "idGenero") final Integer idGenero, final Model model) {
        model.addAttribute("genero", generoService.get(idGenero));
        return "genero/edit";
    }

    @PostMapping("/edit/{idGenero}")
    public String edit(@PathVariable(name = "idGenero") final Integer idGenero,
            @ModelAttribute("genero") @Valid final GeneroDTO generoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "genero/edit";
        }
        generoService.update(idGenero, generoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("genero.update.success"));
        return "redirect:/generos";
    }

    @PostMapping("/delete/{idGenero}")
    public String delete(@PathVariable(name = "idGenero") final Integer idGenero,
            final RedirectAttributes redirectAttributes) {
        generoService.delete(idGenero);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("genero.delete.success"));
        return "redirect:/generos";
    }

}
