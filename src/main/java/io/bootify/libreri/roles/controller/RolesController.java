package io.bootify.libreri.roles.controller;

import io.bootify.libreri.roles.model.RolesDTO;
import io.bootify.libreri.roles.service.RolesService;
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
@RequestMapping("/roless")
public class RolesController {

    private final RolesService rolesService;

    public RolesController(final RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("roleses", rolesService.findAll());
        return "roles/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("roles") final RolesDTO rolesDTO) {
        return "roles/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("roles") @Valid final RolesDTO rolesDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "roles/add";
        }
        rolesService.create(rolesDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("roles.create.success"));
        return "redirect:/roless";
    }

    @GetMapping("/edit/{idRol}")
    public String edit(@PathVariable(name = "idRol") final Integer idRol, final Model model) {
        model.addAttribute("roles", rolesService.get(idRol));
        return "roles/edit";
    }

    @PostMapping("/edit/{idRol}")
    public String edit(@PathVariable(name = "idRol") final Integer idRol,
            @ModelAttribute("roles") @Valid final RolesDTO rolesDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "roles/edit";
        }
        rolesService.update(idRol, rolesDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("roles.update.success"));
        return "redirect:/roless";
    }

    @PostMapping("/delete/{idRol}")
    public String delete(@PathVariable(name = "idRol") final Integer idRol,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = rolesService.getReferencedWarning(idRol);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            rolesService.delete(idRol);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("roles.delete.success"));
        }
        return "redirect:/roless";
    }

}
