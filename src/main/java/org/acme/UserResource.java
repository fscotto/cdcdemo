package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.Collection;

@Path(("/users"))
public class UserResource {
    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response users() {
        Collection<UserDTO> users = userService.getUsers();
        return Response.ok(users).build();
    }

    @POST
    public Response createUser(@RequestBody UserDTO user) {
        userService.createUser(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return Response.ok().build();
    }
}
