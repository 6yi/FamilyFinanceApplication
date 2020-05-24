package com.lzheng.familyfinance.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * member
 * @author 6yi
 */
@Data
@ApiModel("成员实体")
public class Member implements Serializable {
    @ApiModelProperty("ID")
    private Integer mId;

    @ApiModelProperty("登陆用户名")
    private String mUsername;

    @ApiModelProperty("登陆密码")
    private String mPassword;

    @ApiModelProperty("用户昵称")
    private String mName;

    @ApiModelProperty("用户角色类型")
    private String mType;

    @ApiModelProperty("用户状态")
    private Integer status;

    private static final long serialVersionUID = 1L;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", mId=").append(mId);
        sb.append(", mUsername=").append(mUsername);
        sb.append(", mPassword=").append(mPassword);
        sb.append(", mName=").append(mName);
        sb.append(", mType=").append(mType);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}