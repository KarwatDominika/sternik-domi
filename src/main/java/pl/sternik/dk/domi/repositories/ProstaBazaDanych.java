package pl.sternik.dk.domi.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import pl.sternik.dk.domi.entities.Status;
import pl.sternik.dk.domi.entities.Znaczek;


@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements ZnaczkiRepository {

    private Znaczek[] baza;

    public ProstaBazaDanych() {
        baza = new Znaczek[15];
        Znaczek z = new Znaczek();
        z.setNumerKatalogowy(0L);
        z.setKrajPochodzenia("Polska");
        z.setOpis("znaczek z kwiatkami");
        z.setRokProdukcji(new Date());
        z.setStan("Bardzo dobry");
        z.setCena(new BigDecimal("4.55"));
        z.setStatus(Status.NOWY);
        baza[0] = z;
        z = new Znaczek();
        z.setNumerKatalogowy(1L);
        z.setKrajPochodzenia("Polska");
        z.setOpis("znaczek z postacia historycza");
        z.setRokProdukcji(new Date());
        z.setStan("Bardzo dobry");
        z.setCena(new BigDecimal("5.55"));
        z.setStatus(Status.DO_SPRZEDANIA);
        baza[1] = z;
        z = new Znaczek();
        z.setNumerKatalogowy(2L);
        z.setKrajPochodzenia("Polska");
        z.setOpis("znaczek z krolowa angli");
        z.setRokProdukcji(new Date());
        z.setStan("zagiety rog");
        z.setCena(new BigDecimal("14.55"));
        z.setStatus(Status.STARY);
        baza[2] = z;

    }

    public ProstaBazaDanych(int rozmiarBazy) { baza = new Znaczek[rozmiarBazy];}

    @Override
    public Znaczek create(Znaczek znaczek) throws ZnaczekAlreadyExistsException {
        if (znaczek.getNumerKatalogowy() != null && baza[znaczek.getNumerKatalogowy().intValue()] != null) {
            if (znaczek.getNumerKatalogowy().equals(baza[znaczek.getNumerKatalogowy().intValue()].getNumerKatalogowy())) {
                throw new ZnaczekAlreadyExistsException("Już jest znaczek o takim numerze.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = znaczek;
                znaczek.setNumerKatalogowy((long) i);
                return znaczek;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
    }

    @Override
    public void deleteById(Long id) throws NoSuchZnaczekException {
        int numerKatalogowy = id.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchZnaczekException("Nie poprawny numer katologowy");
        }
        // tu troche zle ;)
        baza[numerKatalogowy] = null;
    }

    @Override
    public Znaczek update(Znaczek znaczek) throws NoSuchZnaczekException {
        int numerKatalogowy = znaczek.getNumerKatalogowy().intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchZnaczekException("Nie poprawny numer katologowy");
        }

        Znaczek z = baza[znaczek.getNumerKatalogowy().intValue()];
        if (z == null) {
            throw new NoSuchZnaczekException("Brak takiego znaczka.");
        } else {
            baza[znaczek.getNumerKatalogowy().intValue()] = znaczek;
        }
        return znaczek;
    }

    @Override
    public Znaczek readById(Long numerKatalogowy) throws NoSuchZnaczekException {
        int id = numerKatalogowy.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(id) || czyWolne(id)) {
            throw new NoSuchZnaczekException();
        }
        return baza[id];
    }

    private boolean czyWolne(int id) {
        if(baza[id]!= null)
            return false;
        return true;
    }

    @Override
    public List<Znaczek> findAll() {
        List<Znaczek> tmp = new ArrayList<>();
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] != null)
                tmp.add(baza[i]);
        }
        return tmp;
    }

    public void wyswietlBaze() {
        for (int i = 0; i < baza.length; i++) {
            System.out.println("" + i + ":" + baza[i]);
        }
    }

    private boolean sprawdzPoprawnoscNumeruKatalogowego(int numerKatalogowy) {
        if (numerKatalogowy < 0 || numerKatalogowy >= baza.length) {
            System.out.println("Zły numer katalogowy");
            return false;
        }
        return true;
    }

}
