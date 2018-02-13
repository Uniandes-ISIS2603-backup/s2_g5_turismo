/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.ValoracionesDetailDTO;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * <pre>Clase que implementa el recurso "valoraciones".
 * URL: /api/valoraciones
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "valoraciones".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author jf.gutierrez13  
 * @version 1.0
 */
@Path("valoraciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ValoracionesResource 
{
    /**
     * <h1>POST /api/usuario/id/valoraciones : Crear una valoracion.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link ValoracionesDetailDTO}.
     * 
     * Crea una nueva valoracion con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un id auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva valoracion .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la valoracion.
     * </code>
     * </pre>
     * @param valoracion {@link ValoracionesDetailDTO} - La valoracion que se desea guardar.
     * @return JSON {@link ValoracionesDetailDTO}  - La valoracion guardada con el atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la valoracion.
     */
    @POST
    public ValoracionesDetailDTO createValoracion(ValoracionesDetailDTO valoracion) throws BusinessLogicException {
        return valoracion;
    }
    
    /**
     * <h1>GET /api/valoraciones : Obtener todas las valoraciones.</h1>
     * 
     * <pre>Busca y devuelve todas las valoraciones que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las valoraciones de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link ValoracionesDetailDTO} - Las valoraciones encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ValoracionesDetailDTO> getValoraciones() {
        return new ArrayList<>();
    }
    
    /**
     * <h1>GET /api/usuario/{id}/paqueteTuristico/{id1}/plan/{id2}/valoraciones : Obtener las valoraciones de un plan contenido en un paquete turistico de un usuario dado por id.</h1>
     * 
     * <pre>Busca la valoracion con el usuario, paquete y plan asociados recibidos en la URL y la devuelve.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve la valoracion correspondiente al usuario, paquete y plan.
     * </code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe una valoracion con el usuario, paquete y plan dados.
     * </code> 
     * </pre>
     * @param id Identificador de la valoracion que se esta buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link ValoracionesDetailDTO} - La valoracion buscada
     */
    @GET
    @Path("{id: \\d+}/pqueteTuristico/{id1: \\d+}/plan/{id2: \\d+}/valoraciones")
    public ValoracionesDetailDTO getValoraciones(@PathParam("id") Long id, @PathParam("id1") Long id1,@PathParam("id2") Long id2) {
        return null;
    }
    
    /**
     * <h1>PUT /api/usuario/{id}/paqueteTuristico/{id1}/plan/{id2}/valoraciones/{id3} : Actualizar valoracion con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link ValoracionesDetailDTO}.
     * 
     * Actualiza la valoracion con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la valoracion con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una valoracion con el id dado.
     * </code> 
     * </pre>
     * @param id3 Identificador de la valoracion que se desea actualizar.Este debe ser una cadena de dígitos.
     * @param valoracion {@link ValoracionDetailDTO} La valoracion que se desea guardar.
     * @return JSON {@link ValoracionDetailDTO} - La valoracion guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la valoracion porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}/pqueteTuristico/{id1: \\d+}/plan/{id2: \\d+}/valoraciones/{id3: \\d+}")
    public ValoracionesDetailDTO updateCity(@PathParam("id") Long id, @PathParam("id1") Long id1, @PathParam("id2") Long id2, @PathParam("id3") Long id3, ValoracionesDetailDTO valoracion) throws BusinessLogicException {
        return valoracion;
    }
    
    /**
     * <h1>DELETE /api/usuario/{id}/paqueteTuristico/{id1}/plan/{id2}/valoraciones/{id3} : Borrar valoracion por id.</h1>
     * 
     * <pre>Borra la valoracion con el id asociado recibido en la URL.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la valoracion correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una valoracion con el id dado.
     * </code>
     * </pre>
     * @param id Identificador de la valoracion que se desea borrar. Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{id: \\d+}/pqueteTuristico/{id1: \\d+}/plan/{id2: \\d+}/valoraciones/{id3: \\d+}")
     public void deleteValoracion(@PathParam("id") Long id, @PathParam("id1") Long id1, @PathParam("id2") Long id2, @PathParam("id3") Long id3) {
        // Void
    }
}
