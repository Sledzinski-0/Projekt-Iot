package com.example.demo.conventers;


import com.example.demo.commands.WplataCommand;
;
import com.example.demo.model.Wplata;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class WplatatoWplataCommand implements Converter<Wplata, WplataCommand> {
    @Synchronized
    @Nullable
    @Override
    public WplataCommand convert(Wplata source) {
        final WplataCommand command= new WplataCommand();
        command.setId(source.getId());
        command.setPaymentDay(source.getPaymentDay());
        command.setVolume(source.getVolume());
        return command;
    }
}
