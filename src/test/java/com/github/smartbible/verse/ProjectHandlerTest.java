package com.github.smartbible.verse;

import org.junit.Test;
import ratpack.test.embed.EmbeddedApp;

import static org.junit.Assert.assertEquals;

public class ProjectHandlerTest {

    @Test
    public void testProjectHandler() throws Exception {
        ProjectorData projectorData = new ProjectorData();
        projectorData.setText("Handle project");
        EmbeddedApp.fromHandler(new ProjectorHandler(projectorData)).test(httpClient ->
                assertEquals("Handle project", httpClient.getText())
        );
    }
}
