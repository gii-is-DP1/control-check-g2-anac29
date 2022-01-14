package org.springframework.samples.petclinic.feeding;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedingRepository extends CrudRepository<Feeding,Integer> {
    
	
	@Query("SELECT f from Feeding f")
	List<Feeding> findAll();
	@Query("SELECT ft from FeedingType ft ")
	List<FeedingType> findAllFeedingTypes();

	
	@Query("SELECT ft from FeedingType ft where ft.name= :name")
	FeedingType findFeedingByName(@Param("name") String name);
    Optional<Feeding> findById(int id);
    Feeding save(Feeding p);
}
