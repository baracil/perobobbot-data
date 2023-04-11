package perobobbot.command.impl;

import fpc.tools.lang.ListTool;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import perobobbot.command.api.CommandDefinitionParsingFailure;

import java.util.List;

@RequiredArgsConstructor
public class ChainedCommandParser implements CommandParser {

    private final @NonNull List<CommandParser> parsers;

    @Override
    public @NonNull Command parse(@NonNull String commandDefinition) {
        final var suppressed = ListTool.<Throwable>arrayList();

        for (CommandParser parser : parsers) {
            try {
                return parser.parse(commandDefinition);
            } catch (CommandDefinitionParsingFailure error) {
                suppressed.add(error);
            }
        }
        final var ex = new CommandDefinitionParsingFailure("Fail to parse",commandDefinition);
        suppressed.forEach(ex::addSuppressed);
        throw ex;
    }
}
