package application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Path("/health")
public class HealthEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response healthcheck() {
      boolean healthy = true;

      System.out.println("Health endpoint");

      // SongsMetrics.requests.labels("/health").inc();

      JsonObject resp = new JsonObject();
      resp.addProperty("status", healthy? "UP" : "DOWN");

      Gson gson = new Gson();
      String json = gson.toJson(resp);
      
      return Response.ok(json).build();
    }

}
