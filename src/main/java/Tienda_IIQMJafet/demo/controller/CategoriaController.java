/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package Tienda_IIQMJafet.demo.controller;

import Tienda_IIQMJafet.demo.domain.Categoria;
import Tienda_IIQMJafet.demo.service.CategoriaService;
import Tienda_IIQMJafet.demo.service.impl.FirebaseStorageServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
//  @Slf4j  Este sirve para poder encontrar los log de cuando estoy corriendolo y sirve como para debuggear o saber si se hizo el proceso
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    //Este metodo lo que hace es que le pide a categoriaService que le traiga la info de categoria y que lo pueda mostrar en pantalla
    @GetMapping("/listado")
    public String inicio(Model model) {
        //Es la variable lista que uso para traerme los datos de las categorias de la vara de datos y se pone "false" para que me traiga todos
        List<Categoria> categorias = categoriaService.getCategorias(false);
        //List<Categoria> categorias = categoriaService.getPorDescripicion("Teclados");   >> El como se aplica el metodo de DAO para encontrar por algo especifico String en este caso
        //nombre que le quise poner|| llama a la variable categoria de arriba
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
        return "/categoria/listado";
    }

    @GetMapping("/nuevo")
    public String categoriaNuevo(Categoria categoria) {
        return "/categoria/modifica";
    }

    @PostMapping("/guardar")
    public String categoriaGuardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            categoriaService.save(categoria);
            categoria.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "categoria",
                            categoria.getIdCategoria()));
        }
        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String categoriaEliminar(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "/categoria/modifica";
    }
}
