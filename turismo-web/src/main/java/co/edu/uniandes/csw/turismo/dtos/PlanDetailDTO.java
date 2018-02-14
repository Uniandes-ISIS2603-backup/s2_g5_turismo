/*
 * PlanDetailDTO
 * Objeto de transferencia de datos de planes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "idPlan": number,
 *      "nombrePlan: string,
 *      "descripcion": string,
 *      "pais": string,
 *      "ciudad": string,
 *      "longitud": number,
 *      "latitud": number,
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
 *                     "nombreGuia": number,
 *                     "idiomaGuia": string,
 *                     "idiomaGuia": string,
 *                  }],
        "categoriasPlan":{"tiposPlan":[string]}                     
 *   }
 * </pre>
 */
package co.edu.uniandes.csw.turismo.dtos;

import java.util.ArrayList;

/*
 * Objeto de transferencia detallado de datos de planes
 * @author jc.montoyar
 */
public class PlanDetailDTO extends PlanDTO {

    //ATRIBUTOS 
    /**
     * Atributo que modela la lista de guias que hay para el plan
     */
    private ArrayList<GuiaDTO> guiasPlan;

    /**
     * Atributo que modela una lista con los categorias de plan de el plan
     */
    private ArrayList<PreferenciasDTO> categoriasPlan;

    /**
     * Atributo que modela las valoraciones del plan
     */
    private ArrayList<ValoracionesDTO> valoraciones; //TODO, ES DE TIPO ValoracionDTO

    //CONSTRUCTOR
    /**
     * Constructor poe defecto
     */
    public PlanDetailDTO() { }

    //METODOS
    /**
     * @return los guias asociados del plan
     */
    public ArrayList<GuiaDTO> getGuiasPlan()
    {
        return this.guiasPlan;
    }

    /**
     * Cambia los planes del guia por los dados por parametro
     * @param guiasPlan 2 set
     */ 
    public void setGuiasPlan(ArrayList<GuiaDTO> guiasPlan) 
    {
        this.guiasPlan = guiasPlan;
    }

    /**
     * @return los categorias de plan del plan
     */
    public ArrayList<PreferenciasDTO> getTiposPlan() 
    {
        return this.categoriasPlan;
    }

    /**
     * Cambia las tipos de plan por los dados por parámetro
     * @param tiposPlan 2 set
     */
    public void setCategoriasPlan(ArrayList<PreferenciasDTO> tiposPlan)
    {
        this.categoriasPlan = tiposPlan;
    }

    /**
     * @return the valoraciones
     */
    public ArrayList getValoraciones() 
    {
        return valoraciones;
    }

    /**
     * @param valoraciones the valoraciones to set
     */
    public void setValoraciones(ArrayList valoraciones) 
    {
        this.valoraciones = valoraciones;
    }
}