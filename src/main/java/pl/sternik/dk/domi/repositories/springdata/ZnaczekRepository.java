package pl.sternik.dk.domi.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.sternik.dk.domi.entities.Znaczek;
import pl.sternik.dk.domi.repositories.ZnaczkiRepository;


@Repository
public interface ZnaczekRepository
        extends JpaRepository<Znaczek, Long>{
        public Znaczek findByNumerKatalogowy(Long id);
        }