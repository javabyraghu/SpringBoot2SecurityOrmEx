package in.nareshit.raghu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.raghu.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	//SQL: select * from user where email=?
	Optional<User> findByUserMail(String userMail);

}
