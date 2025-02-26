package exercise.dto.posts;

import java.util.List;
import exercise.model.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


// BEGIN
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public class PostsPage {
    public final List<Post> posts;
    public int page;
}
// END


