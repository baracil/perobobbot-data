open module perobobbot.twitch.api {
    requires static lombok;
    requires java.desktop;

    requires com.google.common;

    requires org.slf4j;

    requires perobobbot.api;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.guava;
    requires com.fasterxml.jackson.datatype.jdk8;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires jakarta.inject;
    requires fpc.tools.jackson;
    requires io.micronaut.inject;
    requires io.micronaut.core;
    requires jakarta.annotation;

    requires io.micronaut.serde.serde_api;

    exports perobobbot.twitch.api;
    exports perobobbot.twitch.api.serde;
    exports perobobbot.twitch.api.eventsub;
    exports perobobbot.twitch.api.eventsub.event;
    exports perobobbot.twitch.api.eventsub.subscription;
}