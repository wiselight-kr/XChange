package org.knowm.xchange.mxc;

import java.io.IOException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.knowm.xchange.mxc.dto.trade.MxcTradeRequest;
import org.knowm.xchange.mxc.dto.trade.MxcTradeResponse;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

@Path("/open/api/v2/order/place")
@Produces(MediaType.APPLICATION_JSON)
public interface MxcAuthenticated {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  MxcTradeResponse limitOrder(
      @QueryParam("api_key") String apiKey,
      @QueryParam("req_time") SynchronizedValueFactory<Long> reqTime,
      @HeaderParam("sign") ParamsDigest sign,
      MxcTradeRequest request)
      throws IOException;
}
