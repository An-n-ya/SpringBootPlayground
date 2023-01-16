package host.ankh.jeyseylearn.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Secret {
    @Id
    public String SecretId;
    public String SecretKey;
}
