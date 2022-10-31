package com.developers.dmaker.service;

import com.developers.dmaker.code.StatusCode;
import com.developers.dmaker.dto.CreateDeveloper;
import com.developers.dmaker.dto.DeveloperDetailDto;
import com.developers.dmaker.dto.DeveloperDto;
import com.developers.dmaker.dto.EditDeveloper;
import com.developers.dmaker.entity.Developer;
import com.developers.dmaker.entity.RetiredDeveloper;
import com.developers.dmaker.exception.DMakerException;
import com.developers.dmaker.repository.DeveloperRepository;
import com.developers.dmaker.repository.RetiredDeveloperRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.developers.dmaker.exception.DMakerErrorCode.DUPLICATED_MEMBER_ID;
import static com.developers.dmaker.exception.DMakerErrorCode.NO_DEVELOPER;

@Service
@RequiredArgsConstructor
public class DMakerService {

    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {
        validateCreateDeveloperRequest(request);
        return CreateDeveloper.Response.fromEntity(developerRepository.save(createDeveloperFromRequest(request)));
    }

    private Developer createDeveloperFromRequest(CreateDeveloper.Request request) {
        return Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .statusCode(StatusCode.EMPLOYED)
                .name(request.getName())
                .age(request.getAge())
                .build();
    }

    @Transactional(readOnly = true)
    public List<DeveloperDto> getAllEmployedDevelopers() {
        return developerRepository.findByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream().map(DeveloperDto::fromEntity)// 받아온 Developer 를 DeveloperDto 로 바꿔주는 매핑
                .collect(Collectors.toList());  // List<DeveloperDto>로 변경
    }

    @Transactional(readOnly = true)
    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return DeveloperDetailDto.fromEntity(getByDeveloperByMemberId(memberId));
    }

    private Developer getByDeveloperByMemberId(String memberId) {
        return developerRepository.findByMemberId(memberId).orElseThrow(
                () -> new DMakerException(NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
        request.getDeveloperLevel().validateExperienceYears(request.getExperienceYears());

        return DeveloperDetailDto.fromEntity(
                getUpdatedDeveloperFromRequest(
                        request, getByDeveloperByMemberId(memberId)
                )
        );
    }

    private Developer getUpdatedDeveloperFromRequest(EditDeveloper.Request request, Developer developer) {
        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        return developer;
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        Developer developer = developerRepository.findByMemberId(memberId).orElseThrow(
                () -> new DMakerException(NO_DEVELOPER)
        );
        developer.setStatusCode(StatusCode.RETIRED);

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();

        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);
    }

    private void validateCreateDeveloperRequest(@NonNull CreateDeveloper.Request request) {
        request.getDeveloperLevel().validateExperienceYears(request.getExperienceYears());

        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID);
                }));
    }

}
