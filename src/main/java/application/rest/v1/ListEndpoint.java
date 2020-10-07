package application.rest.v1;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

// import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import application.datasource.DataSource;
import application.json.JSONObjectFactory;
import application.metrics.SongsMetrics;

// @CrossOriginResourceSharing(allowAllOrigins = true)
@Path("/v1/songs")
public class ListEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listSongs() {
      System.out.println("List endpoint");

      SongsMetrics.requests.labels("/list").inc();
      
      JsonArray array = JSONObjectFactory.getInstance().generateJSONArray(DataSource.getInstance().fetchAllSongs());

      HashMap<String, JsonArray> map = new HashMap<String, JsonArray>();
      map.put("songs", array);

      Gson gson = new Gson();
      String json = gson.toJson(map);
      System.out.println("JSON reponse: "+ json);
      
      return Response.ok(json).build();
    }

}
