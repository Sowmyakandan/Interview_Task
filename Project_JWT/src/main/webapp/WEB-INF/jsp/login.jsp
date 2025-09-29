<html>
<body>
<h2>Login</h2>
<form method="post" action="/login">
    Username: <input type="text" name="username"/><br/>
    Password: <input type="password" name="password"/><br/>
    <button type="submit">Login</button>
</form>
<p style="color:red">${error}</p>
</body>
</html>
