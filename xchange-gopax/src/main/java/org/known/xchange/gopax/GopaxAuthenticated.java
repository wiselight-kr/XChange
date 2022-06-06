package org.known.xchange.gopax;

import org.known.xchange.gopax.dto.trade.GopaxTradeRequest;
import org.known.xchange.gopax.dto.trade.GopaxTradeResponse;
import si.mazi.rescu.ParamsDigest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
public interface GopaxAuthenticated {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    GopaxTradeResponse limitOrder(
            @HeaderParam("api-key") String apiKey,
            @HeaderParam("timestamp") Long timeStamp,
            @HeaderParam("signature") ParamsDigest signatureCreator,
            GopaxTradeRequest request
    ) throws IOException, GopaxException;
}
