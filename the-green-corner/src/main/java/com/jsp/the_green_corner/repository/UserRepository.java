package com.jsp.the_green_corner.repository;

import com.jsp.the_green_corner.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("SELECT u FROM User u WHERE u.email=:email AND u.password=:password")
//    User findByEmailAndPassword(@Param("email")String email, @Param("password")String password);
      Optional<User> findByEmailAndPassword(String email, String password);


//    @Query("SELECT u FROM User u WHERE u.email=:email")
//    User findByEmail(@Param("email")String email);
    Optional<User> findByEmail(String email);

//    @Query("SELECT u FROM User u WHERE u.contact=:contact")
//    User findByContact(@Param("contact")Long contact);
    Optional<User> findByContact(Long contact);


    @Query("SELECT u FROM User u WHERE LOWER(REPLACE(u.name, ' ', '')) = LOWER(REPLACE(:name, ' ', ''))")
    Optional<User> findByName(@Param("name") String name);


//    @Query("SELECT u FROM User u WHERE u.name=:name")
//    List<User> findAllByName(@Param("name")String name);
    List<User> findAllByName(String name);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :id")
    int deleteUserById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.email=:email")
    int deleteByEmail(@Param("email")String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.contact=:contact")
    int deleteByContact(@Param("contact")Long contact);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.name=:name")
    int deleteByName(String name);
}

