package perobobbot.twitch.api.eventsub.event;

import io.micronaut.serde.annotation.Serdeable;
import io.micronaut.serde.config.naming.SnakeCaseStrategy;
import lombok.Value;
import perobobbot.twitch.api.TwitchApiPayload;
import perobobbot.twitch.api.UserInfo;

@Value
@Serdeable(naming = SnakeCaseStrategy.class)
public class ChannelRaidEvent implements BroadcasterProvider, EventSubEvent, TwitchApiPayload {

    UserInfo fromBroadcaster;
    UserInfo toBroadcaster;
    int viewers;

    @Override
    public UserInfo getBroadcaster() {
        return toBroadcaster;
    }
}
