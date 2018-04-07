package pl.sternik.dk.domi.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.dk.domi.entities.Znaczek;
import pl.sternik.dk.domi.repositories.ZnaczekAlreadyExistsException;
import pl.sternik.dk.domi.repositories.ZnaczkiRepository;
import pl.sternik.dk.domi.repositories.NoSuchZnaczekException;
import pl.sternik.dk.domi.entities.Status;

import java.util.stream.Collectors;

@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private ZnaczkiRepository ProstaBazaDanych;

    @Override
    public List<Znaczek> findAll() {
        return ProstaBazaDanych.findAll();
    }

    @Override
    public List<Znaczek> findAllToSell() {
        return ProstaBazaDanych.findAll().stream()
        .filter(p -> Objects.equals(p.getStatus(), Status.DO_SPRZEDANIA))
        .collect(Collectors.toList());

    }

    @Override
    public Optional<Znaczek> findById(Long id) {
        try {
            return Optional.of(ProstaBazaDanych.readById(id));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Znaczek> create(Znaczek znaczek) {
        try {
            return Optional.of(ProstaBazaDanych.create(znaczek));
        } catch (ZnaczekAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Znaczek> edit(Znaczek znaczek) {
        try {
            return Optional.of(ProstaBazaDanych.update(znaczek));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            ProstaBazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchZnaczekException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Znaczek> findLatest3() {
        //TODO:

        return Collections.emptyList();
    }

}