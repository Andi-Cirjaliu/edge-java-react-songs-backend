package application.rest;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import application.metrics.SongsMetrics;

@Path("/")
public class RootEndpoint {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response listResources(@Context UriInfo uriInfo) {
    System.out.println("Index endpoint - JSON");

    SongsMetrics.requests.labels("/").inc();

    String listURL = (uriInfo.getAbsolutePath() + "/v1/books").replaceAll("(?<!http:)\\/\\/", "/");
    String rateURL = (uriInfo.getAbsolutePath() + "/rateTest").replaceAll("(?<!http:)\\/\\/", "/");
    String metricsURL = (uriInfo.getAbsolutePath() + "/metrics").replaceAll("(?<!http:)\\/\\/", "/");
    String healthURL = (uriInfo.getAbsolutePath() + "/health").replaceAll("(?<!http:)\\/\\/", "/");

    JsonObject resp = new JsonObject();
    resp.addProperty("list", listURL);
    resp.addProperty("rate", rateURL);
    resp.addProperty("metrics", metricsURL);
    resp.addProperty("health", healthURL);

    Gson gson = new Gson();
    String json = gson.toJson(resp);
    System.out.println("JSON: "+ json);

    return Response.ok(json).build();
  }

  @GET
  @Produces({ MediaType.TEXT_HTML })
  public InputStream getIndex() {
    System.out.println("Index endpoint - html");
    
    SongsMetrics.requests.labels("/").inc();

    try {
      return this.getClass().getResourceAsStream("/index.html");
    } catch (Exception e) {
      throw new RuntimeException("Exception returning index.html", e);
    }
  }
}
