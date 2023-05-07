package perobobobbot.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import perobobbot.command.impl.CommandParser;

import java.util.Map;
import java.util.stream.Stream;

public class CommandBindingDefinitionParserTest {

//    public static Pattern P = Pattern.compile("^play( +(?<title>('(?:[^ '\\\\]|\\\\.)+'|\"(?:[^ \"\\\\]|\\\\.)+\"|[^ \"']+))( +(?<volume>('(?:[^ '\\\\]|\\\\.)+'|\"(?:[^ \"\\\\]|\\\\.)+\"|[^ \"']+)))?)$");


    private static Stream<Arguments> testNumberOfParameters() {
        return Stream.of(
                Arguments.of("decho*", "decho twitch Hello World","twitch Hello World",Map.of()),
                Arguments.of("play {title} [volume]", "play chut", "chut", Map.of("title","chut")),
                Arguments.of("play {title} [volume]", "play \"chut chut\"", "\"chut chut\"", Map.of("title","chut chut")),
                Arguments.of("play {title} [volume]", "play 'super chut'", "'super chut'", Map.of("title","super chut")),
                Arguments.of("play {title} [volume]","play chut 10", "chut 10", Map.of("title","chut","volume","10")),
                Arguments.of("list {arg1} {title} {volume}","list oups chut 10", "oups chut 10", Map.of("arg1","oups","title","chut","volume","10")),
                Arguments.of("play [arg1] {arg2} [arg3]","play chut","chut", Map.of("arg2","chut")),
                Arguments.of("play","play","", Map.of()),
                Arguments.of("play {x},{y} [arg3]","play 120,23","120,23", Map.of("x","120","y","23")),
                Arguments.of("play {x},[y],{z}","play 120,,23","120,,23",Map.of("x","120","z","23")),
                Arguments.of("c4 start [player1] [player2]","c4 start a4", "a4", Map.of("player1","c4"))
        );
    }

    private CommandParser commandParser;

    @BeforeEach
    public void setUp() {
        this.commandParser = CommandParser.chain(CommandParser.fullMatch(), CommandParser.regexp());
    }

    @ParameterizedTest
    @MethodSource("testNumberOfParameters")
    public void shouldHaveRightNumberOfParameter(String commandDefinition, String message, String fullParameters, Map<String,String> expectedParameters) {
        final var command = commandParser.parse(commandDefinition);
        final var parsing = command.bind(message).orElse(null);
        Assertions.assertNotNull(parsing);
        Assertions.assertEquals(expectedParameters.size(), parsing.getNumberOfParameters());
    }
    @ParameterizedTest
    @MethodSource("testNumberOfParameters")
    public void shouldHaveRightParameterNames(String commandDefinition, String message, String fullParameters, Map<String,String> expectedParameters) {
        final var parsing = commandParser.parse(commandDefinition).bind(message).orElse(null);
        Assertions.assertNotNull(parsing);
        Assertions.assertEquals(expectedParameters.keySet(), parsing.getParameterNames());

    }
    @ParameterizedTest
    @MethodSource("testNumberOfParameters")
    public void shouldHaveRightParameterValues(String commandDefinition, String message, String fullParameters, Map<String,String> expectedParameters) {
        final var parsing = commandParser.parse(commandDefinition).bind(message).orElse(null);
        Assertions.assertNotNull(parsing);
        Assertions.assertEquals(expectedParameters.keySet(), parsing.getParameterNames());

    }

    @ParameterizedTest
    @MethodSource("testNumberOfParameters")
    public void shouldHaveRightFullParameters(String commandDefinition, String command, String fullParameters, Map<String,String> expectedParameters) {
        final var parsing = commandParser.parse(commandDefinition).bind(command).orElse(null);
        Assertions.assertNotNull(parsing);
        Assertions.assertEquals(fullParameters, parsing.getFullParameters());

    }
}
