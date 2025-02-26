package exercise.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // BEGIN
    public static String postsPath() {
        return "/posts";
    }

    public static String postWithIdPath() {
        return "/posts/{id}";
    }

    public static String editPostPath() {
        return "/posts/{id}/edit";
    }

    public static String newPostPath() {
        return "/posts/build";
    }

    // END
}
