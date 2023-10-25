package com.example.soulbloom.response;
/**
 * Represents a login response containing a JSON Web Token (JWT).
 */
public class LoginResponse {
    private String jwt;

    /**
     * Constructs a new LoginResponse with the provided JWT.
     *
     * @param jwt The JSON Web Token to include in the response.
     */
    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Get the JSON Web Token (JWT) from the response.
     *
     * @return The JWT.
     */
    public String getJwt() {
        return jwt;
    }

    /**
     * Set the JSON Web Token (JWT) in the response.
     *
     * @param jwt The JWT to set in the response.
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}

