package org.negar.service;

import org.springframework.stereotype.Component;

@Component
public class IdSequencerimpl implements IdSequencer {
    private int number = 0;

    @Override
    public int nextId() {
        return ++number;
    }

    @Override
    public void clear() {
        number = 0;
    }
}