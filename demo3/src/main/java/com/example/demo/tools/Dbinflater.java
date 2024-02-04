package com.example.demo.tools;


import com.example.demo.model.Wplata;

import com.example.demo.model.Wyplata;
import com.example.demo.repo.WplataRepo;
import com.example.demo.repo.WyplataRepo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Dbinflater implements ApplicationListener<ContextRefreshedEvent> {
    private WyplataRepo wyplataRepo;
    private WplataRepo wplataRepo;

    public Dbinflater(WyplataRepo wyplataRepo, WplataRepo wplataRepo) {
        this.wyplataRepo = wyplataRepo;
        this.wplataRepo = wplataRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
    ;
    private void initData(){
        Wplata johnny =new Wplata("100","10.05.2023");
        Wplata dave =new Wplata("1000","10.05.2023");
        Wplata Monkey =new Wplata("50","10.05.2023");


        Wyplata aqua= new Wyplata("100","10.05.2023");
        Wyplata wood= new Wyplata("500","21.01.2023");
        Wyplata man= new Wyplata("150","5.05.2023");

        johnny.getWyplatas().add(aqua);
        dave.getWyplatas().add(wood);
        Monkey.getWyplatas().add(man);
        wplataRepo.save(johnny);
        wplataRepo.save(dave);
        wplataRepo.save(Monkey);


        aqua.getWplatas().add(johnny);
        wood.getWplatas().add(dave);
        man.getWplatas().add(Monkey);

        wyplataRepo.save(aqua);
        wyplataRepo.save(wood);
        wyplataRepo.save(man);


    }
}
