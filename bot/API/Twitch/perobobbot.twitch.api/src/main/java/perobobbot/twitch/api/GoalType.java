package perobobbot.twitch.api;

import fpc.tools.lang.IdentifiedEnumWithAlternateIdentifications;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public enum GoalType implements IdentifiedEnumWithAlternateIdentifications {
    FOLLOW("follow","follower"),
    SUBSCRIPTION("subscription"),
    SUBSCRIPTION_COUNT("subscription_count"),
    NEW_SUBSCRIPTION("new_subscription"),
    NEW_SUBSCRIPTION_COUNT("new_subscription_count")
    ;

    @Getter
    private final @NonNull String identification;
    @Getter
    private final @NonNull Set<String> alternateIdentifications;

    GoalType(@NonNull String identification, String... alternateIdentifications) {
        this.identification = identification;
        this.alternateIdentifications = alternateIdentifications == null?Set.of():Set.of(alternateIdentifications);
    }
}
