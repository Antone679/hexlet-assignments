@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage
@param PostPage page

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${page.getPost().getName()}</h5>
                    <h5 class="card-title">${page.getPost().getId()}</h5>
                    <p class="card-text">${page.getPost().getBody()}</p>
                    <a href="${NamedRoutes.postsPath()}" class="card-link">Back to posts</a>
                </div>
            </div>
        </main>
    </div>
`
)

