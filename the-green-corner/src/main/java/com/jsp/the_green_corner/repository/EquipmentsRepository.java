package com.jsp.the_green_corner.repository;

import com.jsp.the_green_corner.entity.Equipments;
import com.jsp.the_green_corner.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentsRepository extends JpaRepository<Equipments,Long> {

    @Query("SELECT e FROM Equipments e WHERE LOWER(e.name)=LOWER(:name)")
    List<Equipments> findAllByNameIgnoreCase(@Param("name") String name);

    @Query("SELECT e FROM Equipments e WHERE e.rating=:rating ")
    List<Equipments> findByRating(@Param("rating") double rating);

    @Query(nativeQuery=true, value = "SELECT * FROM equipments WHERE rating BETWEEN :low AND :high")
    List<Equipments> findByRatingRange(@Param("low")double low,@Param("high")double high);

    @Query("SELECT e FROM Equipments e ORDER BY e.price")
    List<Equipments> findByLowToHighPrice();

    @Query("SELECT e FROM Equipments e ORDER BY e.price DESC")
    List<Equipments> findByHighToLowPrice();

//    @Query("SELECT e FROM Equipments e JOIN e.categories c WHERE c =:category")
    @Query(nativeQuery = true,
        value = "SELECT * FROM equipments e " +
                "JOIN equipments_categories ec ON e.id = ec.equipments_id " +
                "WHERE ec.categories = :category")
    List<Equipments> findByCategory(@Param("category") String category);


}
