package com.example.demospringbatch.job.parallel;

import com.example.demospringbatch.dto.AmountDto;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class AmountFieldSetMapper implements FieldSetMapper<AmountDto> {

    @Override
    public AmountDto mapFieldSet(FieldSet fieldSet) throws BindException {
        AmountDto amountDto = new AmountDto();
        amountDto.setIndex(fieldSet.readInt(0));
        amountDto.setName(fieldSet.readString(1));
        amountDto.setAmount(fieldSet.readInt(2));
        return amountDto;
    }
}
