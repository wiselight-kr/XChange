package xchange.indodax;

import java.math.BigDecimal;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import xchange.indodax.service.IndodaxTradeResponse;

@Path("/tapi")
@Produces(MediaType.APPLICATION_JSON)
public interface IndodaxAuthenticated {

  @POST
  @FormParam("method")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  IndodaxTradeResponse limitOrder(
      @HeaderParam("apiKey") String apiKey,
      @HeaderParam("secretKey") String secretKey,
      @FormParam("method") String method,
      @FormParam("pair") String pair,
      @FormParam("type") String type,
      @FormParam("price") BigDecimal price,
      @FormParam("idr") BigDecimal idr);
}
