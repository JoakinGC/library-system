package io.bootify.libreri.config;

import io.bootify.libreri.roles.domain.Roles;
import io.bootify.libreri.usuario.domain.Usuario;
import io.bootify.libreri.usuario.repos.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDatilsServiceImplet implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.getName(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario"));

        System.out.println(user.getRol());

        Set<Roles> roles = new HashSet<>();

        roles.add(user.getRol());

        Collection<? extends GrantedAuthority> authorities = roles.stream().
                map(
                        roles1 -> new SimpleGrantedAuthority("ROLE_".concat(roles1.getRol().toString()))
                ).collect(Collectors.toSet());

        return new User(
                user.getNombre(),
                user.getContresena().toString(),
                true,
                true,
                true,
                true,
                authorities
        );
    }
}
