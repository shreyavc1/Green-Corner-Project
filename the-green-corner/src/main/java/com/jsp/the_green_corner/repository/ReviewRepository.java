package com.jsp.the_green_corner.repository;

import com.jsp.the_green_corner.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {


}
