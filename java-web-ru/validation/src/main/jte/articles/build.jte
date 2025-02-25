@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage buildArticlePage

@template.layout.page(
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Создать новую статью:</h1>

            @if(buildArticlePage.getErrors() != null)

                <ul>
                    @for(var errors : buildArticlePage.getErrors().values())
                        @for(var error : errors)
                            <li>${error.getMessage()}</li>
                        @endfor
                    @endfor
                </ul>
            @endif

            <form class="table table-striped" action="/articles" method="POST">
                <div>
                    <label>
                        Title
                        <input type="text" name="title" value="${buildArticlePage.getTitle()}" required placeholder="article's name"/>
                    </label>
                </div>
                <div>
                    <label>
                        Text
                        <input type="text" value="${buildArticlePage.getContent()}" required name="content" placeholder="text"/>
                    </label>
                </div>
                <div>
                    <label>
                        <input type="submit" value="create"/>
                    </label>
                </div>

            </form>
        </main>
    </div>
`
)