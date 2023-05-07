package perobobbot.twitch.chat.impl._private;

import fpc.tools.advanced.chat.AdvancedIO;
import fpc.tools.advanced.chat.ReceiptSlip;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import perobobbot.api.data.JoinedChannel;
import perobobbot.twitch.chat.message.from.Part;
import perobobbot.twitch.chat.message.to.MessageToTwitch;

import java.util.Set;
import java.util.concurrent.CompletionStage;

@RequiredArgsConstructor
@Slf4j
public class PartAction implements ChatAction {

    private final String userId;
    private final Set<JoinedChannel> joinedChannels;
    private final JoinedChannel channelToPart;

    @Override
    public CompletionStage<AdvancedIO> execute(AdvancedIO io) {
        LOG.info("{} : part channel {}",userId,channelToPart.channelName());

        return io.sendCommand(MessageToTwitch.privateMsg(channelToPart.channelName(), "C'est l'heure de vous quitter"))
                 .thenCompose(c -> io.sendRequest(MessageToTwitch.part(channelToPart.channelName())))
                 .whenComplete(this::handleResult)
                .thenApply(r -> io);
    }

    private void handleResult(@Nullable ReceiptSlip<Part> part, @Nullable Throwable throwable) {
        if (part != null) {
            joinedChannels.remove(channelToPart);
        }
    }


}
