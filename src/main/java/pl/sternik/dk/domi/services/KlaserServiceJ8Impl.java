package pl.sternik.dk.domi.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import pl.sternik.dk.domi.entities.Status;
import pl.sternik.dk.domi.entities.Znaczek;
import pl.sternik.dk.domi.repositories.ZnaczekAlreadyExistsException;
import pl.sternik.dk.domi.repositories.ZnaczkiRepository;
import pl.sternik.dk.domi.repositories.NoSuchZnaczekException;


@Service
@Primary
public class KlaserServiceJ8Impl implements KlaserService {

    @Autowired
    @Qualifier("lista")
    private ZnaczkiRepository znaczki;

    @Override
    public List<Znaczek> findAll() {
        return znaczki.findAll();
    }

    @Override
    public List<Znaczek> findLatest3() {
        return znaczki.findAll().stream().sorted((a, b) -> b.getRokProdukcji().compareTo(a.getRokProdukcji())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Znaczek> findById(Long id) {
        try {
            return Optional.of(znaczki.readById(id));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Znaczek> create(Znaczek znaczek) {
        try {
            return Optional.of(znaczki.create(znaczek));
        } catch (ZnaczekAlreadyExistsException e) {
            try {
                return Optional.of(znaczki.readById(znaczek.getNumerKatalogowy()));
            } catch (NoSuchZnaczekException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Znaczek> edit(Znaczek znaczek) {
        try {
            return Optional.of(znaczki.update(znaczek));
        } catch (NoSuchZnaczekException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            znaczki.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchZnaczekException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Znaczek> findAllToSell() {
        return znaczki.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DO_SPRZEDANIA))
                .collect(Collectors.toList());
    }
}
