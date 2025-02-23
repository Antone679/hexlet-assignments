@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`

    @for(var user : page.getUsers())
        <div class="card"><a href = "users/${user.getId()}">${user.getFirstName() + " " + user.getLastName()}</a>
        <p>${user.getEmail() + ", id - " + user.getId()}</p>
        </div>
    @endfor
`
)
