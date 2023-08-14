package Tienda_IIQMJafet.demo.service.impl;

import Tienda_IIQMJafet.demo.dao.UsuarioDao;
import Tienda_IIQMJafet.demo.domain.Rol;
import Tienda_IIQMJafet.demo.domain.Usuario;
import Tienda_IIQMJafet.demo.service.UsuarioDetailService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService{

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar en Base de datos mi usuario
        Usuario usuario = usuarioDao.findByUsername(username);

        if(usuario==null) {
            throw new UsernameNotFoundException("El usuario" +username+ "no existe");
        }

        //Variables de sesion
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());

        var roles = new ArrayList<GrantedAuthority>();

        //for each
        for(Rol role : usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(role.getNombre()));
        }


        //Extraer los roles
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }

}
