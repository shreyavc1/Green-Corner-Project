package com.jsp.the_green_corner.repository;

import com.jsp.the_green_corner.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant,Long> {

    @Query("""
SELECT p FROM Plant p
LEFT JOIN p.categories c
WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(p.localName) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(p.regionalName) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(p.botanicalName) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(c) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Plant> searchPlants(@Param("keyword") String keyword);


    @Query("SELECT p FROM Plant p WHERE p.rating=:rating ")
    List<Plant> findByRating(@Param("rating") double rating);

    @Query(nativeQuery=true, value = "SELECT * FROM plant WHERE rating BETWEEN :low AND :high")
    List<Plant> findByRatingRange(@Param("low") double low,@Param("high") double high);

    @Query("SELECT p FROM Plant p ORDER BY p.price ASC")
    List<Plant> findByLowToHighPrice();

    @Query("SELECT p FROM Plant p ORDER BY p.price DESC")
    List<Plant> findByHighToLowPrice();

    @Query("SELECT p FROM Plant p JOIN p.categories c WHERE LOWER(c) = LOWER(:category)")
//    @Query(nativeQuery = true,value="SELECT * FROM plant p " + " JOIN plant_categories pc ON p.id=pc.plant_id " + " WHERE pc.categories=:category")
    List<Plant> findByCategory(@Param("category") String category);

}
