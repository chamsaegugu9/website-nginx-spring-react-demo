package com.example.springdemo.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@ConfigurationProperties("storage")
public class HelloBeerProperty {

    private String location = "upload-dir";


}
