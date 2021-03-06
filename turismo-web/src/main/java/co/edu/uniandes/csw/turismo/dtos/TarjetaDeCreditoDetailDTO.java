/**
 * TarjetaDeCreditoDetailDTO
 * Objeto de transferencia de datos de planes.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *        "name": String,
 *         "numero": number,
 *         "CDV":numero,
 *         "usuario":{
 *                      "id": number,
 *                       "nombre": string,
 *                       "apellido": string,
 *                       "contrasenia": string,
 *                       "correo": string,
 *                       "telefono": number,
 *                       "idioma": string,
 *                       "esAdministrador": boolean
 *                   }
 *                                   
 *   }
 * Por ejemplo una plan detallado se representa asi:<br>
 * 
 * <pre>
 * 
 *   {
 *      "name": sebastian,
 *      "numero":1234567891011324,
 *      "CDV":213,
 *      "cedula":1016078997
 *      "usuario":{
 *                  "id": 534682,
 *                  "nombre": Juan,
 *                  "apellido": Perez,
 *                  "contrasenia": rplc6519,
 *                  "correo": jperez@gmail.com,
 *                  "telefono": 3103334455,
 *                  "idioma": Ingles,
 *                  "esAdministrador": false
 *                 }
 *   }
 */
package co.edu.uniandes.csw.turismo.dtos;

import co.edu.uniandes.csw.turismo.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.turismo.entities.UsuarioEntity;

/**
 *
 * @author s.benitez10
 */
public class TarjetaDeCreditoDetailDTO extends TarjetaDeCreditoDTO
{
   /**
    * representa al usuario que tiene la tarjeta
    */
    private UsuarioDTO usuario;
    /**
     * constructor vacio
     */
    public TarjetaDeCreditoDetailDTO() 
    {
         // para mostrar si un DTO esta vacio
    }

   /**
    * metodo que se encarga de pasar un entity a DTO
    * @param entity 
    */
    public TarjetaDeCreditoDetailDTO(TarjetaDeCreditoEntity entity) 
    {
      super(entity);

        if (entity.getUsuario() != null)
        {
            this.usuario = new UsuarioDTO(entity.getUsuario());
        }

    }
    /**
     * metodo que se encargar de pasar de DTO a entity
     * @param user
     * @return 
     */
   // @Override
    public TarjetaDeCreditoEntity toEntity( UsuarioEntity user)
    {
        TarjetaDeCreditoEntity entity = super.toEntity();
        entity.setUsuario(user);
        return entity;
    }
    /**
     * devuelve el usuario DTO
     * @return usuario
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }
    /**
     * establece el usuario DTO
     * @param usuario 
     */
    public void setUsuario(UsuarioDTO pUsuario) {
        this.usuario = pUsuario;
    }

    
}
