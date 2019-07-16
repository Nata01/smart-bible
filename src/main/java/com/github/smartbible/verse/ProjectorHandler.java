package com.github.smartbible.verse;

import ratpack.handling.Context;
import ratpack.handling.Handler;

import static ratpack.jackson.Jackson.json;

public class ProjectorHandler implements Handler {

    private ProjectorData projectorData;

    public ProjectorHandler(ProjectorData projectorData) {
        this.projectorData = projectorData;
    }

    @Override
    public void handle(Context ctx) {
        ctx.render(json(projectorData));
    }
}
