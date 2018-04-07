package pl.sternik.dk.domi;

        import javax.sql.DataSource;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.InjectionPoint;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.ComponentScan;
        import org.springframework.context.annotation.ComponentScan.Filter;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.context.annotation.FilterType;
        import org.springframework.context.annotation.ImportResource;
        import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = { "pl.sternik.dk.domi" }, excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "pl\\.sternik\\.dk\\.domi\\.web\\..*") })
@ImportResource({"classpath:/applicationContext.xml"})//,"classpath:/database-config.xml"})
public class SpringBusinessConfig {

     @Bean
     @Scope("prototype")
     public Logger logger(InjectionPoint injectionPoint) {
                       Class<?> containingClass = injectionPoint.getField().getDeclaringClass();
                       return LoggerFactory.getLogger(containingClass);
                  }

}