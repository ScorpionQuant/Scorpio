package app.config;

import app.pricer.PricingScheduler;
import app.pricer.service.CalcHandler;
import app.pricer.service.CalcHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PricingConfig {

    @Bean
    public CalcHandler calcHandler() {
        return new CalcHandlerImpl();
    }

    @Bean
    public PricingScheduler pricingScheduler() {
        return new PricingScheduler();
    }
}
