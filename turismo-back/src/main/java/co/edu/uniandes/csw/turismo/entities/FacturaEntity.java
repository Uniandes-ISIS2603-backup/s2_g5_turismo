/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.benitez10
 */
@Entity
public class FacturaEntity extends  BaseEntity implements Serializable
{
    /* atributo del costo de la factura
    */
    private Long costo; 
   
    //Relaciones
    @PodamExclude
    @ManyToOne
    private TarjetaDeCreditoEntity tarjetadecredito;
    
    @OneToOne( cascade=CascadeType.PERSIST)
    private PaqueteTuristicoEntity paqueteturistico;
    
    /**
     * @return la tarjeta de credito con que se pago la factura
    */
    public TarjetaDeCreditoEntity getTarjetadecredito() {
        return tarjetadecredito;
    }
    /**
     * establece la tarjeta de credito con que se pago la factura
     * @param tarjetadecredito 
     */
    public void setTarjetadecredito(TarjetaDeCreditoEntity tarjetadecredito) {
        this.tarjetadecredito = tarjetadecredito;
    }
    /**
     *
     * @return el paquete turistico que se esta pagando 
     */
    public PaqueteTuristicoEntity getPaqueteturistico() {
        return paqueteturistico;
    }
    /**
     * Establece el paquete turitico que se va pagar en la factura
     * @param paqueteturistico 
     */
    public void setPaqueteturistico(PaqueteTuristicoEntity paqueteturistico) {
        this.paqueteturistico = paqueteturistico;
    }

    /**
     * 
     * @return El costo de la factura
     */
    public Long getCosto() {
        return costo;
    }
     /**
      * Establece el costo de la factura
      * @param costo 
      */
    public void setCosto(Long costo) {
        this.costo = costo;
    }
    
    
}
