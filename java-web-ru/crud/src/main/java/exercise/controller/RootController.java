package exercise.controller;

import exercise.dto.posts.PostsPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import io.javalin.http.Context;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class RootController {
    public static void index(Context ctx) {
        ctx.render("index.jte");
    }

    public static void showAllPageable(Context ctx) {

        int pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);

        List<Post> posts = PostRepository.findAll(pageNumber, 5);
        PostsPage page;
        if (!posts.isEmpty()) {
            page = new PostsPage(posts, pageNumber);
        } else {
            page = new PostsPage(PostRepository.findAll(1, 5), 1);
        }
        ctx.render("posts/index.jte", model("page", page));
    }
}
