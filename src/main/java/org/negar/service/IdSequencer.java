package org.negar.service;

public interface IdSequencer {
    int nextId();
    void clear();
}