package net.fatimazahramassane.billingservice.config;

import net.fatimazahramassane.billingservice.entities.Bill;
import net.fatimazahramassane.billingservice.entities.ProductItem;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, org.springframework.web.servlet.config.annotation.CorsRegistry cors) {
        config.exposeIdsFor(Bill.class, ProductItem.class);
    }
}
