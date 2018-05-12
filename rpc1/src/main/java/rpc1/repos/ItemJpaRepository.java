package rpc1.repos;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import rpc1.Item;

public interface ItemJpaRepository extends JpaRepository<Item, Integer> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Item> findById(int id);
	
	List<Item> findByNameLike(String name);
	
	List<Item> findByCost(double cost);
}
