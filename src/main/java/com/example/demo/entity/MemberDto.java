package com.example.demo.entity;

import com.example.demo.swaggerutil.Enum2AllowableValues;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberDto {

    private Integer mid;

    private String phone;

    @Schema(description = "类型"
    )
    @Enum2AllowableValues(value = TypeEnum.class, method = "getValue:getDesc")
    private String type;


}
