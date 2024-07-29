package com.aman.FullstackDemo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class RegisterDto {
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    private String phone;
    private String address;
    @Size(min = 6,message = "Minimum password length is 6 characters")
    private String password;
    private String confirmPassword;


    public String getFirstName(){
        return firstname;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
    public String getAddress(){
        return address;
    }
    public String getPassword(){
        return password;
    }
    public String getConfirmPassword(){
        return confirmPassword;
    }


    public void setFirstName(String firstName){
        this.firstname=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setConfirmPassword(String confirmPassword){
        this.confirmPassword=confirmPassword;
    }
}
