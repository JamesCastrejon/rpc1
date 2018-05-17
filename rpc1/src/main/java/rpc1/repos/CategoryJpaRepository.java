package rpc1.repos;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import rpc1.Category;

public interface CategoryJpaRepository extends JpaRepository<Category, Integer>  {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Category> findById(Integer id);
}
