package com.belentani.platform.repository;

import com.belentani.platform.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio de álbumes con consultas avanzadas.
 * 
 * @author Pedro Belentani
 * @version 1.0.0
 */
@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByArtist(String artist);

    List<Album> findByGenre(String genre);

    List<Album> findByStatus(Album.AlbumStatus status);

    List<Album> findByPublished(boolean published);

    List<Album> findByFeatured(boolean featured);

    @Query("SELECT a FROM Album a WHERE a.published = true ORDER BY a.playCount DESC")
    Page<Album> findMostPlayed(Pageable pageable);

    @Query("SELECT a FROM Album a WHERE a.published = true ORDER BY a.createdAt DESC")
    Page<Album> findNewest(Pageable pageable);

    @Query("SELECT a FROM Album a WHERE LOWER(a.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(a.artist) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Album> searchAlbums(@Param("query") String query, Pageable pageable);

    @Query("SELECT a.genre, COUNT(a) FROM Album a WHERE a.published = true GROUP BY a.genre ORDER BY COUNT(a) DESC")
    List<Object[]> countAlbumsByGenre();

    @Query("SELECT SUM(a.playCount) FROM Album a WHERE a.published = true")
    Long getTotalPlayCount();

    @Query("SELECT SUM(a.downloadCount) FROM Album a WHERE a.published = true")
    Long getTotalDownloadCount();
}
