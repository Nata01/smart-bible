package com.github.smartbible.verse;


import ratpack.form.Form;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

public class Main {
    public static void main(String[] args) throws Exception {
        ProjectorData projectorData = new ProjectorData();
        RatpackServer.start(server -> server
                .serverConfig(ServerConfig.builder().development(true).port(8080))
                .handlers(chain -> chain
                        .get("projector", new ProjectorHandler(projectorData))
                        .path("admin", ctx -> {
                            if (ctx.getRequest().getMethod().isGet()) {
                                ctx.getResponse()
                                        .contentType("text/html")
                                        .send("<form method=\"post\">" +
                                                "<input name=\"text\" type=\"text\"/>" +
                                                "<button>Send</button>" +
                                                "</form>");
                            } else if (ctx.getRequest().getMethod().isPost()){
                                ctx.parse(Form.class).then(form -> projectorData.setText(form.get("text")));
                                ctx.redirect("admin");
                            }
                        })
                        .get(":name", ctx -> ctx.render("Hello " + ctx.getPathTokens().get("name") + "!"))
                )
        );
    }
}
