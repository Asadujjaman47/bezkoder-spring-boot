package com.bezkoder.repository;

import com.bezkoder.model.Tutorial;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    // basic query method
    List<Tutorial> findAll();


    // JPA find by field

//    by:
    Optional<Tutorial> findById(long id);

    List<Tutorial> findByLevel(int level);

    List<Tutorial> findByPublished(boolean isPublished);

    List<Tutorial> findByLevelIs(int level);

    List<Tutorial> findByLevelEquals(int level);

//    Not ot IsNot
    List<Tutorial> findByLevelNot(int level);

    List<Tutorial> findByLevelIsNot(int level);


//    JPA find by multiple Columns

    List<Tutorial> findByLevelAndPublished(int level, boolean isPublished);

    List<Tutorial> findByTitleOrDescription(String title, String description);


//    JPA Like Query

    List<Tutorial> findByTitleLike(String title);

    List<Tutorial> findByTitleStartingWith(String title);

    List<Tutorial> findByTitleEndingWith(String title);

    List<Tutorial> findByTitleContaining(String title);

    List<Tutorial> findByTitleContainingOrDescriptionContaining(String title, String description);

    List<Tutorial> findByTitleContainingAndPublished(String title, boolean isPublished);

//    IgnoreCase
    List<Tutorial> findByTitleContainingIgnoreCase(String title);


//    JPA Boolean Query

    List<Tutorial> findByPublishedTrue();

    List<Tutorial> findByPublishedFalse();


//    JPA Repository Query with Comparison

List<Tutorial> findByLevelGreaterThan(int level);

    List<Tutorial> findByCreatedAtGreaterThanEqual(Date date);

    List<Tutorial> findByCreatedAtAfter(Date date);

    List<Tutorial> findByLevelBetween(int start, int end);

    List<Tutorial> findByLevelBetweenAndPublished(int start, int end, boolean isPublished);

    List<Tutorial> findByCreatedAtBetween(Date start, Date end);


//    JPA Repository Query with Sorting

    List<Tutorial> findByOrderByLevel();
    // same as
    List<Tutorial> findByOrderByLevelAsc();

    List<Tutorial> findByOrderByLevelDesc();

    List<Tutorial> findByTitleContainingOrderByLevelDesc(String title);

    List<Tutorial> findByPublishedOrderByCreatedAtDesc(boolean isPublished);


    // Sort, Order
    List<Tutorial> findByTitleContaining(String title, Sort sort);

    List<Tutorial> findByPublished(boolean isPublished, Sort sort);

    List<Tutorial> findTop3ByTitleContainingAndPublished(String title, boolean isPublished);


//    JPA Repository Query with Pagination

    Page<Tutorial> findAll(Pageable pageable);

    Page<Tutorial> findByTitle(String title, Pageable pageable);


//    JPA Repository Query with Pagination and Sorting

//    Page<Tutorial> findAll(Pageable pageable);

    Page<Tutorial> findByTitleContaining(String title, Pageable pageable);


//    JPA Delete multiple Rows

    @Transactional
    void deleteAllByCreatedAtBefore(Date date);




}
