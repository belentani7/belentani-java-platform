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
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Album - Representa álbumes musicales.
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Entity
@Table(name = "albums")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String coverUrl;

    @Column(length = 100)
    private String artist;

    @Column(length = 100)
    private String genre;

    @Column(length = 50)
    private String releaseYear;

    @Column(length = 50)
    private String label;

    @Column(length = 500)
    private String spotifyUrl;

    @Column(length = 500)
    private String youtubeUrl;

    @Column(length = 500)
    private String appleMusicUrl;

    @Column(nullable = false)
    @Builder.Default
    private Integer trackCount = 0;

    @Column(nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal totalDuration = BigDecimal.ZERO;

    @Column(nullable = false)
    @Builder.Default
    private Long playCount = 0L;

    @Column(nullable = false)
    @Builder.Default
    private Long downloadCount = 0L;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private AlbumStatus status = AlbumStatus.DRAFT;

    @Column(nullable = false)
    @Builder.Default
    private boolean published = false;

    @Column(nullable = false)
    @Builder.Default
    private boolean featured = false;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("trackNumber ASC")
    @Builder.Default
    private Set<Track> tracks = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "album_tags", joinColumns = @JoinColumn(name = "album_id"))
    @Column(name = "tag")
    @Builder.Default
    private Set<String> tags = new HashSet<>();

    public enum AlbumStatus {
        DRAFT, RECORDING, MIXING, MASTERING, READY, PUBLISHED, ARCHIVED
    }

    public void addTrack(Track track) {
        this.tracks.add(track);
        track.setAlbum(this);
        this.trackCount = this.tracks.size();
    }

    public void removeTrack(Track track) {
        this.tracks.remove(track);
        track.setAlbum(null);
        this.trackCount = this.tracks.size();
    }
}
