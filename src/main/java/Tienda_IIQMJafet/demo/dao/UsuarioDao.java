
package Tienda_IIQMJafet.demo.dao;

import Tienda_IIQMJafet.demo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao extends JpaRepository<Usuario, Long>{

    Usuario findByUsername(String username);

}
