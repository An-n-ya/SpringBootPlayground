package host.ankh.jeyseylearn.config;

import host.ankh.jeyseylearn.endpoint.AccountEndpoint;
import host.ankh.jeyseylearn.endpoint.HelloEndpoint;
import host.ankh.jeyseylearn.endpoint.ReverseReturnEndpoint;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.ext.Provider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
//        register(HelloEndpoint.class);
//        register(ReverseReturnEndpoint.class);
//        register(AccountEndpoint.class);
        scanPackages("host.ankh.jeyseylearn.endpoint");
        scanPackages("host.ankh.jeyseylearn.infrastructure.jaxrs");
    }

    /**
     * 自动注册目录下的Path，Provider到Jersey
     * @param scanPackage
     */
    private void scanPackages(String scanPackage) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Path.class));
        scanner.addIncludeFilter(new AnnotationTypeFilter(Provider.class));
        this.registerClasses(scanner.findCandidateComponents(scanPackage).stream()
                .map(beanDefinition -> ClassUtils.resolveClassName(Objects.requireNonNull(beanDefinition.getBeanClassName()), this.getClassLoader()))
                .collect(Collectors.toSet()));
    }

}

