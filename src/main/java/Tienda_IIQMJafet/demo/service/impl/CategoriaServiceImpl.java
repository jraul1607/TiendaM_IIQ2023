
package Tienda_IIQMJafet.demo.service.impl;
import Tienda_IIQMJafet.demo.dao.CategoriaDao;
import Tienda_IIQMJafet.demo.domain.Categoria;
import Tienda_IIQMJafet.demo.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    @Autowired
    private CategoriaDao categoriaDao;
 
    @Override
    @Transactional(readOnly=true)
    public List<Categoria> getCategorias(boolean activos) {
      List<Categoria> lista = categoriaDao.findAll();
      
      if (activos) {
          lista.removeIf(x -> !x.isActivo());
      }
      
      return lista;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }
    
}
