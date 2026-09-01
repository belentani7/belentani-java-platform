package com.belentani.platform.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

/**
 * Configuración de caché para la plataforma.
 * Mejora el rendimiento cacheando datos frecuentes.
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public ConcurrentMapCacheManager cacheManager() {
        return new ConcurrentMapCacheManager(
            "users",
            "tracks",
            "albums",
            "resources",
            "documents"
        );
    }
}
