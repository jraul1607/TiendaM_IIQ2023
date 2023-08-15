
package Tienda_IIQMJafet.demo.service.impl;


import Tienda_IIQMJafet.demo.dao.UsuarioDao;
import Tienda_IIQMJafet.demo.domain.Rol;
import Tienda_IIQMJafet.demo.domain.Usuario;
import Tienda_IIQMJafet.demo.service.UsuarioDetailsService;
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
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {

    /*Este es de la interfaz de service*//*Este es de los "users* en "ProjectoConfig"*/

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar en la BD el usuario
        Usuario usuario = usuarioDao.findByUsername(username);

        if (username == null) {
            throw new UsernameNotFoundException("El usuario" + username + "no existe");
        }
        
        //Variables de Sesion 
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());
        
        //Extraer los roles 
        var roles = new ArrayList<GrantedAuthority>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
                    
        }
        
        return new User (usuario.getUsername(),usuario.getPassword(),roles);
        
    }
    

}
