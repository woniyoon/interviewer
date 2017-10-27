package ca.ciccc.madp202.backend;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ca.ciccc.madp202.backend.model.User;

@Path("interview")
public class Interview {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    		User profile = new User("jaewon", "yoon", "korea", "woniyoon@gmail.com", "gjddj02!");
    		return profile.getFirstName() +"  "+ profile.getLastName();
    }
}
