package pl.sternik.dk.domi.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.dk.domi.entities.Znaczek;
import pl.sternik.dk.domi.repositories.ZnaczekAlreadyExistsException;
import pl.sternik.dk.domi.repositories.ZnaczkiRepository;
import pl.sternik.dk.domi.repositories.NoSuchZnaczekException;


@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private ZnaczkiRepository bazaDanych;

    @Override
    public List<Znaczek> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Znaczek> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Znaczek> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Znaczek> create(Znaczek znaczek) {
        try {
            return Optional.of(bazaDanych.create(znaczek));
        } catch (ZnaczekAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Znaczek> edit(Znaczek znaczek) {
        try {
            return Optional.of(bazaDanych.update(znaczek));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
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