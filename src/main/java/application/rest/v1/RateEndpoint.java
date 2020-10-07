package application.rest.v1;

import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import application.datasource.DataSource;
import application.json.JSONObjectFactory;
import application.metrics.SongsMetrics;
import application.models.Song;
import application.models.SongRating;

@Path("/v1/songs/{id}")
public class RateEndpoint {

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response rateBook(@PathParam("id") int id, @FormParam("rating") int rating) {
    System.out.println("Rating endpoint - media type - " + MediaType.APPLICATION_FORM_URLENCODED + " - id: " + id + ", rating: " + rating);

    SongsMetrics.requests.labels("/rate").inc();

    Song result = null;
    String msg = null;

    if (rating < 1 || rating > 5) {
      System.out.println("Invalid rating: " + rating);
      msg = "Rating should be between 1 and 5.";
    } else {
      try {
        result = DataSource.getInstance().rateSong(id, rating);
      } catch (Exception e) {
        e.printStackTrace();
        msg = e.getMessage();
      }
    }

    JsonObject resp = null;
    if (result != null) {
      // response is the result (the updated song)
      resp = JSONObjectFactory.getInstance().generateJSON(result);
    } else {
      resp = new JsonObject();
      resp.addProperty("message", msg);
    }

    Gson gson = new Gson();
    String json = gson.toJson(resp);
    System.out.println("JSON reponse: " + json);

    return Response.ok(json).build();
  }

  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response rateBook(@PathParam("id") int id, SongRating songRating) {
    System.out.println("Rating endpoint - media type - " + MediaType.APPLICATION_JSON + " - id: " + id + ", rating: " + songRating.getRating());

    SongsMetrics.requests.labels("/rate").inc();

    Song result = null;
    String msg = null;

    int rating = songRating.getRating();

    if (rating < 1 || rating > 5) {
      System.out.println("Invalid rating: " + rating);
      msg = "Rating should be between 1 and 5.";
    } else {
      try {
        result = DataSource.getInstance().rateSong(id, rating);
      } catch (Exception e) {
        e.printStackTrace();
        msg = e.getMessage();
      }
    }

    JsonObject resp = null;
    if (result != null) {
      // response is the result (the updated song)
      resp = JSONObjectFactory.getInstance().generateJSON(result);
    } else {
      resp = new JsonObject();
      resp.addProperty("message", msg);
    }

    Gson gson = new Gson();
    String json = gson.toJson(resp);
    System.out.println("JSON reponse: " + json);

    return Response.ok(json).build();
  }

}
