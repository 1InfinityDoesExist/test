
package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Agencies;

@Repository
public interface AgencyRepository extends CrudRepository<Agencies, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update agencies  set delete_flag=true where id = ?1")
    public void closeAgency(Long id);

    @Override
    @Query("select Agencies from #{#entityName} Agencies where deleteFlag = false")
    public List<Agencies> findAll();
}
