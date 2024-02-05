package com.example.demo.contolers;



import com.example.demo.commands.WplataCommand;
import com.example.demo.conventers.WplataCommandToWplata;
import com.example.demo.conventers.WplatatoWplataCommand;

import com.example.demo.model.Wplata;
import com.example.demo.repo.WplataRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class WplataController {

    private final WplataRepo wplataRepo;
    private final WplataCommandToWplata wplataCommandToWplata;
    private WplatatoWplataCommand wplataCommand;

    public WplataController(WplataRepo wplataRepo, WplataCommandToWplata wplataCommandToWplata, WplatatoWplataCommand wplataCommand) {
        this.wplataRepo = wplataRepo;
        this.wplataCommandToWplata = wplataCommandToWplata;
        this.wplataCommand = wplataCommand;
    }

    @RequestMapping("/wplata/list")
    public String getWplata(Model model) {
        model.addAttribute("wplata", wplataRepo.findAll());
        return "wplata/list";
    }

    @GetMapping("/wplata/new")
    public String newWplata(Model model) {
        model.addAttribute("wplata", new WplataCommand());
        return "wplata/addedit";
    }
   @PostMapping("/wplata")
    public String saveOrUpdate(@ModelAttribute WplataCommand command) {
        Wplata detachedWplata = wplataCommandToWplata.convert(command);

        if (detachedWplata != null) {
            if (detachedWplata.getId() != null) {
                // Jeśli wplata ma istniejący identyfikator, to aktualizuj
                Optional<Wplata> existingWplata = wplataRepo.findById(detachedWplata.getId());
                if (existingWplata.isPresent()) {
                    Wplata existing = (Wplata) existingWplata.get();
                    existing.setVolume(detachedWplata.getVolume());
                    existing.setPaymentDay(detachedWplata.getPaymentDay());
                    wplataRepo.save(existing);
                    return "redirect:/wplata/" + existing.getId() + "/show";
                }
            } else {
                // Jeśli wplata nie ma identyfikatora, to dodaj nową
                Wplata savedWplata = wplataRepo.save(detachedWplata);
                return "redirect:/wplata/" + savedWplata.getId() + "/show";
            }
        }

        // Obsługa sytuacji, gdy detachedWplata jest nullem
        return "redirect:/wplata";
    }



    @RequestMapping("/wplata/{id}/edit")
    public String editWplata(Model model, @PathVariable("id")Long id){

        model.addAttribute("wplata", wplataCommand.convert(
                wplataRepo.findById(id).get()
        ));
        return "wplata/addedit";
    }


    @RequestMapping("/wplata/{id}/show")
    public String getWplataDetails(Model model,@PathVariable("id") Long id){
        model.addAttribute("wplata",wplataRepo.findById(id).get());
        return "wplata/show";
    }



}
