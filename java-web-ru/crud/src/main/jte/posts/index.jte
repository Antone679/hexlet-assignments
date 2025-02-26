@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <ul>
                @for(var post : page.getPosts())
                    <li>
                        <div class="card" style="width: 40rem;">
                            <div class="card-body">
                                <h5 class="card-title"><a
                                            href="${NamedRoutes.postsPath() + "/" + post.getId()}">${post.getName()}</a></h5>
                            </div>
                        </div>
                    </li>
                @endfor

                @if(page.getPage() >= 2)
                    <a href="${NamedRoutes.postsPath()}/?page=${page.getPage() - 1}">Previous page</a>
                @endif
                @if (page.getPosts().size() >= 5)
                <a href="${NamedRoutes.postsPath()}/?page=${page.getPage() + 1}">Next page</a>
                    @endif
            </ul>
        </main>
    </div>
`
)
