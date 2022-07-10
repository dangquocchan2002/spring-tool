package edu.poly.reponsitory;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findByName(String name);
	Page<Category> findByName(String name, Pageable pageable);
	Page<Category> findByNameContaining(String name, Pageable pageable);
	
}
