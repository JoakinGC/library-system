package io.bootify.libreri.autor.controller;

import io.bootify.libreri.autor.model.AutorDTO;
import io.bootify.libreri.autor.service.AutorService;
import io.bootify.libreri.util.WebUtils;
import jakarta.validation.Valid;
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
@RequestMapping("/autors")
public class AutorController {

    private final AutorService autorService;

    public AutorController(final AutorService autorService) {
        this.autorService = autorService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("autors", autorService.findAll());
        return "autor/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("autor") final AutorDTO autorDTO) {
        return "autor/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("autor") @Valid final AutorDTO autorDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "autor/add";
        }
        autorService.create(autorDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("autor.create.success"));
        return "redirect:/autors";
    }

    @GetMapping("/edit/{idAutor}")
    public String edit(@PathVariable(name = "idAutor") final Integer idAutor, final Model model) {
        model.addAttribute("autor", autorService.get(idAutor));
        return "autor/edit";
    }

    @PostMapping("/edit/{idAutor}")
    public String edit(@PathVariable(name = "idAutor") final Integer idAutor,
            @ModelAttribute("autor") @Valid final AutorDTO autorDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "autor/edit";
        }
        autorService.update(idAutor, autorDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("autor.update.success"));
        return "redirect:/autors";
    }

    @PostMapping("/delete/{idAutor}")
    public String delete(@PathVariable(name = "idAutor") final Integer idAutor,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = autorService.getReferencedWarning(idAutor);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            autorService.delete(idAutor);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("autor.delete.success"));
        }
        return "redirect:/autors";
    }

}
