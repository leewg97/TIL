package com.dev.passweb.service.pass;

import com.dev.passweb.repository.pass.BulkPassStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BulkPass {

    private Integer bulkPassSeq;

    private String userGroupId;

    private Integer count;

    private BulkPassStatus status;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

}
