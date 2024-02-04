package com.example.demo.conventers;


import com.example.demo.commands.WyplataCommand;
import com.example.demo.model.Wyplata;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

;

@Component
public class WyplatatoWyplataCommand implements Converter<Wyplata, WyplataCommand> {
    @Synchronized
    @Nullable
    @Override
    public WyplataCommand convert(Wyplata source) {
        final WyplataCommand command= new WyplataCommand();
        command.setId(source.getId());
        command.setIlosc(source.getIlosc());
        command.setData(source.getData());
        return command;
    }
}
