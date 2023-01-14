package host.ankh.jeyseylearn;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientProperties;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

@Sql(scripts = {"classpath:schema-hsqldb.sql", "classpath:data-hsqldb.sql"})
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JAXRSResourceBase {
    @Value("${local.server.port}")
    int port;

    Logger log = LoggerFactory.getLogger(JAXRSResourceBase.class);

    private Map<String, NewCookie> cookies;

    Invocation.Builder build(String path) {
        Invocation.Builder builder = ClientBuilder.newClient()
                .target("http://localhost:" + port + "/api" + path)
                .property(ClientProperties.FOLLOW_REDIRECTS, false)
                .request(MediaType.MULTIPART_FORM_DATA).accept(MediaType.TEXT_PLAIN);
        if (cookies != null) {
            // 如果cookies不为空，说明已经登录，把cookies都传进去
            for (var cookie:cookies.entrySet()) {
                builder.cookie(cookie.getValue());
            }
        }
        return builder;
    }

    void login() {
        Form form = new Form();
        form.param("username", "user");
        form.param("password", "user");
        Invocation.Builder builder = ClientBuilder.newClient()
                .target("http://localhost:" + port + "/login")
                .property(ClientProperties.FOLLOW_REDIRECTS, false)
                .request(MediaType.MULTIPART_FORM_DATA).accept(MediaType.TEXT_PLAIN);
        var res = builder.post(Entity.form(form));
        this.cookies = res.getCookies();
    }

    void logout() {
        // 登出时，把cookies都清掉
        this.cookies = null;
    }

    void authenticatedScope(Runnable runner) {
        try {
            login();
            runner.run();
        } finally {
            logout();
        }
    }

    Response get(String path) {
        var res = build(path).get();
        return res;
    }

    Response post(String path, Object entity) {
        return build(path).post(Entity.form((Form) entity));
    }


    static void assertBadRequest(Response response) {
        assert Response.Status.BAD_REQUEST.getStatusCode() == response.getStatus();
    }
    static void assertForbidden(Response response) {
        assert Response.Status.FORBIDDEN.getStatusCode() == response.getStatus();
    }
    static void assertRedirect(Response response) {
        assert Response.Status.FOUND.getStatusCode() == response.getStatus();
    }
    static void assertOK(Response response) {
        assert Response.Status.OK.getStatusCode() == response.getStatus();
    }
}
