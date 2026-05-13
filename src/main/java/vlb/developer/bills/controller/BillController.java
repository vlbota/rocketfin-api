package vlb.developer.bills.controller;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import vlb.developer.bills.dtos.BillDTO;
import vlb.developer.bills.dtos.BillPaidDTO;
import vlb.developer.bills.forms.BillCreateFORM;
import vlb.developer.bills.forms.BillPayFORM;
import vlb.developer.bills.forms.BillUpdateFORM;
import vlb.developer.bills.service.BillReadService;
import vlb.developer.bills.service.BillWriteService;

import java.util.List;
import java.util.UUID;

@Path("/bills")
@Authenticated
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BillController {

    @Inject
    JsonWebToken jwt;

    @Inject
    BillReadService readService;

    @Inject
    BillWriteService writeService;

    private UUID clientId() {
        return UUID.fromString(jwt.getClaim("id"));
    }

    @GET
    public List<BillDTO> list() {
        return readService.listByClient(clientId());
    }

    @GET
    @Path("/{id}")
    public BillDTO get(@PathParam("id") Long id) {
        return readService.findByIdAndClient(id, clientId());
    }

    @POST
    public Response create(@Valid BillCreateFORM form) {
        return Response.status(Response.Status.CREATED)
                .entity(writeService.create(form, clientId()))
                .build();
    }

    @PUT
    @Path("/{id}")
    public BillDTO update(@PathParam("id") Long id, @Valid BillUpdateFORM form) {
        return writeService.update(id, form, clientId());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        writeService.delete(id, clientId());
        return Response.noContent().build();
    }

    @POST
    @Path("/{id}/pay")
    public BillPaidDTO pay(@PathParam("id") Long id, @Valid BillPayFORM form) {
        return writeService.pay(id, form, clientId());
    }
}
