package com.br.jl.chatamizade;

import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.AskPattern;
import akka.cluster.sharding.typed.ShardingEnvelope;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class RoomResource {

    @Inject
    ActorRef<ShardingEnvelope<RoomCompanion.Command>> roomShardingRegion;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy Reactive";
    }

    @PUT
    @Path("{participant}")
    public void join(@PathParam(value = "participant") String participant) {
    }
}