package com.br.jl.chatamizade;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;
import akka.cluster.sharding.typed.javadsl.EntityTypeKey;
import akka.persistence.typed.PersistenceId;
import akka.persistence.typed.javadsl.CommandHandlerWithReply;
import akka.persistence.typed.javadsl.EventHandler;
import akka.persistence.typed.javadsl.EventSourcedBehaviorWithEnforcedReplies;

import static com.br.jl.chatamizade.RoomCompanion.*;

public class RoomBehavior extends EventSourcedBehaviorWithEnforcedReplies<Command, Event, State> {

    public static final EntityTypeKey<Command> ENTITY_TYPE_KEY =
            EntityTypeKey.create(Command.class, RoomBehavior.class.getSimpleName());

    public RoomBehavior(String entityId) {
        super(PersistenceId.of(RoomBehavior.class.getSimpleName(), entityId, "|"));
    }

    @Override
    public State emptyState() {
        return null;
    }

    @Override
    public CommandHandlerWithReply<Command, Event, State> commandHandler() {
        return null;
    }

    @Override
    public EventHandler<State, Event> eventHandler() {
        return null;
    }
}
