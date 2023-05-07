package perobobbot.twitch.chat.message.from;

import fpc.tools.irc.IRCParsing;
import fpc.tools.lang.CastTool;
import lombok.Getter;
import perobobbot.twitch.chat.TwitchChannel;
import perobobbot.twitch.chat.message.IRCCommand;

/**
 * @author Bastien Aracil
 **/
@Getter
public abstract class HostTarget extends KnownMessageFromTwitch {


    private final int numberOfViewers;

    public HostTarget(IRCParsing ircParsing, int numberOfViewers) {
        super(ircParsing);
        this.numberOfViewers = numberOfViewers;
    }

    @Override
    public IRCCommand getCommand() {
        return IRCCommand.HOSTTARGET;
    }

    /**
     * @author Bastien Aracil
     **/
    @Getter
    public static class Start extends HostTarget {

        private final TwitchChannel hostingChannel;

        public Start(
                IRCParsing ircParsing,
                int numberOfViewers,
                TwitchChannel hostingChannel) {
            super(ircParsing, numberOfViewers);
            this.hostingChannel = hostingChannel;
        }

        @Override
        public <T> T accept(MessageFromTwitchVisitor<T> visitor) {
            return visitor.visit(this);
        }

    }

    /**
     * @author Bastien Aracil
     **/
    @Getter
    public static class Stop extends HostTarget {

        public Stop(IRCParsing ircParsing, int numberOfViewers) {
            super(ircParsing, numberOfViewers);
        }

        @Override
        public <T> T accept(MessageFromTwitchVisitor<T> visitor) {
            return visitor.visit(this);
        }
    }


    public static HostTarget build(AnswerBuilderHelper helper) {
        final String[] parameters = helper.lastParameter().split(" ");
        final int nbViewers = parameters.length<2? -1: CastTool.castToInt(parameters[1], -1);
        if (parameters[0].equals("-")) {
            return new Stop(helper.getIrcParsing(), nbViewers);
        }
        return new Start(helper.getIrcParsing(), nbViewers, TwitchChannel.create(parameters[0]));
    }
}
