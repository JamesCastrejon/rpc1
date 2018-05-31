package rpc1.repos;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import rpc1.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<User> findById(Integer id);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<User> findByUserName(String username);
}
