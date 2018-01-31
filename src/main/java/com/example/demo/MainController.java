package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
