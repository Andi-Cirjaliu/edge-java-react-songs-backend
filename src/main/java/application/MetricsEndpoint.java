package application;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.Enumeration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import application.datasource.DataSource;
import application.metrics.SongsMetrics;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Collector.MetricFamilySamples;
import io.prometheus.client.exporter.common.TextFormat;

@Path("/metrics")
public class MetricsEndpoint {

  @GET
  @Produces( TextFormat.CONTENT_TYPE_004)
  // @Produces(MediaType.TEXT_PLAIN)
  public Response metrics() {
    System.out.println( new Date() + " - Get Metrics");

    String metrics = "";

    // generate statistics
    SongsMetrics.collectSongsStatistics();

    Enumeration<MetricFamilySamples> samples = CollectorRegistry.defaultRegistry.metricFamilySamples();

    //write samples in the correct format
    Writer writer = new StringWriter();
    try {
      TextFormat.write004(writer, samples);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        writer.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    metrics = writer.toString();
    System.out.println( new Date() + " - Metrics:\r\n" + metrics);

    return Response.ok(metrics).build();
  }

}
