package com.github.smartbible.verse;


import ratpack.form.Form;
import ratpack.http.Status;
import ratpack.server.RatpackServer;
import ratpack.server.ServerConfig;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ratpack.jackson.Jackson.json;

public class Main {
    public static void main(String[] args) throws Exception {
        ProjectorData projectorData = new ProjectorData();
        VerseRepository verseRepository = new VerseRepository();
        ArrayList<ScriptDto> bible = new BibleReader().read("bible.json");
        VerseMapper verseMapper = new VerseMapper();
        List<Verse> verses = bible.stream()
                .flatMap(script -> verseMapper.map(script).stream())
                .collect(Collectors.toList());
        verses.forEach(verseRepository::save);
        RatpackServer.start(server -> server
                .serverConfig(ServerConfig.builder().development(true).port(8080))
                .handlers(chain -> chain
                        .prefix("api", apiChain -> apiChain
                                .get("projector", new ProjectorHandler(projectorData))
                        )
                        .get("projector", ctx -> ctx.getResponse().sendFile(Paths.get(Main.class.getClassLoader().getResource("public/projector.html").toURI())))
                        .path("admin", ctx -> {
                            if (ctx.getRequest().getMethod().isGet()) {
                                ctx.getResponse()
                                        .contentType("text/html")
                                        .send("<form method=\"post\">" +
                                                "<input name=\"scripture\" type=\"text\" placeholder=\"scripture\"/></br>" +
                                                "<input name=\"chapter-number\" type=\"number\"/></br>" +
                                                "<input name=\"verse-number\" type=\"number\"/></br>" +
                                                "<button>Send</button>" +
                                                "</form>");
                            } else if (ctx.getRequest().getMethod().isPost()){
                                ctx.parse(Form.class).then(form -> {
                                    Address address = new Address(form.get("scripture"), Integer.parseInt(form.get("chapter-number")), Integer.parseInt(form.get("verse-number")));
                                    Optional<Verse> verse = verseRepository.getByAddress(address);
                                    if (verse.isPresent()) {
                                        projectorData.setText(verse.get().getText());
                                        ctx.redirect("admin");
                                    } else {
                                        ctx.getResponse().status(Status.BAD_REQUEST).send();
                                    }
                                });
                            }
                        })
                        .get(":name", ctx -> ctx.render(json("Hello " + ctx.getPathTokens().get("name") + "!")))
                )
        );
    }
}
