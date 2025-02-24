@import exercise.dto.users.UsersPage
@param UsersPage page


@template.layout.page(
content = @`
    <p>Создать нового пользователя:</p>
    <form action="/users" method="POST">
        <div class="mb-3">
            <label class="form-label">
                Firstname:
                <input type="text" required name="firstName" />
            </label>
        </div>

        <div class="mb-3">
            <label class="form-label">
                Lastname:
                <input type="text" required name="lastName" />
            </label>
        </div>

        <div class="mb-3">
            <label class="form-label">
                Email:
                <input type="email" required name="email" />
            </label>
        </div>

        <div class="mb-3">
            <label class="form-label">
                Password:
                <input type="password" required name="password" />
            </label>
        </div>

        <input type="submit" class="btn btn-primary" value="Create!">
    </form>
`
)