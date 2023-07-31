
package Tienda_IIQMJafet.demo.dao;

import Tienda_IIQMJafet.demo.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoDao extends JpaRepository<Producto, Long>{
    
}
