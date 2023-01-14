package host.ankh.jeyseylearn.domain.auth;

public interface Role {
    // 不要加 ROLE_的前缀，不然会出问题
    String USER = "USER";
    String ADMIN = "ADMIN";
}
