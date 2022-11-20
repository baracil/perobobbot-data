package perobobbot.api.bus;

import fpc.tools.lang.Subscription;
import lombok.NonNull;

public interface EventDispatcher {
    int VERSION = 1;

    /**
     * Add a listener ot the bus
     * @param eventType the type of event the listener listen to
     * @param listener the listener
     * @return a subscription that can be used to remove the listener
     * @param <T> the type of the event
     */
    <T> Subscription addListener(@NonNull Class<T> eventType, @NonNull BusListener<? super T> listener);
}
