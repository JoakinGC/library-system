package io.bootify.libreri.ejemplar.controller;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.ejemplar.model.EjemplarDTO;
import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.ejemplar.service.EjemplarService;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.repos.LibrosRepository;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.revista.domain.Revista;
import io.bootify.libreri.revista.repos.RevistaRepository;
import io.bootify.libreri.util.CustomCollectors;
import io.bootify.libreri.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/ejemplars")
public class EjemplarController {

    private final EjemplarService ejemplarService;
    private final LibrosRepository librosRepository;
    private final RevistaRepository revistaRepository;
    private final EjemplarRepository ejemplarRepository;

    public EjemplarController(final EjemplarService ejemplarService,
                              final LibrosRepository librosRepository, final RevistaRepository revistaRepository, EjemplarRepository ejemplarRepository) {
        this.ejemplarService = ejemplarService;
        this.librosRepository = librosRepository;
        this.revistaRepository = revistaRepository;
        this.ejemplarRepository = ejemplarRepository;
    }

    @Transactional(readOnly = true)
    @ModelAttribute
    public void prepareContext(final Model model) {
        // Obtener todos los libros y revistas
        List<Libros> allLibros = librosRepository.findAll(Sort.by("isbn"));
        List<Revista> allRevistas = revistaRepository.findAll(Sort.by("idRevista"));
        List<Ejemplar> allEjemplares = ejemplarRepository.findAll(Sort.by("idEjemplar"));

        ArrayList<Libros> librosDisponibles = new ArrayList<>();
        ArrayList<Revista> revistasDisponibles = new ArrayList<>();

        for (Libros libro : allLibros) {
            boolean hasEjemplar = allEjemplares.stream().anyMatch(ejemplar -> ejemplar.getLibro() != null && ejemplar.getLibro().equals(libro));
            if (!hasEjemplar) {
                librosDisponibles.add(libro);
            }
        }

        for (Revista revista : allRevistas) {
            boolean hasEjemplar = allEjemplares.stream().anyMatch(ejemplar -> ejemplar.getRevista() != null && ejemplar.getRevista().equals(revista));
            if (!hasEjemplar) {
                revistasDisponibles.add(revista);
            }
        }

        // Agregar los valores filtrados al modelo
        model.addAttribute("libroValues", librosDisponibles
                .stream()
                .collect(CustomCollectors.toSortedMap(Libros::getIsbn, Libros::getTitulo)));

        model.addAttribute("revistaValues", revistasDisponibles
                .stream()
                .collect(CustomCollectors.toSortedMap(Revista::getIdRevista, Revista::getTitulo)));
    }


    @GetMapping
    public String list(final Model model) {
        model.addAttribute("ejemplars", ejemplarService.findAll());
        return "menuEmpleado/menuEmpleado";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("ejemplar") final EjemplarDTO ejemplarDTO) {
        return "ejemplar/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("ejemplar") @Valid final EjemplarDTO ejemplarDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes,
                      Model model) {
        if (bindingResult.hasErrors()) {
            return "ejemplar/add";
        }

        if (ejemplarDTO.getLibro() != null && ejemplarDTO.getRevista() != null){
            model.addAttribute("error","libroYrevista");
            return "ejemplar/add";
        }

        if (ejemplarDTO.getLibro() == null && ejemplarDTO.getRevista() == null){
            model.addAttribute("error","nullos");
            return "ejemplar/add";
        }
        ejemplarService.create(ejemplarDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("ejemplar.create.success"));
        return "redirect:/ejemplars";
    }

    @GetMapping("/edit/{idEjemplar}")
    public String edit(@PathVariable(name = "idEjemplar") final Integer idEjemplar,
                       final Model model) {
        model.addAttribute("ejemplar", ejemplarService.get(idEjemplar));
        return "ejemplar/edit";
    }

    @PostMapping("/edit/{idEjemplar}")
    public String edit(@PathVariable(name = "idEjemplar") final Integer idEjemplar,
                       @ModelAttribute("ejemplar") @Valid final EjemplarDTO ejemplarDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes,
                       Model model){
        if (bindingResult.hasErrors()) {
            return "ejemplar/edit";
        }

        if (ejemplarDTO.getLibro() != null && ejemplarDTO.getRevista() != null){
            model.addAttribute("error","libroYrevista");
            return "ejemplar/edit";
        }

        if (ejemplarDTO.getLibro() == null && ejemplarDTO.getRevista() == null){
            model.addAttribute("error","nullos");
            return "ejemplar/edit";
        }

        ejemplarService.update(idEjemplar, ejemplarDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("ejemplar.update.success"));
        return "redirect:/ejemplars";
    }

    @PostMapping("/delete/{idEjemplar}")
    public String delete(@PathVariable(name = "idEjemplar") final Integer idEjemplar,
                         final RedirectAttributes redirectAttributes) {
        final String referencedWarning = ejemplarService.getReferencedWarning(idEjemplar);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            ejemplarService.delete(idEjemplar);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("ejemplar.delete.success"));
        }
        return "redirect:/ejemplars";
    }

}
