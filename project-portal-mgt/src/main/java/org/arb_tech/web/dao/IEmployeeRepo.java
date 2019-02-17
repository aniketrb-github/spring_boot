package org.arb_tech.web.dao;

import org.arb_tech.web.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * IEmployeeRepo represents the DAO Layer 
 * @author Aniket.Bharsakale
 */
@Repository
public interface IEmployeeRepo extends JpaRepository<Employee, Integer> {

}
