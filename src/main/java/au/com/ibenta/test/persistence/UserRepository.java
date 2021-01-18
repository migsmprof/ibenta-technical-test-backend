package au.com.ibenta.test.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.ArrayList;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	public <S extends UserEntity> S save(UserEntity user);
	public Optional<UserEntity> findById(Long userId);
	public void deleteById(Long userId);
	public ArrayList<UserEntity> findAll();
}
