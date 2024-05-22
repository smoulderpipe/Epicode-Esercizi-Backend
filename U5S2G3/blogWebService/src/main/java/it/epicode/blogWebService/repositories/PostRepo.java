package it.epicode.blogWebService.repositories;
import it.epicode.blogWebService.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface PostRepo extends JpaRepository<Post, UUID> {
}
