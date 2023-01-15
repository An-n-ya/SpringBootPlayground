package host.ankh.jeyseylearn.domain.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import host.ankh.jeyseylearn.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Account{
    // 这里不能用GenerationType.AUTO. 为什么？ h2数据库可能不支持auto，所以只能用identity
    //
    //    GenerationType.IDENTITY
    //    此种主键生成策略就是通常所说的主键自增长,数据库在插入数据时,会自动给主键赋值,比如MySQL可以在创建表时声明"auto_increment" 来指定主键自增长。该策略在大部分数据库中都提供了支持(指定方法或关键字可能不同),但还是有少数数据库不支持,所以可移植性略差。使用自增长主键生成策略是只需要声明strategy = GenerationType.IDENTITY即可。
    //    GenerationType.AUTO
    //
    //    把主键生成策略交给持久化引擎(persistence engine),持久化引擎会根据数据库在以上三种主键生成策略中选择其中一种。此种主键生成策略比较常用,由于JPA默认的生成策略就是GenerationType.AUTO,所以使用此种策略时.可以显式的指定@GeneratedValue(strategy = GenerationType.AUTO)也可以直接@GeneratedValue。



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotNull(message = "用户名不允许为空")
    private String username;

    // 密码不允许更新，只允许插入）
    // 密码不允许序列化，只允许反序列化
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Column(updatable = false)
    @NotNull(message = "密码不能为空")
    private String password;

    private String name;

    private String telephone;

    private String email;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, String name, String telephone, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}