//SHIVAAAAHHH


package com.iemms.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemms.entity.Equipment;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment,Long> {
	long countByStatus(String status);
	Page<Equipment> findByStatus(String status,Pageable pegeable);
	@Query("""
		    SELECT e FROM Equipment e
		    WHERE
		        (:status IS NULL OR e.status = :status)
		        AND
		        (
		            :search IS NULL
		            OR LOWER(e.name) LIKE LOWER(CONCAT('%', :search, '%'))
		            OR LOWER(e.equipmentCode) LIKE LOWER(CONCAT('%', :search, '%'))
		            OR LOWER(e.manufacturer) LIKE LOWER(CONCAT('%', :search, '%'))
		            OR LOWER(e.type) LIKE LOWER(CONCAT('%', :search, '%'))
		        )
		    """)
		Page<Equipment> searchEquipment(
		        @Param("status") String status,
		        @Param("search") String search,
		        Pageable pageable
		);
}
