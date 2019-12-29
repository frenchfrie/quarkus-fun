package org.frenchfrie;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.time.Instant;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/v1/data")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class DataController {

  private static final Logger log = LoggerFactory.getLogger(DataController.class);

  @GET
  @Transactional
  public Response getAll() {
    log.info("Received a get all request.");
    return Response.ok(Data.<Data>findAll().stream().map(DataDto::new).collect(toList())).build();
  }

  @POST
  @Transactional
  public void post(DataDto value) {
    log.info("Received a data post request with body:" + value);
    Data.persist(value.toEntity());
  }

  public static class DataDto {

    public Long id;
    public String name;
    public Instant creation;
    public Instant modification;

    public DataDto() {
      // default constructor for json-b
    }

    public DataDto(Data data) {
      id = data.id;
      name = data.name;
      creation = data.creation;
      modification = data.modification;
    }

    public Data toEntity() {
      Data entity = new Data();
      entity.id = id;
      entity.name = name;
      entity.creation = creation;
      entity.modification = modification;
      return entity;
    }

  }

}
