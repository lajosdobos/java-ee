/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oracle.iqjb.hu;

import java.net.URI;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import oracle.iqjb.hu.cdi.facade.DepartmentCdiSessionService;
import oracle.iqjb.hu.pm.dm.Department;

/**
 * Teszteles
 * Firefoxbol pl. RESTClient
 * 
 * Be kell allitani hogy GET/POST/PUT/DELETE
 * Es ettol fuggoen:
 * http://localhost:7001/pm-server-web/webresources/departmentservices/add
 * 
 *  -ehhez a request body-ba kell a parameter json, pl:
 *          {"type":"department", "name":"rest-add-new"}
 * Ha Invalid, akkor a RESTClient-nel be kell allitani, hogy Headers -> Custom Header -> Name : ContentType, AttributeValue : application/json
 *  
 * http://localhost:7001/pm-server-web/webresources/departmentservices
 * http://localhost:7001/pm-server-web/webresources/departmentservices/get/10 (ahol a 10 az id)
 * http://localhost:7001/pm-server-web/webresources/departmentservices/update/10 (ahol az id 10, es a requestBody az add-hoz hasonloan)
 */
@Path("departmentservices")
@RequestScoped
public class DepartmentServicesResource {

    @Context
    private UriInfo context;
    
    @Inject
    private DepartmentCdiSessionService departmentCdiSessionService;    

    /**
     * Creates a new instance of DepartmentServicesResource
     */
    public DepartmentServicesResource() {
    }

    /**
     * Retrieves representation of an instance of oracle.iqjb.hu.DepartmentServicesResource
     * @return an instance of java.lang.String
     */
    @GET
    public List<Department> get() {
        return departmentCdiSessionService.findAll();
    }
    
    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Department get(@PathParam("id") String id) {
        return departmentCdiSessionService.findDepartmentById(Long.parseLong(id));
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") String id) {
        departmentCdiSessionService.delete(Long.parseLong(id));
        return Response.ok().build();
    }   
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Department department) {
        department = departmentCdiSessionService.add(department);
        URI uri = context.getAbsolutePathBuilder().path(department.getId().toString()).build();
        return Response.created(uri).build();
    }
    
    @PUT
    @Path(value = "/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Department department) {
        Department d = departmentCdiSessionService.findDepartmentById(Long.parseLong(id));
        d.setName(department.getName());
        departmentCdiSessionService.add(d);
        return Response.noContent().build();
    }
    
}
