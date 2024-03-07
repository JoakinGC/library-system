package io.bootify.libreri;


import io.bootify.libreri.fichado.domain.Fichado;


import io.bootify.libreri.fichado.model.FichadoDTO;
import io.bootify.libreri.fichado.service.FichadoService;
import io.bootify.libreri.usuario.model.UsuarioDTO;
import io.bootify.libreri.usuario.service.UsuarioService;
import io.bootify.libreri.util.NotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
public class HomeController {

    private UsuarioService usuarioService;
    private FichadoService fichadoService;



    public HomeController(final UsuarioService usuarioService, final FichadoService fichoService){
        this.usuarioService = usuarioService;
        this.fichadoService = fichoService;
    }

    @PreAuthorize("hasRole('PUBLIC')")
    @GetMapping("/")
    public String index() {
        return "home/index";
    }



    @GetMapping("/menuSupervisor")
    public String backMenuSupervisor(){
        return "menuSupervisor/menuSupervisor";
    }

    @GetMapping("/menuEmpleado")
    public String backMenuEmpleado(Model model){
        model.addAttribute("main", "");
        return "menuEmpleado/menuEmpleado";
    }

    @PreAuthorize("hasRole('PUBLIC')")
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

    @PreAuthorize("hasRole('PUBLIC')")
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

                    usuarioDTO.getFichadoUserFichadoes().add(id);
                    usuarioService.update(usuarioDTO.getIdUser(),usuarioDTO);

                    if (usuarioDTO.getFichadoUserFichadoes() != null) {
                        fichado.setFichadoUserUsuarios(Set.of(usuarioDTO));
                    }
                    fichadoService.update(id, fichado);

                    session.setAttribute("fichado", fichado);
                    session.setAttribute("id", id);

                    model.addAttribute("id",usuarioDTO.getIdUser());
                    model.addAttribute("idSuperivor",usuarioDTO.getIdSuper());

                    return "menuSupervisor/menuSupervisor";
                } else if (usuarioDTO.getRol() == 3) {
                    FichadoDTO fichado = new FichadoDTO();
                    fichado.setHoraEntrada(OffsetDateTime.now());
                    fichado.setFechaFichaje(OffsetDateTime.now());
                    Integer id = fichadoService.create(fichado);


                    usuarioDTO.getFichadoUserFichadoes().add(id);
                    usuarioService.update(usuarioDTO.getIdUser(),usuarioDTO);

                    if (usuarioDTO.getFichadoUserFichadoes() != null) {
                        fichado.setFichadoUserUsuarios(Set.of(usuarioDTO));
                    }
                    fichadoService.update(id, fichado);


                    session.setAttribute("fichado", fichado);
                    session.setAttribute("id", id);

                    String aux = usuarioDTO.getIdUser().toString();
                    model.addAttribute("id", usuarioDTO.getNombre());
                    model.addAttribute("main", "");



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


    @GetMapping("/documentation")
    public String getDocumentation(){
        return "/documentation/index";
    }
}
