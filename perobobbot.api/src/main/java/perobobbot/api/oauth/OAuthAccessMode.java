package perobobbot.api.oauth;

import lombok.NonNull;

import java.util.Optional;

public enum OAuthAccessMode {
    USER_ONLY,
    APP_ONLY,
    BOTH;

    public static @NonNull Optional<OAuthAccessMode> evaluateMode(boolean userAccepted, boolean applicationAccepted) {
        final var code = (applicationAccepted ? 1 : 0) + (userAccepted ? 2 : 0);
        final var mode = switch (code) {
            case 1 -> APP_ONLY;
            case 2 -> USER_ONLY;
            case 3 -> BOTH;
            default -> null;
        };
        return Optional.ofNullable(mode);
    }
}
