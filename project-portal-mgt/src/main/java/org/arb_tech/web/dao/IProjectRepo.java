package org.arb_tech.web.dao;

import org.arb_tech.web.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProjectRepo extends JpaRepository<Project, Integer> {

}
