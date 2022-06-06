package org.knowm.xchange.zb;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.zb.dto.trade.ZbOrderRequest;
import org.knowm.xchange.zb.dto.trade.ZbOrderResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.math.BigDecimal;

@Path("api")
@Produces(MediaType.APPLICATION_JSON)
public interface ZbAuthenticated {

    @POST
    @Path("order")
    @Consumes(MediaType.APPLICATION_JSON)
    ZbOrderResponse limitOrder(
            @QueryParam("accesskey") String apiKey,
            @QueryParam("amount") BigDecimal amount,
            @QueryParam("currency") String symbol,
            @QueryParam("method") String method,
            @QueryParam("price") BigDecimal price,
            @QueryParam("reqTime") Long reqTime,
            @QueryParam("sign") String sign,
            @QueryParam("tradeType") int tradeType
    ) throws IOException;
}
