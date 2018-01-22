package com.penguinwan.pattern.matcher.infrastructure;

import com.penguinwan.pattern.matcher.application.MatcherNotFoundException;
import com.penguinwan.pattern.matcher.application.MatchingApplicationService;
import com.penguinwan.pattern.matcher.domain.model.Consequent;
import com.penguinwan.pattern.matcher.domain.model.Input;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Path("matcher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MatchingRestController {
    @Inject
    private MatchingApplicationService matchingApplicationService;

    @POST
    @Path("{matcherId}")
    public Response match(@PathParam("matcherId") long matcherId, HashMap<String, String> inputs) {

        List<Input> inputList = inputs.entrySet().
                stream().
                map(entry -> new Input(entry.getKey(), entry.getValue())).
                collect(Collectors.toList());

        try {
            Consequent consequent = matchingApplicationService.match(
                    matcherId,
                    inputList.toArray(
                            new Input[inputList.size()]));
            return Response.ok().entity(consequent).build();

        } catch (MatcherNotFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
