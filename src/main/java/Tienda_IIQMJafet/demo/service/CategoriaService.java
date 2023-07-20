
package Tienda_IIQMJafet.demo.service;
import Tienda_IIQMJafet.demo.domain.Categoria;
import java.util.List;


public interface CategoriaService {
    
    public List<Categoria> getCategorias(boolean activos);
    
    
}
