package com.example.demo.conventers;

import com.example.demo.commands.WyplataCommand;
import com.example.demo.model.Wyplata;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class WyplataCommandToWyplata implements Converter<WyplataCommand, Wyplata> {

    @Synchronized
    @Nullable
    @Override
    public Wyplata convert(WyplataCommand source) {
        final Wyplata wplata=new Wyplata();
        wplata.setData(source.getData());
        wplata.setIlosc(source.getIlosc());

        return null;
    }
}
