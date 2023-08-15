package Tienda_IIQMJafet.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UsuarioDetailsService {

    /*se utiliza para correr objetos de tipo userdetails como esta en la "projectConfig" para los ROLES*/
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    
}
