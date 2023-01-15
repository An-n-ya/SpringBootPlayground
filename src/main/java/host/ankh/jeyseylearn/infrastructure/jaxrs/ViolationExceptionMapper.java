package host.ankh.jeyseylearn.infrastructure.jaxrs;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Provider
@Slf4j
public class ViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        log.warn("客户端传入了非法数据： " + e);
        var msg = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(";"));
        return CommonResponse.send(Response.Status.BAD_REQUEST, msg);
    }
}
