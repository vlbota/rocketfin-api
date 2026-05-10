package vlb.developer.profile.controller;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import vlb.developer.profile.dtos.ClientDto;
import vlb.developer.profile.forms.ClientCreateForm;
import vlb.developer.profile.forms.ClientUpdateForm;
import vlb.developer.profile.service.ClientReadService;
import vlb.developer.profile.service.ClientWriteService;

import java.util.List;
import java.util.UUID;

@Path("/profile/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientProfileController {

    @Inject
    ClientWriteService writeService;

    @Inject
    ClientReadService readService;

    @POST
    public Response add(@Valid ClientCreateForm form) {
        return Response.status(Response.Status.CREATED)
                .entity(writeService.addClient(form))
                .build();
    }

    @PUT
    @Path("/{id}")
    public ClientDto update(@PathParam("id") UUID id, @Valid ClientUpdateForm form) {
        return writeService.updateClient(id, form);
    }

    @GET
    public List<ClientDto> list() {
        return readService.listClients();
    }

    @GET
    @Path("/{id}")
    public ClientDto get(@PathParam("id") UUID id) {
        return readService.getClient(id);
    }
}
