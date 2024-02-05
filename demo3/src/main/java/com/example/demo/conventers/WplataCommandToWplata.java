package com.example.demo.conventers;

import com.example.demo.commands.WplataCommand;
import com.example.demo.model.Wplata;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class WplataCommandToWplata implements Converter<WplataCommand, Wplata> {

    @Synchronized
    @Nullable
    @Override
    public Wplata convert(WplataCommand source) {
        final Wplata wplata=new Wplata();
        wplata.setId(source.getId());
        wplata.setPaymentDay(source.getPaymentDay());
        wplata.setVolume(source.getVolume());

        return wplata;
    }
}
