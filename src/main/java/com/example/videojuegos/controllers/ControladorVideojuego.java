package com.example.videojuegos.controllers;

import com.example.videojuegos.entities.Videojuego;
import com.example.videojuegos.services.ServicioCategoria;
import com.example.videojuegos.services.ServicioEstudio;
import com.example.videojuegos.services.ServicioVideojuego;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ControladorVideojuego {

    @Autowired
    private ServicioVideojuego svcVideojuego;

    @Autowired
    private ServicioCategoria svcCategoria;

    @Autowired
    private ServicioEstudio svcEstudio;

    @GetMapping("/inicio")
    public String inicio(Model model){
        try{
            List<Videojuego> videojuegos = this.svcVideojuego.findAllByActivo();
            model.addAttribute("videojuegos", videojuegos);

            return "views/inicio";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/detalle/{id}")
    public String detalleVideojuego(Model model, @PathVariable("id") long id){
        try{
            Videojuego videojuego = this.svcVideojuego.findByIdAndActivo(id);
            model.addAttribute("videojuego", videojuego);

            return "views/detalle";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/busqueda")
    public String busquedaVideojuego(Model model, @RequestParam(value = "query", required = false)String q){
        try{
            List<Videojuego> videojuegos = this.svcVideojuego.findByTitle(q);
            model.addAttribute("videojuegos", videojuegos);

            return "views/busqueda";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/crud")
    public String crudVideojuego(Model model){
        try{
            List<Videojuego> videojuegos = this.svcVideojuego.findAll();
            model.addAttribute("videojuegos", videojuegos);

            return"views/crud";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/formulario/videojuego/{id}")
    public String formularioVideojuego(Model model, @PathVariable("id")long id){
        try{
            model.addAttribute("categorias", this.svcCategoria.findAll());
            model.addAttribute("estudios", this.svcEstudio.findAll());
            if(id == 0){
                model.addAttribute("videojuego", new Videojuego());
            }else{
                model.addAttribute("videojuego", this.svcVideojuego.findById(id));
            }
            return"views/formulario/videojuego";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/formulario/videojuego/{id}")
    public String guardarVideojuego(@ModelAttribute("videojuego") Videojuego videojuego, Model model, @PathVariable("id")long id){
        try{
            if(id == 0){
                this.svcVideojuego.saveOne(videojuego);
            }else{
                this.svcVideojuego.updateOne(videojuego, id);
            }
            return"redirect:/crud";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/eliminar/videojuego/{id}")
    public String eliminarVideojuego(Model model, @PathVariable("id")long id){
        try{
            model.addAttribute("videojuego", this.svcVideojuego.findById(id));
            return"views/formulario/eliminar";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/eliminar/videojuego/{id}")
    public String desactivarVideojuego(Model model, @PathVariable("id")long id){
        try{
            this.svcVideojuego.deleteById(id);
            return"redirect:/crud";
        }catch (Exception e){
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

}
