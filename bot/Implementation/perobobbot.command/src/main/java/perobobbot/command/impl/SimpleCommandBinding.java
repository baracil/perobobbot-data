package perobobbot.command.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.NonNull;
import lombok.Value;
import perobobbot.command.api.CommandBinding;

import java.util.Optional;

@Value
public class SimpleCommandBinding implements CommandBinding {

    @NonNull String commandDefinition;
    @NonNull String fullParameters;
    @NonNull ImmutableMap<String,String> parameters;

    @Override
    public int getNumberOfParameters() {
        return parameters.size();
    }

    @Override
    public @NonNull ImmutableSet<String> getParameterNames() {
        return parameters.keySet();
    }

    @Override
    public @NonNull Optional<String> findParameterValue(@NonNull String name) {
        return Optional.ofNullable(parameters.get(name));
    }
}
