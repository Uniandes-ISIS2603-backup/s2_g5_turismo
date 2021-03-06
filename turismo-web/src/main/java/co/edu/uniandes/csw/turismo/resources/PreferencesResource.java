/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PreferenciasDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.PreferenciasLogic;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.mappers.BusinessLogicExceptionMapper;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
/**
 * <pre>Clase que implementa el recurso "preferences".
 * URL: /api/preferences
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta "/api" y
 * este recurso tiene la ruta "preferences".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio). 
 * </pre>
 * @author jc.montoyar
 */
@Path("preferences")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PreferencesResource
{
    @Inject
    PreferenciasLogic preferenciasLogic;

    /**
     * <h1>POST /api/preferences : Crear una preferencia.</h1>
     * 
     * <pre>Cuerpo de petición: JSON {@link PreferenciasDetailDTO}.
     * 
     * Crea una nueva preferencia con la informacion que se recibe en el cuerpo 
     * de la petición y se regresa un objeto identico con un nombre auto-generado 
     * por la base de datos.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó la nueva preferencia .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe la preferencia.
     * </code>
     * </pre>
     * @param preferencias {@link PreferenciasDetailDTO} - La preferencia que se desea guardar.
     * @return JSON {@link PreferenciasDetailDTO}  
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera cuando ya existe la preferencia.
     */
    @POST
    public PreferenciasDetailDTO createPreferencias(PreferenciasDetailDTO preferencias) throws BusinessLogicException
    {
        return new PreferenciasDetailDTO(preferenciasLogic.createPreferencias(preferencias.toEntity()));
    }

    /**
     * <h1>GET /api/preferences : Obtener todas las preferencias (tipos de plan en el sistema).</h1>
     * 
     * <pre>Busca y devuelve todas las preferencias que existen en la aplicacion.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas las preferencias de la aplicacion.</code> 
     * </pre>
     * @return JSONArray {@link PreferenciasDetailDTO} - Las preferencias encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<PreferenciasDetailDTO> getpreferences() 
    {
        return listPreferenciasEntity2DetailDTO(preferenciasLogic.getPreferenciass());
    }
    
    /**
     * <h1>PUT /api/preferences/{nombre} : Actualizar preferencia con el nombre dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PreferenciasDetailDTO}.
     * 
     * Actualiza la preferencia con el nombre recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     * 
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza la preferencia con el nombre dado con la información enviada como parámetro. Retorna un objeto identico.</code> 
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una preferencia con el nombre dado.
     * </code> 
     * </pre>
     * @param id 
     * @param preferencias {@link PreferenciasDetailDTO} La preferencia que se desea guardar.
     * @return JSON {@link PreferenciasDetailDTO} - La preferencia guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} - Error de lógica que se genera al no poder actualizar la preferencia porque ya existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public PreferenciasDetailDTO updatePreferencias(@PathParam("id") Long id, PreferenciasDetailDTO preferencias) throws BusinessLogicException
    {
        preferencias.setId(id);
        PreferenciasEntity entity = preferenciasLogic.getPreferencias(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /preferences/" + id + " no existe.", 404);
        }
        return new PreferenciasDetailDTO(preferenciasLogic.updatePreferencias(preferencias.toEntity()));
    }
    
    /**
     * <h1>DELETE /api/preferences/{id} : Borrar preferencia por nombre.</h1>
     * 
     * <pre>Borra la preferencia con el id dado.
     * 
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina la preferencia correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe una preferencia con el id dado.
     * </code>
     * </pre>
     * @param id  
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePreferencias(@PathParam("id") Long id) throws BusinessLogicException
    {
        PreferenciasEntity entity = preferenciasLogic.getPreferencias(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /preferences/" + id + " no existe.", 404);
        }
        preferenciasLogic.deletePreferencias(id);
    }
    
     /**
     * <h1>GET /api/preferences/{nombreTipoPlan} : Obtener una preferencia dado su nombre.</h1>
     *
     * <pre>Buscala preferencia / categoria / tipo de plan dado el nombre y lo retorna.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el tipo plan correspondiente.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un tipo plan con el nombre dado.
     * </code>
     * </pre>
     *
     * @param id
     * @return JSON {@link PlanDetailDTO} - la preferencia buscada
     */
    @GET
    @Path("{id: \\d+}")
    public PreferenciasDetailDTO getPreferencia(@PathParam("id") Long id)
    {
        PreferenciasEntity entity = preferenciasLogic.getPreferencias(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /preferences/" + id + " no existe.", 404);
        }
        return new PreferenciasDetailDTO(entity);
    }

    /**
     * Recibe una lista de plan entities y la convierte a dtos
     * @param entityList
     * @return dtos lista
     */
    private List<PreferenciasDetailDTO> listPreferenciasEntity2DetailDTO(List<PreferenciasEntity> entityList) 
    {
        List<PreferenciasDetailDTO> list = new ArrayList<>();
        for (PreferenciasEntity entity : entityList) 
        {
            list.add(new PreferenciasDetailDTO(entity));
        }
        return list;
    }
}