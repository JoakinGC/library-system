package io.bootify.libreri;

import io.bootify.libreri.fichado.domain.Fichado;
import io.bootify.libreri.fichado.model.FichadoDTO;
import io.bootify.libreri.fichado.service.FichadoService;
import io.bootify.libreri.usuario.model.UsuarioDTO;
import io.bootify.libreri.usuario.service.UsuarioService;
import io.bootify.libreri.util.NotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    private UsuarioService usuarioService;
    private FichadoService fichadoService;
    public HomeController(final UsuarioService usuarioService, final FichadoService fichoService){
        this.usuarioService = usuarioService;
        this.fichadoService = fichoService;
    }


    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/menuSupervisor")
    public String backMenuSupervisor(){
        return "menuSupervisor/menuSupervisor";
    }

    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session){
        System.out.println(session.getAttribute("fichado"));
        FichadoDTO fichado = (FichadoDTO) session.getAttribute("fichado");
        Integer id = (Integer) session.getAttribute("id");


        fichado.setHoraSalida(OffsetDateTime.now());
        Duration duracion = Duration.between(fichado.getHoraEntrada(), fichado.getHoraSalida());
        Double tiempo_total_dia = (double) duracion.toHours();
        fichado.setTiempoTotalDia(tiempo_total_dia);

        fichadoService.update(id, fichado);
        return "home/index";
    }

    @PostMapping("/prueba")
    public String processLogin(@RequestParam(required = false) String nombre, @RequestParam(required = false) String contresena, Model model, HttpSession session) {
        try {
            if (nombre == null) {
                model.addAttribute("error", "400");
                return "home/index";
            }
            if (contresena == null) {
                model.addAttribute("error", "401");
                return "home/index";
            }
            if (nombre.isEmpty()) {
                model.addAttribute("error", "400");
                return "home/index";
            }
            if (contresena.isEmpty()) {
                model.addAttribute("error", "401");
                return "home/index";
            }

            UsuarioDTO usuarioDTO = usuarioService.findByNameAndPassword(nombre, contresena);
            if (usuarioDTO == null) {
                model.addAttribute("error", "403");
                return "menuEmpleado/menuEmpleado";
            } else {
                if (usuarioDTO.getRol() == 1) {
                    return "menuSupervisor/menuSupervisor";
                } else if (usuarioDTO.getRol() == 2) {
                    FichadoDTO fichado = new FichadoDTO();
                    fichado.setHoraEntrada(OffsetDateTime.now());
                    fichado.setFechaFichaje(OffsetDateTime.now());
                    Integer id = fichadoService.create(fichado);
                    List<Integer> fichadoUserFichadoes = new ArrayList<>();
                    fichadoUserFichadoes.add(id);

                    usuarioDTO.setFichadoUserFichadoes(fichadoUserFichadoes);
                    usuarioService.update(usuarioDTO.getIdUser(),usuarioDTO);
                    session.setAttribute("fichado", fichado);
                    session.setAttribute("id", id);
                    return "menuSupervisor/menuSupervisor";
                } else if (usuarioDTO.getRol() == 3) {
                    FichadoDTO fichado = new FichadoDTO();
                    fichado.setHoraEntrada(OffsetDateTime.now());
                    fichado.setFechaFichaje(OffsetDateTime.now());
                    Integer id = fichadoService.create(fichado);
                    List<Integer> fichadoUserFichadoes = new ArrayList<>();
                    fichadoUserFichadoes.add(id);

                    usuarioDTO.setFichadoUserFichadoes(fichadoUserFichadoes);
                    usuarioService.update(usuarioDTO.getIdUser(),usuarioDTO);
                    return "menuEmpleado/menuEmpleado";
                }else{
                    return null;
                }
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
            model.addAttribute("error", "403");
            return "home/index";
        }

    }

}