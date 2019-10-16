package app.repositories;

import app.models.WallMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/** allows you to get a complete list of fields and find them by identifier
 *
 */

public interface MessageRepository extends CrudRepository<WallMessage, UUID> {
}
