package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        long id = ctx.pathParamAsClass("id", Long.class).get();
        PostPage page = new PostPage(PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found")));

        ctx.render("posts/show.jte", model("page", page));
    }
    // END
}
