package com.roman.margolin.api.proxy.proxyapi.model;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Book implements Serializable {
    String id;
    String name;
    String path;
    Integer currentPage;
    LocalDate uploadedDate;
    String userId;
}
