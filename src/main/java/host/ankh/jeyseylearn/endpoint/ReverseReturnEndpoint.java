package host.ankh.jeyseylearn.endpoint;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import org.springframework.stereotype.Service;

@Service
@Path("/reverse")
public class ReverseReturnEndpoint {

    @GET
    @Produces("text/plain")
    public String reverse(@QueryParam("data") @NotNull String data) {
        return new StringBuilder(data).reverse().toString();
    }
}
