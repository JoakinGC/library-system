package io.bootify.libreri.fichado.controller;

import io.bootify.libreri.fichado.model.FichadoDTO;
import io.bootify.libreri.fichado.service.FichadoService;
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
@RequestMapping("/fichados")
public class FichadoController {

    private final FichadoService fichadoService;

    public FichadoController(final FichadoService fichadoService) {
        this.fichadoService = fichadoService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("fichadoes", fichadoService.findAll());
        return "fichado/list";
    }



    @GetMapping("/add")
    public String add(@ModelAttribute("fichado") final FichadoDTO fichadoDTO) {
        return "fichado/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("fichado") @Valid final FichadoDTO fichadoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "fichado/add";
        }
        fichadoService.create(fichadoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("fichado.create.success"));
        return "redirect:/fichados";
    }

    @GetMapping("/edit/{idFichado}")
    public String edit(@PathVariable(name = "idFichado") final Integer idFichado,
            final Model model) {
        model.addAttribute("fichado", fichadoService.get(idFichado));
        return "fichado/edit";
    }

    @PostMapping("/edit/{idFichado}")
    public String edit(@PathVariable(name = "idFichado") final Integer idFichado,
            @ModelAttribute("fichado") @Valid final FichadoDTO fichadoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "fichado/edit";
        }
        fichadoService.update(idFichado, fichadoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("fichado.update.success"));
        return "redirect:/fichados";
    }

    @PostMapping("/delete/{idFichado}")
    public String delete(@PathVariable(name = "idFichado") final Integer idFichado,
            final RedirectAttributes redirectAttributes) {
        final String referencedWarning = fichadoService.getReferencedWarning(idFichado);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            fichadoService.delete(idFichado);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("fichado.delete.success"));
        }
        return "redirect:/fichados";
    }


    @GetMapping("/closeSession/{idUser}")
    public String closeSession(Model model,
                               Integer idUser){


        //Necesito mi usario actual conectado y actulizar el mismo fichado y hacer el calculo de

        //fichadoService.update();


        return  "/";
    }
}
