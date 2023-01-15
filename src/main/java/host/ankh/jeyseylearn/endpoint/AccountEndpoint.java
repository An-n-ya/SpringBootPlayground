package host.ankh.jeyseylearn.endpoint;

import host.ankh.jeyseylearn.domain.account.Account;
import host.ankh.jeyseylearn.domain.account.AccountRepository;
import host.ankh.jeyseylearn.infrastructure.jaxrs.CommonResponse;
import host.ankh.jeyseylearn.service.AccountService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Slf4j
public class AccountEndpoint {

    @Inject
    AccountService accountService;

    @POST
    @Path("/create")
    public Response create(@Valid Account account) {
        return CommonResponse.op(() -> accountService.createAccount(account));
    }

}
