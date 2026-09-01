package com.belentani.platform.repository;

import com.belentani.platform.entity.Analytics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio de analytics.
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {

    List<Analytics> findByEventType(String eventType);

    List<Analytics> findByEntityTypeAndEntityId(String entityType, Long entityId);

    List<Analytics> findByUserId(String userId);

    @Query("SELECT a FROM Analytics a WHERE a.createdAt BETWEEN :start AND :end")
    Page<Analytics> findByDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end, Pageable pageable);

    @Query("SELECT a.eventType, COUNT(a) FROM Analytics a WHERE a.createdAt BETWEEN :start AND :end GROUP BY a.eventType ORDER BY COUNT(a) DESC")
    List<Object[]> countEventsByTypeBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT a.country, COUNT(a) FROM Analytics a WHERE a.country IS NOT NULL GROUP BY a.country ORDER BY COUNT(a) DESC")
    List<Object[]> countEventsByCountry();

    @Query("SELECT a.deviceType, COUNT(a) FROM Analytics a WHERE a.deviceType IS NOT NULL GROUP BY a.deviceType ORDER BY COUNT(a) DESC")
    List<Object[]> countEventsByDeviceType();

    @Query("SELECT COUNT(a) FROM Analytics a WHERE a.eventType = :eventType AND a.createdAt > :since")
    Long countEventsSince(@Param("eventType") String eventType, @Param("since") LocalDateTime since);
}
