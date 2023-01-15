package host.ankh.jeyseylearn.infrastructure.jaxrs;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * 返回体包装
 */
@Slf4j
public abstract class CommonResponse {
    // 获取相应的状态和message，构建Response
    public static Response send(Response.Status status, String message) {
        Integer code = status.getStatusCode();
        return Response.status(status)
                .type(MediaType.APPLICATION_JSON)
                .entity(new host.ankh.jeyseylearn.infrastructure.jaxrs.CodedMessage(code, message))
                .build();
    }

    // 对send的封装
    public static Response failure(String message) {
        return send(Response.Status.INTERNAL_SERVER_ERROR, message);
    }
    // 对send的封装
    public static Response success(String message) {
        return send(Response.Status.OK, message);
    }
    // 对send的封装
    public static Response success() {
        return send(Response.Status.OK, "success");
    }

    // 封装执行代码的操作，返回Response，自动处理错误
    public static Response op(Runnable runner, Consumer<Exception> exceptionConsumer) {
        try {
            runner.run();
            return success();
        } catch (Exception e) {
            exceptionConsumer.accept(e);
            return failure(e.getMessage());
        }
    }

    // 对op的封装
    public static Response op(Runnable runner) {
        // log.error用法：接受两个参数，错误信息String：e.getMessage(), 错误对象Throwable: e
        return op(runner, e -> log.error(e.getMessage(), e));
    }



}