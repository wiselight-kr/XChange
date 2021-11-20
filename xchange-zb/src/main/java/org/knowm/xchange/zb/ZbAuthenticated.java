package org.knowm.xchange.zb;

import org.knowm.xchange.zb.dto.trade.ZbOrderRequest;
import org.knowm.xchange.zb.dto.trade.ZbOrderResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public interface ZbAuthenticated {

    @POST
    @Path("order")
    @Consumes(MediaType.APPLICATION_JSON)
    ZbOrderResponse limitOrder(
            @QueryParam("method") String method,
            @QueryParam("accesskey") String apiKey,
            @QueryParam("sign") String sign,
            @QueryParam("reqTime") Long reqTime,
            ZbOrderRequest request
    ) throws IOException;
}
