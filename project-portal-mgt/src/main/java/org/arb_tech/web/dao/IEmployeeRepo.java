package org.arb_tech.web.dao;

import org.arb_tech.web.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * IEmployeeRepo represents the DAO Layer 
 * @author Aniket.Bharsakale
 */
public interface IEmployeeRepo extends JpaRepository<Employee, Integer> {

}
