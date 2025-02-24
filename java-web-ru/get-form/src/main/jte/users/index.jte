@import exercise.dto.users.UsersPage
@param UsersPage users

@template.layout.page(
content = @`
    <div class="container mt-4">
        <form action="/users" method="get" class="mb-4">
            <input type="search" name="term" value="${users.getTerm()}" class="form-control" placeholder="Search users..." />
            <input type="submit" value="Search" class="btn btn-primary mt-2" />
        </form>

        @if(!users.getUsers().isEmpty())
            <table class="table table-warning">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">Email</th>
                </tr>
                </thead>
                <tbody>
                @for(var user: users.getUsers())
                    <tr>
                        <th scope="row">${user.getId()}</th>
                        <td>${user.getFirstName()}</td>
                        <td>${user.getLastName()}</td>
                        <td>${user.getEmail()}</td>
                    </tr>
                @endfor
                </tbody>
            </table>
        @endif
    </div>
`
)