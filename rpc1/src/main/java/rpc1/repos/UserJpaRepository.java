package rpc1.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import rpc1.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
