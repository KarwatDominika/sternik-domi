package pl.sternik.dk.domi.entities;

import java.math.BigDecimal;
import java.util.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;


@XmlRootElement
public class Znaczek {

    @NotNull
    private Long numerKatalogowy;

    @NotNull
    private Date rokProdukcji;

    @Size(min=2, max=50, message = "Opis should be in the range [{min}...{max}]")
    private String opis;

    @NotNull
    private BigDecimal cena;

    @NotEmpty
    private String krajPochodzenia;

    @NotNull
    private String stan;

    @NotNull
    private Status status;


    public static Znaczek produceZnaczek(Long numerKatalogowy, String krajPochodzenia, String opis,
                                       Date rokProdukcji, String stan, BigDecimal cena, Status status) {
        Znaczek z = new Znaczek();
        z.numerKatalogowy = numerKatalogowy;
        z.krajPochodzenia = krajPochodzenia;
        z.opis = opis;
        z.cena = cena;
        z.rokProdukcji = rokProdukcji;
        z.stan = stan;
        z.status = status;
        return z;
    }

    public Long getNumerKatalogowy() {
        return numerKatalogowy;
    }

    public String getOpis() {
        return opis;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public Date getRokProdukcji() {
        return rokProdukcji;
    }

    public String getKrajPochodzenia() {
        return krajPochodzenia;
    }

    public Status getStatus() {
        return status;
    }

    public String getStan() { return stan; }

    public void setNumerKatalogowy(Long numerKatalogowy) {
        this.numerKatalogowy = numerKatalogowy;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public void setRokProdukcji(Date rokProdukcji) {
        this.rokProdukcji = rokProdukcji;
    }

    public void setKrajPochodzenia(String krajPochodzenia) {
        this.krajPochodzenia = krajPochodzenia;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setStan(String stan) { this.stan = stan; }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((opis == null) ? 0 : opis.hashCode());
        result = prime * result + ((cena == null) ? 0 : cena.hashCode());
        result = prime * result + ((rokProdukcji == null) ? 0 : rokProdukcji.hashCode());
        result = prime * result + ((krajPochodzenia == null) ? 0 : krajPochodzenia.hashCode());
        result = prime * result + ((numerKatalogowy == null) ? 0 : numerKatalogowy.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((stan == null) ? 0 : stan.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Znaczek other = (Znaczek) obj;
        if (opis == null) {
            if (other.opis != null)
                return false;
        } else if (!opis.equals(other.opis))
            return false;
        if (cena == null) {
            if (other.cena != null)
                return false;
        } else if (!cena.equals(other.cena))
            return false;
        if (rokProdukcji == null) {
            if (other.rokProdukcji != null)
                return false;
        } else if (!rokProdukcji.equals(other.rokProdukcji))
            return false;
        if (krajPochodzenia == null) {
            if (other.krajPochodzenia != null)
                return false;
        } else if (!krajPochodzenia.equals(other.krajPochodzenia))
            return false;
        if (numerKatalogowy == null) {
            if (other.numerKatalogowy != null)
                return false;
        } else if (!numerKatalogowy.equals(other.numerKatalogowy))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (stan == null) {
            if (other.stan != null)
                return false;
        } else if (!stan.equals(other.stan))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Znaczek [numerKatalogowy=" + numerKatalogowy + ", Opis=" + opis +
                ", cenaNabycia=" + cena + ", rokProdukcji=" + rokProdukcji + ", krajPochodzenia="
                + krajPochodzenia + ", stan=" + stan + ", status=" + status + "]";
    }

}