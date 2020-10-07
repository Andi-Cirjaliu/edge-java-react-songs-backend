package application.rest;

import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import application.metrics.SongsMetrics;

@Path("/rateTest")
public class TestRateEndpoint {

  @GET
  @Produces({ MediaType.TEXT_HTML })
  public InputStream getIndex() {
    System.out.println("Test rate endpoint - html");
    
    SongsMetrics.requests.labels("/rate/test").inc();

    try {
      return this.getClass().getResourceAsStream("/rate.html");
    } catch (Exception e) {
      throw new RuntimeException("Exception returning rate.html", e);
    }
  }
}
