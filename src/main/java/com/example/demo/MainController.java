package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    AddressRepository addressRepository;

    @RequestMapping("/")
    public String listAddresses(Model model){
        model.addAttribute("addresses", addressRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String addressForm(Model model){
        model.addAttribute("addressBook", new AddressBook());
        return "addressform";
    }

    @PostMapping("/process")
    public String processForm(@Valid AddressBook addressBook, BindingResult result){
        if (result.hasErrors()){
            return "addressform";
        }
        addressRepository.save(addressBook);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchAddress(@RequestParam("searchName") String searchName, Model model){

        model.addAttribute("addresses",addressRepository.findByLastname(searchName));
        return "list";
    }

    @RequestMapping("/detail/{id}")
    public String showAddress(@PathVariable("id") long id, Model model){
        model.addAttribute("addressBook", addressRepository.findOne(id));
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateAddress(@PathVariable("id") long id, Model model){
        model.addAttribute("addressBook", addressRepository.findOne(id));
        return "addressform";
    }

    @RequestMapping("/delete/{id}")
    public String delAddress(@PathVariable("id") long id){
        addressRepository.delete(id);
        return "redirect:/";
    }
}
