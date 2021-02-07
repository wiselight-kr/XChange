package org.knowm.xchange.mxc;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.mxc.dto.trade.MxcTradeRequest;
import org.knowm.xchange.mxc.dto.trade.MxcTradeResponse;

import java.io.IOException;

@Path("/open/api/v2/order/place")
@Produces(MediaType.APPLICATION_JSON)
public interface MxcAuthenticated {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  MxcTradeResponse limitOrder(
          @HeaderParam("api_key") String apiKey,
          @HeaderParam("req_time") String reqTime,
          @HeaderParam("sign") String sign,
          MxcTradeRequest request) throws IOException;
}
