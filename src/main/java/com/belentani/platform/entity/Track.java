package com.belentani.platform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad Track - Representa canciones individuales.
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Entity
@Table(name = "tracks")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    private Album album;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String lyrics;

    @Column(length = 500)
    private String audioUrl;

    @Column(length = 500)
    private String videoUrl;

    @Column(length = 500)
    private String coverUrl;

    @Column(nullable = false)
    @Builder.Default
    private Integer trackNumber = 0;

    @Column(nullable = false, precision = 5, scale = 2)
    @Builder.Default
    private BigDecimal duration = BigDecimal.ZERO;

    @Column(length = 100)
    private String genre;

    @Column(length = 100)
    private String mood;

    @Column(nullable = false)
    @Builder.Default
    private Long playCount = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long downloadCount = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long likeCount = 0L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private TrackStatus status = TrackStatus.DRAFT;

    @Column(nullable = false)
    @Builder.Default
    private boolean published = false;

    @Column(nullable = false)
    @Builder.Default
    private boolean explicit = false;

    @Column(nullable = false)
    @Builder.Default
    private boolean instrumental = false;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public enum TrackStatus {
        DRAFT, RECORDING, MIXING, MASTERING, READY, PUBLISHED, ARCHIVED
    }
}
