/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.casoexamen.controller;

import com.example.casoexamen.entity.Producto;
import com.example.casoexamen.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author BReyna
 */
@Controller
public class ProductoController {
    @Autowired
    private ProductoRepository productoRepository;
    
     @RequestMapping("/")
    public String mensaje(Model model){
        model.addAttribute("menaje", "Bienvenidos Thymeleaf");
        return "index";
    }
    @RequestMapping("/productos")
    public String prod(Model model){
        model.addAttribute("prods", productoRepository.findAll());
        return "prod";
    }
    @RequestMapping("/form")
    public String create(Model model) {
        return "add";
    }
    @RequestMapping("/add")
    public String guardar(@RequestParam String nombre, @RequestParam double precio, @RequestParam int stock, Model model) {
        Producto prod = new Producto();
        prod.setNombre(nombre);
        prod.setPrecio(precio);
        prod.setStock(stock);
        productoRepository.save(prod);
        return "redirect:/productos";
    }
    @RequestMapping("/del/{id}")
    public String delete(@PathVariable(value="id") Long id) {
        System.out.println("ID: "+id);
        Producto prod = productoRepository.findById(id).orElse(null);
        productoRepository.delete(prod);
        return "redirect:/productos";
    }
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable(value="id") Long id, Model model) {
        model.addAttribute("prod", productoRepository.findById(id).orElse(null));
        return "edit";
    }
    @RequestMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String nombre, @RequestParam double precio, @RequestParam int stock) {
        System.out.println(id+"/"+nombre+"/"+precio+"/"+stock);
        Producto prod = productoRepository.findById(id).orElse(null);
        prod.setNombre(nombre);
        prod.setPrecio(precio);
        prod.setStock(stock);
        productoRepository.save(prod);
        return "redirect:/productos";
    }
}
