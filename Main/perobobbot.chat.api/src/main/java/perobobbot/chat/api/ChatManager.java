package perobobbot.chat.api;

import lombok.NonNull;
import perobobbot.api.data.view.UserIdentity;

import java.util.Optional;

public interface ChatManager {

    @NonNull Optional<ChatIO> findChat(@NonNull UserIdentity userIdentity);
}
