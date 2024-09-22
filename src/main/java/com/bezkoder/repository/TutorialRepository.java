package com.bezkoder.repository;

import com.bezkoder.model.Tutorial;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {


    // JPA native query Select with where condition example

    @Query(value = "SELECT * FROM tutorials", nativeQuery = true)
    List<Tutorial> findAll();

    @Query(value = "SELECT * FROM tutorials t WHERE t.published=?1", nativeQuery = true)
    List<Tutorial> findByPublished(boolean isPublished);

    @Query(value = "SELECT * FROM tutorials t WHERE t.title LIKE %?1%", nativeQuery = true)
    List<Tutorial> findByTitleLike(String title);

    @Query(value = "SELECT * FROM tutorials t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
    List<Tutorial> findByTitleLikeCaseInsensitive(String title);


    // JPA Native Query Greater Than or Equal To

    @Query(value = "SELECT * FROM tutorials t WHERE t.level >= ?1", nativeQuery = true)
    List<Tutorial> findByLevelGreaterThanEqual(int level);

    @Query(value = "SELECT * FROM tutorials t WHERE t.created_at >= ?1", nativeQuery = true)
    List<Tutorial> findByDateGreaterThanEqual(Date date);


    // JPA Native Query Between

    @Query(value = "SELECT * FROM tutorials t WHERE t.level BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Tutorial> findByLevelBetween(int start, int end);

    @Query(value = "SELECT * FROM tutorials t WHERE t.created_at BETWEEN ?1 AND ?2", nativeQuery = true)
    List<Tutorial> findByDateBetween(Date start, Date end);


    // JPA Native Query example with parameters

    @Query(value = "SELECT * FROM tutorials t WHERE t.published=:isPublished AND t.level BETWEEN :start AND :end", nativeQuery = true)
    List<Tutorial> findByLevelBetween(@Param("start") int start, @Param("end") int end, @Param("isPublished") boolean isPublished);

    @Query(value = "SELECT * FROM tutorials t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword,'%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword,'%'))", nativeQuery = true)
    List<Tutorial> findByTitleContainingOrDescriptionContainingCaseInsensitive(String keyword);

    @Query(value = "SELECT * FROM tutorials t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :title,'%')) AND t.published=:isPublished", nativeQuery = true)
    List<Tutorial> findByTitleContainingCaseInsensitiveAndPublished(String title, boolean isPublished);


    // JPA Native Query Order By Desc/Asc

    @Query(value = "SELECT * FROM tutorials t ORDER BY t.level DESC", nativeQuery = true)
    List<Tutorial> findAllOrderByLevelDesc();

    @Query(value = "SELECT * FROM tutorials t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%')) ORDER BY t.level ASC", nativeQuery = true)
    List<Tutorial> findByTitleOrderByLevelAsc(String title);

    @Query(value = "SELECT * FROM tutorials t WHERE t.published=true ORDER BY t.created_at DESC", nativeQuery = true)
    List<Tutorial> findAllPublishedOrderByCreatedDesc();


    // JPA Native Query Sort By
    // Spring Data JPA does not currently support dynamic sorting for native queries

    /* InvalidJpaQueryMethodException
    @Query(value = "SELECT * FROM tutorials t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
    List<Tutorial> findByTitleAndSort(String title, Sort sort);
    */


    // We can use Pageable object instead for dynamic sorting
    @Query(value = "SELECT * FROM tutorials t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))", nativeQuery = true)
    Page<Tutorial> findByTitleLike(String title, Pageable pageable);

    @Query(value = "SELECT * FROM tutorials t WHERE t.published=?1", nativeQuery = true)
    Page<Tutorial> findByPublished(boolean isPublished, Pageable pageable);


    // JPA Native Query Pagination

    @Query(value = "SELECT * FROM tutorials", nativeQuery = true)
    Page<Tutorial> findAllWithPagination(Pageable pageable);


    // JPA Native Query Update

    @Transactional
    @Modifying
    @Query(value = "UPDATE tutorials SET published=true WHERE id=?1", nativeQuery = true)
    int publishTutorial(Long id);
}
