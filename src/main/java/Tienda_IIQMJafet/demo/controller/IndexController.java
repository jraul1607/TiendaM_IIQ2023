
package Tienda_IIQMJafet.demo.controller;

import Tienda_IIQMJafet.demo.service.ProductoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    
    @Autowired
    ProductoService productoService;
    
    @RequestMapping("/")
    public String page(Model model,HttpSession session) {   
        var productos = productoService.getProductos(true);
        model.addAttribute("productos",productos);
        return "index";
    }
    
}
