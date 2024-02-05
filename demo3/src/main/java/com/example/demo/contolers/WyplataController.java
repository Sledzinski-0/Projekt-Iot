package com.example.demo.contolers;

import com.example.demo.commands.WyplataCommand;
import com.example.demo.conventers.WyplataCommandToWyplata;
import com.example.demo.conventers.WyplatatoWyplataCommand;
import com.example.demo.model.Wyplata;
import com.example.demo.repo.WyplataRepo;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class WyplataController {
    private final WyplataRepo wyplataRepo;
    private final WyplataCommandToWyplata wyplataCommandToWyplata;
    private WyplatatoWyplataCommand wyplataCommand;

    public WyplataController(WyplataRepo wyplataRepo, WyplataCommandToWyplata wyplataCommandToWyplata, WyplatatoWyplataCommand wyplataCommand) {
        this.wyplataRepo = wyplataRepo;
        this.wyplataCommandToWyplata = wyplataCommandToWyplata;
        this.wyplataCommand = wyplataCommand;
    }

    @RequestMapping("/wyplata/list")
    public String getWplata(Model model) {
        model.addAttribute("wyplata", wyplataRepo.findAll());
        return "wyplata/list";
    }

    @GetMapping("/wyplata/new")
    public String newWyplata(Model model) {
        model.addAttribute("wyplata", new WyplataCommand());
        return "wyplata/addedit";
    }
    @PostMapping("/wyplata")
    public String saveOrUpdate(@ModelAttribute WyplataCommand command) {
        Wyplata detachedWyplata = wyplataCommandToWyplata.convert(command);

        if (detachedWyplata != null) {
            if (detachedWyplata.getId() != null) {
                // Jeśli wyplata ma istniejący identyfikator, to aktualizuj
                Optional<Wyplata> existingWyplata = wyplataRepo.findById(detachedWyplata.getId());
                if (existingWyplata.isPresent()) {
                    Wyplata existing = (Wyplata) existingWyplata.get();
                    existing.setIlosc(detachedWyplata.getIlosc());
                    existing.setData(detachedWyplata.getData());
                    wyplataRepo.save(existing);
                    return "redirect:/wyplata/" + existing.getId() + "/show";
                }
            } else {
                // Jeśli wplata nie ma identyfikatora, to dodaj nową
                Wyplata savedWyplata = wyplataRepo.save(detachedWyplata);
                return "redirect:/wyplata/" + savedWyplata.getId() + "/show";
            }
        }

        // Obsługa sytuacji, gdy detachedWplata jest nullem
        return "redirect:/wyplata";
    }



    @RequestMapping("/wyplata/{id}/edit")
    public String editWyplata(Model model, @PathVariable("id")Long id){

        model.addAttribute("wplata", wyplataCommand.convert(
                wyplataRepo.findById(id).get()
        ));
        return "wyplata/addedit";
    }
    @RequestMapping("/wyplata/{id}/show")
    public String getWyplataDetails(Model model,@PathVariable("id") Long id){
        model.addAttribute("wyplata",wyplataRepo.findById(id).get());
        return "wyplata/show";
    }



}
