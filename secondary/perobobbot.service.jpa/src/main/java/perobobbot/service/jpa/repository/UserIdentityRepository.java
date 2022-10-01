package perobobbot.service.jpa.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import lombok.NonNull;
import perobobbot.api.data.Platform;
import perobobbot.api.data.view.UserIdentity;
import perobobbot.service.api.UserNotFound;
import perobobbot.service.jpa.domain.UserIdentityEntity;

import java.util.Optional;

@Repository
public interface UserIdentityRepository extends JpaRepository<UserIdentityEntity,Long> {

    @NonNull Optional<UserIdentityEntity> findByPlatformAndUserId(@NonNull Platform platform, @NonNull String userId);

    @NonNull Optional<UserIdentityEntity> findByPlatformAndLogin(@NonNull Platform platform, @NonNull String login);

    default @NonNull Optional<UserIdentityEntity> findByPlatformAndUserId(@NonNull UserIdentity userIdentity) {
        return findByPlatformAndUserId(userIdentity.platform(),userIdentity.userId());
    }

    default @NonNull UserIdentityEntity getByPlatformAndUserId(@NonNull Platform platform, @NonNull String userId) {
        return findByPlatformAndUserId(platform,userId).orElseThrow(() -> new UserNotFound(platform,userId));
    }

    default @NonNull UserIdentityEntity getByPlatformAndLogin(@NonNull Platform platform, @NonNull String login) {
        return findByPlatformAndUserId(platform,login).orElseThrow(() -> new UserNotFound(platform,login));
    }
}