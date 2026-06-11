package repository;

import model.userModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<userModel,Long> {
}
