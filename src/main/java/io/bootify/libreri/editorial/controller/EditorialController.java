package io.bootify.libreri.editorial.controller;

import io.bootify.libreri.editorial.model.EditorialDTO;
import io.bootify.libreri.editorial.service.EditorialService;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.repos.LibrosRepository;
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
@RequestMapping("/editorials")
public class EditorialController {

    private final EditorialService editorialService;
    private final LibrosRepository librosRepository;

    public EditorialController(final EditorialService editorialService,
            final LibrosRepository librosRepository) {
        this.editorialService = editorialService;
        this.librosRepository = librosRepository;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("libroEditorialLibrosesValues", librosRepository.findAll(Sort.by("isbn"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Libros::getIsbn, Libros::getTitulo)));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("editorials", editorialService.findAll());
        return "editorial/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("editorial") final EditorialDTO editorialDTO) {
        return "editorial/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("editorial") @Valid final EditorialDTO editorialDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "editorial/add";
        }
        editorialService.create(editorialDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("editorial.create.success"));
        return "redirect:/editorials";
    }

    @GetMapping("/edit/{idEditorial}")
    public String edit(@PathVariable(name = "idEditorial") final Integer idEditorial,
            final Model model) {
        model.addAttribute("editorial", editorialService.get(idEditorial));
        return "editorial/edit";
    }

    @PostMapping("/edit/{idEditorial}")
    public String edit(@PathVariable(name = "idEditorial") final Integer idEditorial,
            @ModelAttribute("editorial") @Valid final EditorialDTO editorialDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "editorial/edit";
        }
        editorialService.update(idEditorial, editorialDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("editorial.update.success"));
        return "redirect:/editorials";
    }

    @PostMapping("/delete/{idEditorial}")
    public String delete(@PathVariable(name = "idEditorial") final Integer idEditorial,
            final RedirectAttributes redirectAttributes) {
        editorialService.delete(idEditorial);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("editorial.delete.success"));
        return "redirect:/editorials";
    }

}
