package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ua.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
	
	@Query("SELECT r FROM Recipe r LEFT JOIN FETCH r.country WHERE r.id=:id")
	Recipe findOneCountryInited(@Param("id")int id);

	Recipe findByName(String name);
	@Query(value = "SELECT r FROM Recipe r LEFT JOIN FETCH r.country c WHERE c.id=:id",
			countQuery="SELECT count(r.id) FROM Recipe r LEFT JOIN r.country c WHERE c.id=:id")
	Page<Recipe> findAll(Pageable pageable);
	
}
