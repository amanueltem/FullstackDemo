package com.aman.FullstackDemo.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    @Column(unique = true,nullable = false)
    private String email;

    private String phone;
    private String addrress;
    private String password;
    private String role;
    private Date createdAt;

    public int getId(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
   
    public String getAddress(){
        return addrress;
    }
    public String getPhone(){
        return phone;
    }
    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }
    public Date getCreatedAt(){
        return createdAt;
    }

    public void setId(int id){
        this.id=id;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setEmail(String email){
        this.email=email;
    }

    public void setAddress(String address){
        this.addrress=address;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setRole(String role){
        this.role=role;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt=createdAt;
    }
}
