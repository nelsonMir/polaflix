package es.unican.nelson.polaflix_nelson.dominio;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CuentaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto incremento en BD con h2
    protected long id;
    protected String iban;

    public CuentaBancaria(String iban) {
        this.iban = iban;
    }

    public CuentaBancaria(){}
    
}
