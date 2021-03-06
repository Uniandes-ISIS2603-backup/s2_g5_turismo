package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PlanDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.PlanLogic;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.turismo.mappers.BusinessLogicExceptionMapper;
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
 * <pre>Clase que implementa el recurso "plans".
 * URL: /api/plans
 * </pre>
 * <i>Note que la aplicación (definida en {@link RestConfig}) define la ruta
 * "/api" y este recurso tiene la ruta "Plans".</i>
 *
 * <h2>Anotaciones </h2>
 * <pre>
 * Path: indica la dirección después de "api" para acceder al recurso
 * Produces/Consumes: indica que los servicios definidos en este recurso reciben y devuelven objetos en formato JSON
 * RequestScoped: Inicia una transacción desde el llamado de cada método (servicio).
 * </pre>
 */
@Path("plans")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PlanResource 
{
    @Inject
    PlanLogic planLogic;
    
    /**
     * Recibe una lista de plan entities y la convierte a dtos
     * @param entityList
     * @return dtos lista
     */
    private List<PlanDetailDTO> listPlanEntity2DetailDTO(List<PlanEntity> entityList) 
    {
        List<PlanDetailDTO> list = new ArrayList<>();
        for (PlanEntity entity : entityList) 
        {
            list.add(new PlanDetailDTO(entity));
        }
        return list;
    }

    /**
     * <h1>POST /api/plans : Crear un Plan.</h1>
     *
     * <pre>Cuerpo de petición: JSON {@link PlanDetailDTO}.
     *
     * Crea un nuevo Plan con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Creó el nuevo Plan .
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 412 Precodition Failed: Ya existe el Plan.
     * </code>
     * </pre>
     *
     * @param plan {@link PlanDetailDTO} - el Plan que se desea guardar.
     * @return JSON {@link PlanDetailDTO} - el Plan guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el Plan.
     */
    @POST
    public PlanDetailDTO createPlan(PlanDetailDTO plan) throws BusinessLogicException
    {
        return new PlanDetailDTO(planLogic.createPlan(plan.toEntity()));
    }

    /**
     * <h1>GET /api/plans : Obtener todos los Planes.</h1>
     *
     * <pre>Busca y devuelve todos los Planes que existen en la aplicacion.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve todas los Planes de la aplicacion.</code>
     * </pre>
     *
     * @return JSONArray {@link PlanDetailDTO} - Los Planes encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<PlanDetailDTO> getPlans() 
    {
        return listPlanEntity2DetailDTO(planLogic.getPlans());
    }

    /**
     * <h1>GET /api/plans/{id} : Obtener Plan por id.</h1>
     *
     * <pre>Busca el Plan con el id asociado recibido en la URL y lo devuelve.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Devuelve el Plan correspondiente al id.
     * </code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found No existe un Plan con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Plan que se esta buscando. Este debe ser
     * una cadena de dígitos.
     * @return JSON {@link PlanDetailDTO} - el Plan buscado
     */
    @GET
    @Path("{id: \\d+}")
    public PlanDetailDTO getPlan(@PathParam("id") Long id)
    {
        PlanEntity entity = planLogic.getPlan(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + id + " no existe.", 404);
        }
        return new PlanDetailDTO(entity);
    }

    /**
     * <h1>PUT /api/plans/{id} : Actualizar Plan con el id dado.</h1>
     * <pre>Cuerpo de petición: JSON {@link PlanDetailDTO}.
     *
     * Actualiza el Plan con el id recibido en la URL con la informacion que se recibe en el cuerpo de la petición.
     *
     * Codigos de respuesta:
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Actualiza el Plan con el id dado con la información enviada como parámetro. Retorna un objeto identico.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Plan con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Plan que se desea actualizar.Este debe ser
     * una cadena de dígitos.
     * @param plan {@link PlanDetailDTO} el Plan que se desea guardar.
     * @return JSON {@link PlanDetailDTO} - el Plan guardada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera al no poder actualizar el Plan porque ya
     * existe una con ese nombre.
     */
    @PUT
    @Path("{id: \\d+}")
    public PlanDetailDTO updatePlan(@PathParam("id")Long id, PlanDetailDTO plan) throws BusinessLogicException 
    {
        plan.setIdPlan(id);
        PlanEntity entity = planLogic.getPlan(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + id + " no existe.", 404);
        }
        return new PlanDetailDTO(planLogic.updatePlan(plan.toEntity()));
    }

    /**
     * <h1>DELETE /api/plans/{id} : Borrar Plan por id.</h1>
     *
     * <pre>Borra el Plan con el id asociado recibido en la URL.
     *
     * Códigos de respuesta:<br>
     * <code style="color: mediumseagreen; background-color: #eaffe0;">
     * 200 OK Elimina el Plan correspondiente al id dado.</code>
     * <code style="color: #c7254e; background-color: #f9f2f4;">
     * 404 Not Found. No existe un Plan con el id dado.
     * </code>
     * </pre>
     *
     * @param id Identificador de el Plan que se desea borrar. Este debe ser una
     * cadena de dígitos.
     * @throws co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePlan(@PathParam("id")Long id) throws BusinessLogicException 
    {
        PlanEntity entity = planLogic.getPlan(id);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /plans/" + id + " no existe.", 404);
        }
        planLogic.deletePlan(id);
    }
}