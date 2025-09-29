package com.example.Project_JWT.payload;

public class JwtResponse {
    private String token;

    public JwtResponse() {}
    public JwtResponse(String token) { this.token = token; }

    // getter and setter
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
