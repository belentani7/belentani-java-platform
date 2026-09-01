package com.belentani.platform.repository;

import com.belentani.platform.entity.Track;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de tracks.
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {

    List<Track> findByAlbumId(Long albumId);

    List<Track> findByGenre(String genre);

    List<Track> findByMood(String mood);

    List<Track> findByPublished(boolean published);

    @Query("SELECT t FROM Track t WHERE t.published = true ORDER BY t.playCount DESC")
    Page<Track> findMostPlayed(Pageable pageable);

    @Query("SELECT t FROM Track t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Track> searchTracks(@Param("query") String query, Pageable pageable);

    @Query("SELECT t.mood, COUNT(t) FROM Track t WHERE t.published = true GROUP BY t.mood ORDER BY COUNT(t) DESC")
    List<Object[]> countTracksByMood();
}
