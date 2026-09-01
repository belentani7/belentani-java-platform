package com.belentani.platform.repository;

import com.belentani.platform.entity.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de recursos educativos.
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findByCategory(String category);

    List<Resource> findBySubcategory(String subcategory);

    List<Resource> findByLanguage(String language);

    List<Resource> findByType(Resource.ResourceType type);

    List<Resource> findByFree(boolean free);

    List<Resource> findByPublished(boolean published);

    List<Resource> findByFeatured(boolean featured);

    @Query("SELECT r FROM Resource r WHERE r.published = true ORDER BY r.viewCount DESC")
    Page<Resource> findMostViewed(Pageable pageable);

    @Query("SELECT r FROM Resource r WHERE LOWER(r.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(r.description) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Resource> searchResources(@Param("query") String query, Pageable pageable);

    @Query("SELECT r.category, COUNT(r) FROM Resource r WHERE r.published = true GROUP BY r.category ORDER BY COUNT(r) DESC")
    List<Object[]> countResourcesByCategory();

    @Query("SELECT COUNT(r) FROM Resource r WHERE r.published = true")
    Long countPublishedResources();

    @Query("SELECT COUNT(DISTINCT r.language) FROM Resource r WHERE r.published = true AND r.language IS NOT NULL")
    Long countAvailableLanguages();
}
