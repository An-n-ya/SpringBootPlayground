package host.ankh.jeyseylearn.endpoint;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.springframework.stereotype.Service;

@Service
@Path("/hello")
public class HelloEndpoint {
    @GET
    @Produces("text/plain")
    public String hello() {
        return "hello from spring";
    }
}
