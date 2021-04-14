package com.roman.margolin.api.proxy.proxyapi.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    private String password;
    private String email;
    String id;
    List<Book> shelfBooks;
    String firstName;
    String lastName;
    String phone;

}
