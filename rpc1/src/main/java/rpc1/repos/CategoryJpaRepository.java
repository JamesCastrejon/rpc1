package rpc1.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import rpc1.Category;

public interface CategoryJpaRepository extends JpaRepository<Category, Integer> {

}
