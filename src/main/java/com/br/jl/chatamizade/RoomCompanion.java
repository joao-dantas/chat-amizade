package com.br.jl.chatamizade;

import akka.actor.typed.Props;
import akka.cluster.sharding.typed.javadsl.EntityTypeKey;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

public class RoomCompanion {

    public static Props props() {
        return Props.empty().withDispatcherFromConfig("room-entity-dispatcher");
    }

    public abstract static class Command extends Message {
    }

    public abstract static class Event extends Message {
    }

    interface State {
    }

    @Builder
    @EqualsAndHashCode(callSuper = true)
    @Value
    public static class JoinParticipant extends Command {
        String participant;
    }
}
