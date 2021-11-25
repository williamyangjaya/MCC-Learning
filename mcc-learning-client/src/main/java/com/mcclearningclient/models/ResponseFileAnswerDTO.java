
package com.mcclearningclient.models;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResponseFileAnswerDTO {
    private String fileName;
    private String link;
    private String type;
    private long size;
    private Integer idEmployee;
    private String examName;
    private Date deadline;
    private Date uploadTime;
}
