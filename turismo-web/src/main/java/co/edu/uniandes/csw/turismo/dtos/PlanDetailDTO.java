/**
 * PlanDetailDTO
 * Objeto de transferencia de datos de planes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "idPlan": number,
 *      "nombrePlan: string,
 *      "descripcion": string,
 *      "duracion":number,
 *      "restricciones": string,
 *      "archivo": string,
 *      "precio": number,
 *      "cantidadPersonas": number,
 *      "valoraciones":[{
 *                     "calificacion": number,
 *                     "comentario": string,
 *                     }],
 *      "guiasPlan":[{
 *                     "id":number,
 *                     "nombreGuia": number,
 *                     "idiomaGuia": string,
 *                     "idiomaGuia": string,
 *                  }],
        "categoriasPlan":[{"tipoPlan": string},{"tipoPlan": string }]   
 *      "ubicacion":{
 *                  "id": number, 
 *                  "ciudad":string,
 *                  "pais":string,
 *                  "latitud":number,
 *                  "longitud":number                
 *                  }
 *   }
 * Por ejemplo un plan detallado se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
       "idPlan": 1,
       "nombrePlan": "Visita a Monserrate",
       "descripcion": "Ir a monserrate subiendo por teleferico",
       "duracion":180,
       "restricciones": "Menores deben ir a compañados",
       "archivo": "imagenLink",
       "precio": 20.000,
       "cantidadPersonas": 9999,
       "valoraciones":[{
                      "calificacion": 4,
                      "comentario": "fue shido"
                      },
                      {
                      "calificacion": 2,
                      "comentario": "no fue shido"
                      }
                      ],
       "guiasPlan":[{
                      "idGuia": 1,
                      "nombreGuia": "Julian",
                      "idiomaGuia": "Español"
                   },
                   {
                      "idGuia": 2,
                      "nombreGuia": "pejelagarto",
                      "idiomaGuia": "Ingles"
                   }
                   ],
        "categoriasPlan":[{"tipoPlan":"Religion"},{"tipoPlan":"Mirador"}]  
    }
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.GuiaEntity;
import co.edu.uniandes.csw.turismo.entities.PlanEntity;
import co.edu.uniandes.csw.turismo.entities.PreferenciasEntity;
import co.edu.uniandes.csw.turismo.entities.ValoracionesEntity;
import java.util.ArrayList;
import java.util.List;

/*
 * Objeto de transferencia detallado de datos de planes
 * @author jc.montoyar
 */
public class PlanDetailDTO extends PlanDTO {

    //ATRIBUTOS 
    /**
     * Atributo que modela la lista de guias que hay para el plan
     */
    private List<GuiaDTO> guiasPlan;

    /**
     * Atributo que modela una lista con los categorias de plan de el plan
     */
    private List<PreferenciasDTO> categoriasPlan;

    /**
     * Atributo que modela las valoraciones del plan
     */
    private List<ValoracionesDTO> valoraciones; 
    
    /**
     * Atributo que modela la ubicacion del plan
     */
    private UbicacionDTO ubicacion;

    //CONSTRUCTOR
    /**
     * Constructor por defecto
     */
    public PlanDetailDTO()
    {
        super();
    }
    
    /**
     * Constructor a partir de una entity de plan
     * @param entity 
     */
    public PlanDetailDTO(PlanEntity entity)
    {
        super(entity);
        if (entity.getUbicacion() != null)
        {
            this.ubicacion = new UbicacionDTO(entity.getUbicacion());
        }
        else
        {
            entity.setUbicacion(null);
        }
        if (entity.getPreferenciasPlan() != null) 
        {
            categoriasPlan = new ArrayList<>();
            for (PreferenciasEntity entityPreferencias : entity.getPreferenciasPlan()) 
            {
                categoriasPlan.add(new PreferenciasDTO(entityPreferencias));
            }
        }
        if (entity.getGuias() != null) 
        {
            guiasPlan = new ArrayList<>();
            for (GuiaEntity entityGuia : entity.getGuias()) 
            {
                guiasPlan.add(new GuiaDTO(entityGuia));
            }
        }
        if (entity.getValoracionesPlan() != null) 
        {
            valoraciones = new ArrayList<>();
            for (ValoracionesEntity entityValoraciones : entity.getValoracionesPlan()) 
            {
                valoraciones.add(new ValoracionesDTO(entityValoraciones));
            }
        }
    }
    
    /**
     * Transformar el DTO a una entidad
     * @return La entidad que representa el plan.
     */
    @Override
    public PlanEntity toEntity() 
    {
        PlanEntity planE = super.toEntity();
        if (this.getUbicacion() != null)
        {
            planE.setUbicacion(this.getUbicacion().toEntity());
        }
        if (getValoraciones() != null) 
        {
            List<ValoracionesEntity> valoracionesEntity = new ArrayList<>();
            for (ValoracionesDTO dtoValoracion : getValoraciones()) 
            {
                valoracionesEntity.add(dtoValoracion.toEntity());
            }
            planE.setValoracionesPlan(valoracionesEntity);
        }
        if (guiasPlan != null)
        {
            List<GuiaEntity> guiaEntity = new ArrayList<>();
            for (GuiaDTO dtoGuia : getGuiasPlan()) 
            {
                guiaEntity.add(dtoGuia.toEntity());
            }
            planE.setGuias(guiaEntity);
        }
        if (categoriasPlan != null) {
            List<PreferenciasEntity> preferenciaEntity = new ArrayList<>();
            for (PreferenciasDTO dtoPreferencia : getCategoriasPlan()) 
            {
                preferenciaEntity.add(dtoPreferencia.toEntity());
            }
            planE.setPreferenciasPlan(preferenciaEntity);
        }
        return planE;
    }

    //METODOS
    
    /**
    * @return la ubicacion asociada del plan
    */
    public UbicacionDTO getUbicacion()
    {
        return this.ubicacion;
    }

    /**
    * Cambia la ubicacion del plan por la dada por parametro
    * @param ubicacion 2 set
    */ 
    public void setUbicacion(UbicacionDTO ubicacion) 
    {
        this.ubicacion = ubicacion;
    }
    
    /**
     * @return los guias asociados del plan
     */
    public List<GuiaDTO> getGuiasPlan()
    {
        return this.guiasPlan;
    }

    /**
     * Cambia los planes del guia por los dados por parametro
     * @param guiasPlan 2 set
     */ 
    public void setGuiasPlan(List<GuiaDTO> guiasPlan) 
    {
        this.guiasPlan = guiasPlan;
    }

    /**
     * @return los categorias de plan del plan
     */
    public List<PreferenciasDTO> getCategoriasPlan() 
    {
        return this.categoriasPlan;
    }

    /**
     * Cambia las tipos de plan por los dados por parámetro
     * @param categoriasPlan 2 set
     */
    public void setCategoriasPlan(List<PreferenciasDTO> categoriasPlan)
    {
        this.categoriasPlan = categoriasPlan;
    }

    /**
     * @return the valoraciones
     */
    public List<ValoracionesDTO> getValoraciones() 
    {
        return valoraciones;
    }

    /**
     * @param valoraciones the valoraciones to set
     */
    public void setValoraciones(List<ValoracionesDTO> valoraciones) 
    {
        this.valoraciones = valoraciones;
    }
}
