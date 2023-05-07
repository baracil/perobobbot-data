package perobobbot.bus.impl;

import fpc.tools.lang.Subscription;
import fpc.tools.lang.SubscriptionHolder;
import lombok.RequiredArgsConstructor;
import perobobbot.api.PerobobbotExecutors;
import perobobbot.bus.api.Consumer;
import perobobbot.bus.api.Message;
import perobobbot.bus.api.Topic;

import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.LinkedBlockingDeque;

@RequiredArgsConstructor
public class SimpleConsumer<T> implements Consumer<T> {

    private final PerobobbotExecutors executors;
    private final BlockingDeque<Message<T>> pending = new LinkedBlockingDeque<>();

    private final SubscriptionHolder subscription = new SubscriptionHolder();

    public SimpleConsumer(SimpleBus bus,
                          PerobobbotExecutors executors,
                          Set<Topic> topics,
                          Class<T> payloadType) {
        this.executors = executors;
        this.subscription.replace(() ->
                topics.stream()
                      .map(t -> bus.addListener(t, payloadType, pending::addLast))
                      .reduce(Subscription.NONE, Subscription::then)
        );
    }


    @Override
    public Message<T> receive() throws InterruptedException {
        return pending.take();
    }

    @Override
    public CompletionStage<Message<T>> receiveAsync() {
        final var future = new CompletableFuture<Message<T>>();
        executors.submit(() -> {
            try {
                final var value = pending.take();
                future.complete(value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                future.completeExceptionally(e);
            } catch (Throwable t) {
                future.completeExceptionally(t);
            }

        });
        return future;
    }

    @Override
    public void close() {
        subscription.unsubscribe();
    }
}
