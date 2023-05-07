package perobobbot.twitch.api.eventsub.event;

import io.micronaut.serde.annotation.Serdeable;
import lombok.Value;
import perobobbot.twitch.api.TwitchApiPayload;
import perobobbot.twitch.api.UserInfo;

@Value
@Serdeable
public class ChannelUnbanEvent implements BroadcasterProvider, EventSubEvent, TwitchApiPayload {
    UserInfo user;
    UserInfo broadcaster;
    UserInfo moderator;
}
