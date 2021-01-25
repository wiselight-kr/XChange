package xchange.mxc;

import xchange.mxc.service.MxcTradeResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.math.BigDecimal;

@Path("/open/api/v2/order/place")
@Produces(MediaType.APPLICATION_JSON)
public interface MxcAuthenticated {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    MxcTradeResponse limitOrder(
            @HeaderParam("api_key") String apiKey,
            @HeaderParam("req_time") String reqTime,
            @HeaderParam("sign") String sign,
            @FormParam("symbol") String symbol,
            @FormParam("price") BigDecimal price,
            @FormParam("quantity") BigDecimal quantity,
            @FormParam("trade_type") String trade_type,
            @FormParam("order_type") String order_type
    );

}
