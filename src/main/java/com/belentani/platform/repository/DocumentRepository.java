package com.belentani.platform.repository;

import com.belentani.platform.entity.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de documentos.
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    List<Document> findByOwnerId(Long ownerId);

    List<Document> findByType(String type);

    List<Document> findByCategory(String category);

    List<Document> findByStatus(Document.DocumentStatus status);

    List<Document> findByPublicAccess(boolean publicAccess);

    @Query("SELECT d FROM Document d WHERE LOWER(d.name) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Document> searchDocuments(@Param("query") String query, Pageable pageable);

    @Query("SELECT d.type, COUNT(d) FROM Document d GROUP BY d.type ORDER BY COUNT(d) DESC")
    List<Object[]> countDocumentsByType();

    @Query("SELECT SUM(d.size) FROM Document d WHERE d.status = 'ACTIVE'")
    Long getTotalStorageUsed();
}
