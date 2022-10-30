package com.example.lesson5

class User {

    private var password: String
    private var username: String
    private var lastname: String
    private var firstname: String

    constructor(firstname: String, lastname: String, username:String, password: String){
        this.firstname = firstname
        this.lastname = lastname
        this.username = username
        this.password = password
    }

    fun getFirstName(): String{
        return this.firstname
    }
    fun getLastName(): String{
        return this.lastname
    }
    fun getUsername(): String{
        return this.username
    }
    fun getPassword(): String{
        return this.password
    }

    override fun toString(): String {
        return "User(password='$password', username='$username', lastname='$lastname', firstname='$firstname')"
    }


}