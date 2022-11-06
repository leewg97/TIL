package com.example.demospringbatch.core.service;

import com.example.demospringbatch.dto.PlayerDto;
import com.example.demospringbatch.dto.PlayerSalaryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerSalaryServiceTest {

    private PlayerSalaryService playerSalaryService;

    @BeforeEach
    public void setup() {
        playerSalaryService = new PlayerSalaryService();
    }

    @Test
    void calcSalary() {
        // Given
        Year mockYear = mock(Year.class);
        when(mockYear.getValue()).thenReturn(2022);
        Mockito.mockStatic(Year.class).when(Year::now).thenReturn(mockYear);

        PlayerDto mockPlayer = mock(PlayerDto.class);
        when(mockPlayer.getBirthYear()).thenReturn(1980);

        // When
        PlayerSalaryDto result = playerSalaryService.calcSalary(mockPlayer);

        // Then
        assertEquals(result.getSalary(), 42000000);

    }

}