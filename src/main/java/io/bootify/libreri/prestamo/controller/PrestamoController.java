package io.bootify.libreri.prestamo.controller;

import io.bootify.libreri.ejemplar.domain.Ejemplar;
import io.bootify.libreri.ejemplar.repos.EjemplarRepository;
import io.bootify.libreri.errors.EmptyParametrer;
import io.bootify.libreri.errors.ExceptionNoFoundPrestamo;
import io.bootify.libreri.libros.domain.Libros;
import io.bootify.libreri.libros.repos.LibrosRepository;
import io.bootify.libreri.libros.service.LibrosService;
import io.bootify.libreri.prestamo.domain.ETipos;
import io.bootify.libreri.prestamo.domain.Prestamo;
import io.bootify.libreri.prestamo.model.PrestamoDTO;
import io.bootify.libreri.prestamo.repos.PrestamoRepository;
import io.bootify.libreri.prestamo.service.PrestamoService;
import io.bootify.libreri.revista.domain.Revista;
import io.bootify.libreri.roles.domain.ERoles;
import io.bootify.libreri.socio.domain.Socio;
import io.bootify.libreri.socio.model.SocioDTO;
import io.bootify.libreri.socio.repos.SocioRepository;
import io.bootify.libreri.socio.service.SocioService;
import io.bootify.libreri.usuario.domain.Usuario;
import io.bootify.libreri.usuario.repos.UsuarioRepository;
import io.bootify.libreri.util.CustomCollectors;
import io.bootify.libreri.util.WebUtils;
import jakarta.validation.Valid;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yaml.snakeyaml.events.Event;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//control de excepciones globales y login
@Controller
@RequestMapping("/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;
    private final PrestamoRepository prestamoRepository;
    private final EjemplarRepository ejemplarRepository;
    private final UsuarioRepository usuarioRepository;
    private final SocioRepository socioRepository;
    private final SocioService socioService;
    private final LibrosService librosService;


    public PrestamoController(final PrestamoService prestamoService, PrestamoRepository prestamoRepository,
                              final EjemplarRepository ejemplarRepository,
                              final UsuarioRepository usuarioRepository,
                              final SocioRepository socioRepository,
                              final SocioService socioService, LibrosService librosService) {
        this.prestamoService = prestamoService;
        this.prestamoRepository = prestamoRepository;
        this.ejemplarRepository = ejemplarRepository;
        this.usuarioRepository = usuarioRepository;
        this.socioRepository = socioRepository;
        this.socioService = socioService;
        this.librosService = librosService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        List<Usuario> allUsuarios = usuarioRepository.findAll(Sort.by("idUser"));

        List<Usuario> usuariosConRolId3 = allUsuarios.stream()
                .filter(usuario -> usuario.getRol() != null && usuario.getRol().getIdRol() == 3)
                .collect(Collectors.toList());



        List<Ejemplar> allEjemplares = ejemplarRepository.findAll(Sort.by("idEjemplar"));


        List<Ejemplar> ejemplaresDisponibles = allEjemplares.stream()
                .filter(e -> !estaEnPrestamo(e)) // Implementa esta función
                .collect(Collectors.toList());


        List<Socio> allSocios = socioRepository.findAll(Sort.by("idSocio"));

        List<Socio> sociosDisponibles = allSocios.stream()
                .filter(s -> !tienePrestamos(s)) // Implementa esta función
                .collect(Collectors.toList());





        model.addAttribute("ejemplarValues", ejemplaresDisponibles
                .stream()
                .collect(CustomCollectors.toSortedMap(Ejemplar::getIdEjemplar, Ejemplar::getIdEjemplar)));
        model.addAttribute("socioValues", sociosDisponibles
                .stream()
                .collect(CustomCollectors.toSortedMap(Socio::getIdSocio, Socio::getIdSocio)));
        model.addAttribute("empleValues", usuariosConRolId3
                .stream()
                .collect(CustomCollectors.toSortedMap(Usuario::getIdUser, Usuario::getNombre)));
        model.addAttribute("tipoValues", Arrays.stream(ETipos.values())
                .filter(tipo -> tipo.equals(ETipos.LIBRO) || tipo.equals(ETipos.REVISTA))
                .map(Enum::toString)
                .collect(Collectors.toList()));
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("prestamoes", prestamoService.findAll());
        return "menuEmpleado/menuEmpleado";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("prestamo") final PrestamoDTO prestamoDTO,Model model) {
        return "prestamo/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
                      final BindingResult bindingResult, final RedirectAttributes redirectAttributes,
                      Model model) {


        if (bindingResult.hasErrors()) {
            return "prestamo/add";
        }


        if (prestamoDTO.getEjemplar() == null || prestamoDTO.getEjemplar().toString().isEmpty()) {
            model.addAttribute("errorFor", "vacioEjemplar");
            return "prestamo/add";
        }


        if (prestamoDTO.getTipo() == null) {
            model.addAttribute("errorFor", "vacioTipo");
            return "prestamo/add";
        }

        if (prestamoDTO.getEmple() == null || prestamoDTO.getEmple().toString().isEmpty()) {
            model.addAttribute("errorFor", "vacioEmpleado");
            return "prestamo/add";
        }

        if (prestamoDTO.getSocio() == null) {
            model.addAttribute("errorFor", "vacioSocio");
            return "prestamo/add";
        }


        OffsetDateTime fechaActual = OffsetDateTime.now();
        prestamoDTO.setFechaPrestamo(fechaActual);
        prestamoDTO.setFechaFin(fechaActual.plusDays(30));
        prestamoDTO.setEntregado(false);

        prestamoService.create(prestamoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.create.success"));
        return "redirect:/prestamos";
    }


    @GetMapping("/edit/{idPrestamo}")
    public String edit(@PathVariable(name = "idPrestamo") final Integer idPrestamo,
                       final Model model) throws ExceptionNoFoundPrestamo {
        model.addAttribute("prestamo", prestamoService.get(idPrestamo));
        return "prestamo/edit";
    }

    @PostMapping("/edit/{idPrestamo}")
    public String edit(@PathVariable(name = "idPrestamo") final Integer idPrestamo,
                       @ModelAttribute("prestamo") @Valid final PrestamoDTO prestamoDTO,
                       final BindingResult bindingResult, final RedirectAttributes redirectAttributes) throws ExceptionNoFoundPrestamo {
        if (bindingResult.hasErrors()) {
            return "prestamo/edit";
        }

        PrestamoDTO prestamoDTO2 = prestamoService.get(idPrestamo);

        prestamoDTO2.setFechaEntrega(OffsetDateTime.now());

        if(prestamoDTO2.getFechaEntrega().isAfter(prestamoDTO2.getFechaFin()) || prestamoDTO2.getFechaEntrega().isEqual(prestamoDTO2.getFechaFin())) {
            System.out.println("Solo si el socio no entrego el  prestamo en tiempo");
            socioService.incrementarMulta(prestamoDTO2.getSocio(),50);
        }
        prestamoDTO2.setEntregado(true);
        prestamoService.update(idPrestamo, prestamoDTO2);

        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("prestamo.update.success"));
        return "redirect:/prestamos";
    }


    @PostMapping("/buscar")
    public String buscarPorId(@RequestParam(required = false) final Integer id ,
                              final Model model) throws ExceptionNoFoundPrestamo {



        if(id == null || id.toString().isEmpty()){
            System.out.println(new EmptyParametrer("ID").getMessage());
            return "redirect:/prestamos";
        }

        PrestamoDTO prestamo = (prestamoService.get(id) !=null) ? prestamoService.get(id): null ;
        if (prestamo != null) {
            model.addAttribute("prestamoes", prestamo);

            if(prestamo.getFechaEntrega() != null){
                model.addAttribute("entregado","");
            }else if(prestamo.getFechaEntrega() == null){
                model.addAttribute("noEntregado","");
            }
        } else {
            model.addAttribute("mensaje", "No se encontró ningún préstamo con el ID proporcionado.");
        }

        return "prestamo/list";
    }



    @PostMapping("/buscarISBN")
    public String buscarPorISBN(@RequestParam(required = false) final Integer isbn, final Model model) throws ExceptionNoFoundPrestamo {

        if(isbn == null || isbn.toString().isEmpty()){
            System.out.println(new EmptyParametrer("ID").getMessage());
            return "redirect:/prestamos";
        }

        Prestamo prestamo = prestamoRepository.findByIsbnLibro(isbn) == null ?null: prestamoRepository.findByIsbnLibro(isbn) ;
        PrestamoDTO p = (prestamoService.get(prestamo.getIdPrestamo()) != null) ? prestamoService.get(prestamo.getIdPrestamo()): null;

        if (p != null || prestamo != null) {
            model.addAttribute("prestamoEncontrado", p);

            if(prestamo.getFechaEntrega() != null){
                model.addAttribute("entregado","");
            }else if(prestamo.getFechaEntrega() == null){
                model.addAttribute("noEntregado","");
            }
        } else {
            model.addAttribute("mensaje", "No se encontró ningún préstamo con el ISBN de libro proporcionado.");
        }

        return "prestamo/list";
    }

    private boolean estaEnPrestamo(Ejemplar ejemplar) {
        List<Prestamo> prestamos = prestamoRepository.findByEjemplar(ejemplar);
        return prestamos.stream().anyMatch(prestamo -> !prestamo.getEntregado());
    }

    private boolean tienePrestamos(Socio socio) {
        List<Prestamo> prestamos = prestamoRepository.findBySocio(socio);
        return prestamos.stream().anyMatch(prestamo -> !prestamo.getEntregado());
    }

}
