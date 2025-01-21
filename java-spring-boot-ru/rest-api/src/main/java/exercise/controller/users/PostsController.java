package exercise.controller.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RequestMapping("/api/users")
@RestController
public class PostsController {

    private List<Post> posts = Data.getPosts();

    @GetMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> showUserPosts(@PathVariable int id) {
        return posts.stream().filter(i -> i.getUserId() == id).toList();
    }

    @PostMapping("/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@PathVariable int id, @RequestBody Post post) {
        post.setUserId(id);

        posts.add(post);

        return post;
    }
}
// END
