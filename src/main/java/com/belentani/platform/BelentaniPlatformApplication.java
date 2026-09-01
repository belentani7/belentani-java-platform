package com.belentani.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Aplicación principal de la plataforma Belentani.
 * 
 * Ecosistema completo que integra:
 * - Judas Experience (música y arte)
 * - Manos Abiertas (educación)
 * - Duck Studio (producción musical)
 * - Belentani.cv-ai (documentos)
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableScheduling
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class BelentaniPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(BelentaniPlatformApplication.class, args);
    }
}
