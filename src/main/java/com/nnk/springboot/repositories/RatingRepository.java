package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to perform CRUDs request to the rating table.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}