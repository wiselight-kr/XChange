package org.knowm.xchange.indodax;

import java.math.BigDecimal;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.indodax.dto.trade.IndodaxTradeResponse;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("/tapi")
@Produces(MediaType.APPLICATION_JSON)
public interface IndodaxAuthenticated {

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  IndodaxTradeResponse limitOrder(
      @HeaderParam("Key") String key,
      @HeaderParam("Sign") ParamsDigest sign,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce,
      @FormParam("method") String method,
      @FormParam("pair") String pair,
      @FormParam("type") String type,
      @FormParam("price") BigDecimal price);
}
