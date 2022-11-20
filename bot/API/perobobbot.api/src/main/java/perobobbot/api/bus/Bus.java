package perobobbot.api.bus;

import io.micronaut.core.annotation.NonNull;

public interface Bus {

    int VERSION = 1;

    @NonNull
    void publishEvent(@NonNull Event event);
}
