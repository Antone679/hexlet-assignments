@import gg.jte.Content
@param Content content


<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
    <style>
        body {
            display: grid;
            grid-template-rows: auto 1fr auto;
            height: 100vh;
            margin: 0;
             text-align: center;
            }
        a {
        justify-content: center;
        }
    </style>
</head>
<body>
<header class="bg-light text-left py-3">
    <h1>MyLayoutsTesting</h1>
    <nav>
        <a href="/" class="btn btn-link">Home</a>
        <a href="/users" class="btn btn-link">Users</a>
    </nav>
</header>
<div class="content">
    ${content}
</div>
<footer class="text-center text-lg-start bg-light text-muted">
    <div class="text-center p-4" style="background-color: rgba(0, 0, 0, 0.05);">
        <p>&copy; 2025 Tony's Website. All rights reserved.</p>
    </div>
</footer>
</body>