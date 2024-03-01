package io.bootify.libreri.util;

<<<<<<< HEAD
=======
import io.bootify.libreri.roles.domain.ERoles;
>>>>>>> Joaquin-System
import io.bootify.libreri.roles.model.RolesDTO;
import io.bootify.libreri.roles.service.RolesService;
import io.bootify.libreri.usuario.model.UsuarioDTO;
import io.bootify.libreri.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;


@Component
public class Inicializer implements CommandLineRunner {

    private RolesService serRol;
    private UsuarioService serUsuaio;

    @Autowired
    public Inicializer(final RolesService serRol, final UsuarioService r) {
        this.serRol = serRol;
        this.serUsuaio = r;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            RolesDTO rol1 = serRol.get(1);
            if (rol1 == null) {
                rol1 = new RolesDTO();
                rol1.setIdRol(1);
<<<<<<< HEAD
                rol1.setRol("admin");
=======
                rol1.setRol(ERoles.ADMIN);
>>>>>>> Joaquin-System
                serRol.create(rol1);
            }

            RolesDTO rol2 = serRol.get(2);
            if (rol2 == null) {
                rol2 = new RolesDTO();
                rol2.setIdRol(2);
<<<<<<< HEAD
                rol2.setRol("supervisor");
=======
                rol2.setRol(ERoles.SUPERVISOR);
>>>>>>> Joaquin-System
                serRol.create(rol2);
            }

            RolesDTO rol3 = serRol.get(3);
            if (rol3 == null) {
                rol3 = new RolesDTO();
                rol3.setIdRol(3);
<<<<<<< HEAD
                rol3.setRol("empleado");
=======
                rol3.setRol(ERoles.EMPLEADO);
>>>>>>> Joaquin-System
                serRol.create(rol3);
            }

            UsuarioDTO usu1 = serUsuaio.get(1);
            if(usu1 ==  null){
                UsuarioDTO admin = new UsuarioDTO();
                admin.setIdUser(1);
                admin.setNombre("Joaquin");
                admin.setApellido("Contreras");
                admin.setDiaAlta(OffsetDateTime.now());
                admin.setContresena("pepe1");
                admin.setRol(1);
                serUsuaio.create(admin);
            }

            UsuarioDTO usu2 = serUsuaio.get(2);
            if(usu2 ==  null){
                UsuarioDTO supervisor = new UsuarioDTO();
                supervisor.setIdUser(2);
                supervisor.setNombre("Rancio");
                supervisor.setApellido("El Manzanó");
                supervisor.setIdSuper(1);
                supervisor.setDiaAlta(OffsetDateTime.now());
                supervisor.setContresena("pepe2");
                supervisor.setRol(2);
                serUsuaio.create(supervisor);
            }

            UsuarioDTO usu3 = serUsuaio.get(3);
            if(usu3 ==  null){
                UsuarioDTO empleado = new UsuarioDTO();
                empleado.setIdUser(3);
                empleado.setNombre("Ruben");
                empleado.setApellido("En Demoniado");
                empleado.setDiaAlta(OffsetDateTime.now());
                empleado.setContresena("pepe3");
                empleado.setRol(3);
                serUsuaio.create(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


<<<<<<< HEAD
}
=======
}
>>>>>>> Joaquin-System
