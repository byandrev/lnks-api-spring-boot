package com.byandrev.lnks.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    private String msg;

    private int status;

    private Object body;

    private String error;

}
