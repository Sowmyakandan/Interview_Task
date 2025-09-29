<html>
<body>
<h2>Register</h2>
<form method="post" action="/register">
    Username: <input type="text" name="username"/><br/>
    Password: <input type="password" name="password"/><br/>
    <button type="submit">Register</button>
</form>
<p style="color:red">${error}</p>
<p style="color:green">${success}</p>
<a href="/login">Login</a>
</body>
</html>
