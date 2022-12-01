package com.raj.Vo;

import com.raj.constants.CommonEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/26 10:54
 * FileName: Result
 * Description: 响应数据类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "结果类实体", description = "响应的数据类")
public class Result {
    /**
     * 编码
     */
    @ApiModelProperty("编码")
    private Integer code;
    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private Object data;
    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String msg;


    /**
     * 成功方法
     *
     * @return com.raj.Vo.Result
     */
    public static Result success() {
        return new Result(Integer.parseInt(CommonEnum.RESULT_CODE_SUCCESS.getValue()), null, null);
    }


    /**
     * 成功方法
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return new Result(Integer.parseInt(CommonEnum.RESULT_CODE_SUCCESS.getValue()), data, null);
    }

    /**
     * 成功方法
     *
     * @param data
     * @param msg
     * @return
     */
    public static Result success(Object data, String msg) {
        return new Result(Integer.parseInt(CommonEnum.RESULT_CODE_SUCCESS.getValue()), data, msg);
    }

    /**
     * 错误信息方法
     *
     * @param msg
     * @return
     */
    public static Result error(String msg) {
        return new Result(Integer.parseInt(CommonEnum.RESULT_CODE_FAIL.getValue()), null, msg);
    }


}
