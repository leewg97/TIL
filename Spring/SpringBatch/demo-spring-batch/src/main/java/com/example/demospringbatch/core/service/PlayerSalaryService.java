package com.example.demospringbatch.core.service;

import com.example.demospringbatch.dto.PlayerDto;
import com.example.demospringbatch.dto.PlayerSalaryDto;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class PlayerSalaryService {

    /**
     * PlayerDto 를 기반으로 Salary 를 계산하여 PlayerSalaryDto 로 변환
     */
    public PlayerSalaryDto calcSalary(PlayerDto player) {
        int salary = (Year.now().getValue() - player.getBirthYear()) * 1000000;
        return PlayerSalaryDto.of(player, salary);
    }

}
