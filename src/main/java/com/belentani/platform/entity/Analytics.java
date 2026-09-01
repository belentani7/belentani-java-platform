package com.belentani.platform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Entidad Analytics - Registra métricas y estadísticas.
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Entity
@Table(name = "analytics")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String eventType;

    @Column(length = 100)
    private String entityType;

    @Column
    private Long entityId;

    @Column(length = 100)
    private String userId;

    @Column(length = 100)
    private String sessionId;

    @Column(length = 100)
    private String ipAddress;

    @Column(length = 500)
    private String userAgent;

    @Column(length = 500)
    private String referrer;

    @Column(length = 500)
    private String url;

    @Column(columnDefinition = "TEXT")
    private String metadata;

    @Column
    private Integer value;

    @Column(length = 50)
    private String country;

    @Column(length = 50)
    private String city;

    @Column(length = 10)
    private String language;

    @Column(length = 50)
    private String deviceType;

    @Column(length = 50)
    private String os;

    @Column(length = 50)
    private String browser;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
