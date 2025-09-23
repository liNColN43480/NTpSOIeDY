// 代码生成时间: 2025-09-24 06:20:59
import io.micronaut.core.annotation.Introspected;
    import io.micronaut.data.annotation.Id;
    import io.micronaut.data.annotation.MappedEntity;
    import io.micronaut.data.jdbc.annotation.JdbcRepository;
    import io.micronaut.data.model.query.builder.sql.Dialect;
    import io.micronaut.data.runtime.tx.annotation.Transactional;
    import io.micronaut.http.HttpResponse;
    import io.micronaut.http.annotation.Controller;
    import io.micronaut.http.annotation.Get;
    import io.micronaut.http.annotation.QueryValue;
    import java.sql.SQLException;
    import java.util.List;

    /**
     * Represents a simple user entity.
     */
    @Introspected
    @MappedEntity
    public class User {

        @Id
        private Long id;

        private String name;

        // Standard getters and setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * Interface for user repository operations.
     */
    @JdbcRepository(dialect = Dialect.POSTGRES)
    public interface UserRepository extends GenericRepository<User, Long> {

        List<User> findUsersByName(String name);
    }

    /**
     * Controller to handle HTTP requests.
     */
    @Controller(