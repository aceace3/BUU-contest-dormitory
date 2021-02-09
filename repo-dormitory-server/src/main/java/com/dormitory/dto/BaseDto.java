package com.dormitory.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class BaseDto implements Serializable {
    private static final long serialVersionUID = -4442678485125288593L;

    private int id;


}