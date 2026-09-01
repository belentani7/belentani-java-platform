package com.belentani.platform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Resource - Representa recursos educativos (Manos Abiertas).
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Entity
@Table(name = "resources")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 100)
    private String category;

    @Column(length = 100)
    private String subcategory;

    @Column(length = 500)
    private String url;

    @Column(length = 500)
    private String downloadUrl;

    @Column(length = 100)
    private String language;

    @Column(length = 100)
    private String author;

    @Column(length = 100)
    private String source;

    @Column(length = 500)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ResourceType type = ResourceType.DOCUMENT;

    @Column(nullable = false)
    @Builder.Default
    private boolean free = true;

    @Column(nullable = false)
    @Builder.Default
    private boolean published = false;

    @Column(nullable = false)
    @Builder.Default
    private boolean featured = false;

    @Column(nullable = false)
    @Builder.Default
    private Integer viewCount = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer downloadCount = 0;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "resource_tags", joinColumns = @JoinColumn(name = "resource_id"))
    @Column(name = "tag")
    @Builder.Default
    private Set<String> tags = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "resource_languages", joinColumns = @JoinColumn(name = "resource_id"))
    @Column(name = "language")
    @Builder.Default
    private Set<String> availableLanguages = new HashSet<>();

    public enum ResourceType {
        DOCUMENT, VIDEO, AUDIO, COURSE, TOOL, GUIDE, ARTICLE, LINK
    }
}
