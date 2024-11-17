package com.github.vvsslova.controllers;

import com.github.vvsslova.dao.PeopleDAO;
import com.github.vvsslova.models.Book;
import com.github.vvsslova.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO peopleDAO;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }

    @GetMapping()
    public String getAllPeople(Model model) {
        model.addAttribute("people", peopleDAO.getAllPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleDAO.show(id).get());
        return "people/show-person";
    }

    @GetMapping("/{id}/libraryCard")
    public String showPersonCard(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleDAO.show(id).get());
        model.addAttribute("books", peopleDAO.getPersonBooks(id));
        return "people/show-person-card";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new-person";
    }

    @PostMapping("/addNewPerson")
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "people/new-person";
        }
        peopleDAO.addNewPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleDAO.show(id).get());
        return "people/edit-person";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "people/edit-person";
        }
        peopleDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleDAO.delete(id);
        return "redirect:/people";
    }
}
