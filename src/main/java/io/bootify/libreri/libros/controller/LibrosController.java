package io.bootify.libreri.libros.controller;

import io.bootify.libreri.autor.domain.Autor;
import io.bootify.libreri.autor.repos.AutorRepository;
import io.bootify.libreri.editorial.domain.Editorial;
import io.bootify.libreri.editorial.repos.EditorialRepository;
import io.bootify.libreri.editorial.service.EditorialService;
import io.bootify.libreri.genero.domain.Genero;
import io.bootify.libreri.genero.repos.GeneroRepository;
import io.bootify.libreri.libros.model.LibrosDTO;
import io.bootify.libreri.libros.service.LibrosService;
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
@RequestMapping("/libros")
public class LibrosController {

    private final LibrosService librosService;
    private final AutorRepository autorRepository;


    public LibrosController(final LibrosService librosService,
                            final AutorRepository autorRepository) {
        this.librosService = librosService;
        this.autorRepository = autorRepository;

    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("libroAutorAutorsValues", autorRepository.findAll(Sort.by("idAutor"))
                .stream()
                .collect(CustomCollectors.toSortedMap(Autor::getIdAutor, Autor::getNombre)));

    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("libroses", librosService.findAll());
        for(LibrosDTO l:librosService.findAll()){
            System.out.println(l.toString());
        }
        return "menuEmpleado/menuEmpleado";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("libros") final LibrosDTO librosDTO) {
        return "libros/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("libros") @Valid final LibrosDTO librosDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "libros/add";
        }
        librosService.create(librosDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("libros.create.success"));
        return "redirect:/libros";
    }

    @GetMapping("/edit/{isbn}")
    public String edit(@PathVariable(name = "isbn") final Integer isbn, final Model model) {
        model.addAttribute("libros", librosService.get(isbn));
        return "libros/edit";
    }

    @PostMapping("/edit/{isbn}")
    public String edit(@PathVariable(name = "isbn") final Integer isbn,
                       @ModelAttribute("libros") @Valid final LibrosDTO librosDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "libros/edit";
        }
        librosService.update(isbn, librosDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("libros.update.success"));
        return "redirect:/libros";
    }

    @PostMapping("/delete/{isbn}")
    public String delete(@PathVariable(name = "isbn") final Integer isbn,
                         final RedirectAttributes redirectAttributes) {
        final String referencedWarning = librosService.getReferencedWarning(isbn);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            librosService.delete(isbn);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("libros.delete.success"));
        }
        return "redirect:/libros";
    }

}
