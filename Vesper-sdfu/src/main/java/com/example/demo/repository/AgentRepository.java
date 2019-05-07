
package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Agents;

@Repository
public interface AgentRepository extends JpaRepository<Agents, Long> {

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "update agents  set delete_flag=true where id = ?1")
	public void terminateAgent(Long id);

	@Override
	@Query("select Agents from #{#entityName} Agents where deleteFlag = false")
	public List<Agents> findAll();

}
