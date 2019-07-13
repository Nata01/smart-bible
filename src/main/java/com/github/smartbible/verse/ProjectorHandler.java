package com.github.smartbible.verse;

import ratpack.handling.Context;
import ratpack.handling.Handler;

public class ProjectorHandler implements Handler {

    private ProjectorData projectorData;

    public ProjectorHandler(ProjectorData projectorData) {
        this.projectorData = projectorData;
    }

    @Override
    public void handle(Context ctx) {
        ctx.render(projectorData.getText());
    }
}
