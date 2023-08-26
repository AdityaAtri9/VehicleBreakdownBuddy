package com.example.vehiclebreakdownbuddy;

public class Helper {
    // Helper class is generated to store the information of the user.
    String Name,Email,Username,password;
    //declares four private instance variables of the class: name, email, username, and password.
    // These variables are used to store different pieces of information about a user.
    public Helper(String name, String email, String username, String password) {
        this.Name = name;
        this.Email = email;
        this.Username = username;
        this.password = password;
    }
    public Helper() {
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
    //getName  is another constructor that doesn't take any arguments. It's empty, meaning it doesn't do anything when called.
    //These are methods called "getters" and "setters." They allow you to access and modify the private variables name, email, username, and password.
    // The getName method returns the value of the name variable, and the setName method sets the value of the name variable.
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
// this code defines a class called Helper with private variables to store user information and getter/setter methods to
// access and modify that information.
// It's like a container to hold data about a user, and the getter/setter methods help you manage that data safely.