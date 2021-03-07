package com.br.jl.chatamizade;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.SpawnProtocol;
import akka.actor.typed.javadsl.Behaviors;
import akka.cluster.sharding.typed.ShardingEnvelope;
import akka.cluster.sharding.typed.javadsl.ClusterSharding;
import akka.cluster.sharding.typed.javadsl.Entity;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.quarkus.runtime.StartupEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import java.util.logging.Logger;

@ApplicationScoped
public class RoomServiceConfig {

    private static final Logger LOGGER = Logger.getLogger(RoomServiceConfig.class.getName());

    void startup(@Observes StartupEvent event) {
        LOGGER.config("EAGER LOADING BEANS" );
    }

    @PostConstruct
    public void start() {
        roomShardRegion();
    }

    @Produces
    @Singleton
    public Config akkaConfig() {
        return ConfigFactory.load();
    }

    @Produces
    @Singleton
    public ActorSystem<SpawnProtocol.Command> actorSystem() {
        Config config = akkaConfig();
        return ActorSystem.create(
                Behaviors.setup(ctx -> {
                    AkkaManagement.get(ctx.getSystem()).start();
                    ClusterBootstrap.get(ctx.getSystem()).start();
                    return SpawnProtocol.create();
                }), config.getString("application.cluster.name"));
    }

    @Produces
    @Singleton
    public ClusterSharding clusterSharding() {
        return ClusterSharding.get(actorSystem());
    }

    @Produces
    @Singleton
    public ActorRef<ShardingEnvelope<RoomCompanion.Command>> roomShardRegion() {
        return clusterSharding().init(Entity.of(
                RoomBehavior.ENTITY_TYPE_KEY,
                ctx -> new RoomBehavior(ctx.getEntityId())
        ).withEntityProps(RoomCompanion.props()));
    }

}
