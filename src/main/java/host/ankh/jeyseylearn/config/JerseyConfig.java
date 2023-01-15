package host.ankh.jeyseylearn.config;

import host.ankh.jeyseylearn.endpoint.AccountEndpoint;
import host.ankh.jeyseylearn.endpoint.HelloEndpoint;
import host.ankh.jeyseylearn.endpoint.ReverseReturnEndpoint;
import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HelloEndpoint.class);
        register(ReverseReturnEndpoint.class);
        register(AccountEndpoint.class);
    }
}

