package perobobbot.twitch.api.eventsub.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.serde.annotation.Serdeable;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@Serdeable
public class Outcome {
    @NonNull String id;
    @NonNull String title;
    @NonNull OutcomeColor color;
    int users;
    @JsonProperty("channel_points")
    int channelPoints;
    @JsonProperty("top_predictors")
    @NonNull List<TopPredictor> topPredictors;


    @java.beans.ConstructorProperties({"id", "title", "color", "users", "channelPoints", "topPredictors"})
    public Outcome(@NonNull String id, @NonNull String title,
                   @NonNull OutcomeColor color,
                   int users,
                   int channelPoints,
                   @Nullable  List<TopPredictor> topPredictors) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.users = users;
        this.channelPoints = channelPoints;
        this.topPredictors = topPredictors == null ? List.of():topPredictors;
    }
}
