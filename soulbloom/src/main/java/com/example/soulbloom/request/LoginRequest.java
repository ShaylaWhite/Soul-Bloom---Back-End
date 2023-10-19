package com.example.soulbloom.request;

/**
 * Represents a login request containing an email address and password.
 */
public class LoginRequest {
    private String emailAddress;
    private String password;

    /**
     * Get the email address from the login request.
     *
     * @return The email address.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Set the email address in the login request.
     *
     * @param emailAddress The email address to set.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Get the password from the login request.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password in the login request.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
