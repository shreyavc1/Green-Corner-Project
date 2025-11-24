package com.jsp.the_green_corner.repository;

import com.jsp.the_green_corner.entity.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs,Long> {

}
