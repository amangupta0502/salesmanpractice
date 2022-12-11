package com.example.salesmanpractice.web;

import com.example.salesmanpractice.entities.Salesman;
import com.example.salesmanpractice.repositories.SalesmanRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes({"a","e"})
@Controller
@AllArgsConstructor
public class SalesmanController {

    @Autowired
    private SalesmanRepository salesmanRepository;

    static int num = 0;

    @GetMapping(path = "/index")
    public String salesman(Model model){

        List<Salesman> salesman = salesmanRepository.findAll();
        model.addAttribute("listSalesman", salesman);
        return "salesman" ;
    }

    @GetMapping(path = "/index")
    public String salesman(Model model, @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        List<Salesman> salesmen;
        if (keyword.isEmpty()) {
            salesmen = salesmanRepository.findAll();
        } else {
            long key = Long.parseLong(keyword);
            salesmen = salesmanRepository.findSalesmanById(key);
        }
        model.addAttribute("listSalesman", salesmen);
        return "salesman";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        salesmanRepository.deleteById(id);

        return "redirect:/index";
    }

    @GetMapping("/formSalesman")
    public String formSalesman(Model model) {
        model.addAttribute("salesman", new Salesman());
        return "formSalesman";
    }

    @PostMapping(path = "/save")
    public String save(Model model, Salesman salesman, BindingResult bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "formSalesman";
        } else {
            salesmanRepository.save(salesman);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";
        }
    }

    @GetMapping("/editSalesman")
    public String editSalesman(Model model, Long id, HttpSession session){
        num = 2;
        session.setAttribute("info", 0);
        Salesman salesman = salesmanRepository.findById(id).orElse(null);
        if(salesman ==null) throw new RuntimeException("Patient does not exist");
        model.addAttribute("salesman", salesman);
        return "editSalesman";
    }

    @GetMapping(path = "/")
    public String salesman2(Model model, ModelMap mm, @RequestParam(name = "keyword", defaultValue = "") String keyword,
                            HttpSession session) {
        List<Salesman> salesmen;
        if (keyword.isEmpty()) {
            salesmen = salesmanRepository.findAll();
        } else {

            mm.put("e",0);
            mm.put("a",0);
            long key = Long.parseLong(keyword);
            salesmen = salesmanRepository.findSalesmanById(key);
        }
        model.addAttribute("listSalesman", salesmen);
        return "salesman";
    }
}