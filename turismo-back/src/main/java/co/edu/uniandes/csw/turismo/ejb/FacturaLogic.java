/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.ejb;

import co.edu.uniandes.csw.turismo.entities.FacturaEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.persistence.FacturaPersistence;
import co.edu.uniandes.csw.turismo.persistence.TarjetaDeCreditoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author s.benitez10
 */
@Stateless
public class FacturaLogic 
{
    /**
     * modela el logger
     */
    private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());
     /**
      * llama la capa de persistencia de factura
      */
    @Inject
    private FacturaPersistence persistence;
   
    /**
     * le pide a la capa de persistencia crear una factura despues de haber verificado las reglas de negocio
     * @param entity
     * @return factura creada
     * @throws BusinessLogicException 
     */
    public FacturaEntity createFactura(FacturaEntity entity) throws BusinessLogicException 
    {
       
        LOGGER.info("Inicia proceso de creación de la Factura");
       //verifica la regla de negocio de que no pueden haber 2 facturas con el mismo id
        if (persistence.find(entity.getId())!= null) 
        {
            throw new BusinessLogicException("Ya existe una factura con el id" + entity.getId() + "\"");
        }
        if(entity.getCosto() < 0)
        {
            throw new BusinessLogicException("La factura no puede tener un costo negativo o un costo 0. Costo= " + entity.getCosto() );
            
        }     
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de Factura");
        return entity;
    }
    /**
     * Le pide a la capa de persistencia que traiga todas las facturas de la base de datos
     * @return list de facturas
     */ 
    public List<FacturaEntity> getFacturas()
    {
        LOGGER.info("Inicia proceso de consultar todos los facturas");
        List<FacturaEntity> facturas = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los facturas");
        return facturas;
        
    }
    /**
     * le pide a la capa de persistencia que solo le traiga una factura
     * @param id
     * @return factura
     */
    public FacturaEntity getFactura(Long id) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Factura con id={0}", id);
        FacturaEntity factura= persistence.find(id);
        if (factura == null) {
            LOGGER.log(Level.SEVERE, "factura con el id {0} no existe", id);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar factura con id={0}", id);
        return factura;
    }
    /**
     * le pide a la capa de persistencia que le traiga la factura que fue pagada con el numero de tarjeta dada
     * por parametro
     * @param numeroTarjeta
     * @return factura
     */
    public List<FacturaEntity> getFacturaTarjeta(Long numeroTarjeta) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar Factura con tarjeta ={0}", numeroTarjeta);
        List<FacturaEntity> factura= persistence.findByTarjeta(numeroTarjeta);
        if (factura == null) {
            LOGGER.log(Level.SEVERE, " no hay facturas con la tarjeta {0} no existe", numeroTarjeta);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar factura con tarjeta={0}", numeroTarjeta);
        return factura;
    }
    /**
     * Elimina una factura con el id pasado por parametro
     * @param id 
     */
     public void deleteFactura(Long id)
     {
         FacturaEntity factura = getFactura(id);
         factura.setTarjetadecredito(null);
         LOGGER.log(Level.INFO, "Inicia proceso de Eliminar Factura con id={0}", id);
         persistence.delete(id);
         LOGGER.log(Level.INFO, "Termina proceso de Eliminar factura con id={0}", id);
     }
     /**
      * actualiza una factura con id y datos de la factura nueva pasado por parametro
      * ademas valida que cunmpla las reglas de negocio
      * @param id
      * @param entity
      * @return factura modificada
      * @throws BusinessLogicException 
      */
     public FacturaEntity updateFactura(Long id, FacturaEntity entity) throws BusinessLogicException 
     {
    
        // verifica que la factura no tenga un costo negativo
        if(entity.getCosto() < 0)
        {
            throw new BusinessLogicException("La factura no puede tener un costo negativo o un costo 0. Costo= " + entity.getCosto() );
            
        }
        entity.setId(id);
        FacturaEntity newEntity = persistence.update(entity);
        return newEntity;
     }
    
}
