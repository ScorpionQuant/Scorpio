package org.scorpion.config;

import org.scorpion.pricer.PricingScheduler;
import org.scorpion.pricer.service.CalcHandler;
import org.scorpion.pricer.service.CalcHandlerImpl;
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
