@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
content = @`
    <div class="card">
    <p>${page.getUser().getFirstName() + " " + page.getUser().getLastName()}</p>
    <p>${page.getUser().getEmail() + ", id - " + page.getUser().getId()}</p>
    </div>
`
)