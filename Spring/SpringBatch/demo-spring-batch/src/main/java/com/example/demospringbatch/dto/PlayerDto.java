package com.example.demospringbatch.dto;

import lombok.Data;

@Data
public class PlayerDto {
    private String ID;
    private String lastName;
    private String firstName;
    private String position;
    private int birthYear;
    private int debutYear;
}
