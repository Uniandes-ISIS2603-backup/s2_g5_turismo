/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turismo.resources;

import co.edu.uniandes.csw.turismo.dtos.PlanAjendadoDetailDTO;
import co.edu.uniandes.csw.turismo.ejb.PlanAjendadoLogic;
import co.edu.uniandes.csw.turismo.entities.PlanAjendadoEntity;
import co.edu.uniandes.csw.turismo.exceptions.BusinessLogicException;
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
 *
 * @author dl.avendano
 */

    @Path("miPlan")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PlanAjendadoResource {
    
}