package pl.sternik.dk.domi.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.sternik.dk.domi.entities.Znaczek;
import pl.sternik.dk.domi.entities.Status;

@Service
@Qualifier("lista")
public class ZnaczkiRepositoryJ8Impl implements ZnaczkiRepository {

    private List<Znaczek> znaczki = new ArrayList<Znaczek>() {
        private static final long serialVersionUID = 1L;
        {
            add(Znaczek.produceZnaczek(1L, "Polska", "znaczek z kwiatkami", new Date(), "bardzo dobry", new BigDecimal("1.55"),
                    Status.NOWY));
           // add(Znaczek.produceZnaczek(2L, "Polska", "drugi znaczek", new Date(), "bardzo dobry", new BigDecimal("1.55"),
           //         Status.DO_SPRZEDANIA));
           // add(Znaczek.produceZnaczek(3L, "Polska", "trzeci znaczek" ,new Date(), "bardzo dobry", new BigDecimal("1.55"), Status.DUBLET));
           // add(Znaczek.produceZnaczek(4L, "Polska", "czwarty znaczek" ,new Date(), "bardzo dobry", new BigDecimal("1.55"),
            //        Status.DO_SPRZEDANIA));
           // add(Znaczek.produceZnaczek(5L, "Polska", "piąty znaczek" ,new Date(), "bardzo dobry", new BigDecimal("1.55"), Status.NOWY));
           // add(Znaczek.produceZnaczek(6L, "Polska", "szósty znaczek" ,new Date(), "bardzo dobry", new BigDecimal("1.55"), Status.NOWY));
        }
    };

    @Override
    public List<Znaczek> findAll() {
        return this.znaczki;
    }

    @Override
    public Znaczek readById(Long id) throws NoSuchZnaczekException {
        return this.znaczki.stream().filter(p -> Objects.equals(p.getNumerKatalogowy(), id)).findFirst()
                .orElseThrow(NoSuchZnaczekException::new);
    }

    @Override
    public Znaczek create(Znaczek znaczek) {
        if (!znaczki.isEmpty()) {
            znaczek.setNumerKatalogowy(
                    this.znaczki.stream().mapToLong(p -> p.getNumerKatalogowy()).max().getAsLong() + 1);
        } else {
            znaczek.setNumerKatalogowy(1L);
        }
        this.znaczki.add(znaczek);
        return znaczek;
    }

    @Override
    public Znaczek update(Znaczek znaczek) throws NoSuchZnaczekException {
        for (int i = 0; i < this.znaczki.size(); i++) {
            if (Objects.equals(this.znaczki.get(i).getNumerKatalogowy(), znaczek.getNumerKatalogowy())) {
                this.znaczki.set(i, znaczek);
                return znaczek;
            }
        }
        throw new NoSuchZnaczekException("Nie ma takiego znaczka: " + znaczek.getNumerKatalogowy());
    }

    @Override
    public void deleteById(Long id) throws NoSuchZnaczekException {
        for (int i = 0; i < this.znaczki.size(); i++) {
            if (Objects.equals(this.znaczki.get(i).getNumerKatalogowy(), id)) {
                this.znaczki.remove(i);
            }
        }
        throw new NoSuchZnaczekException("Nie ma takiego znaczka: " + id);
    }

}
