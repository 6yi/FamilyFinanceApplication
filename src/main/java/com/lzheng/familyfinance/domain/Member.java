package com.lzheng.familyfinance.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * member
 * @author 
 */
@Data
public class Member implements Serializable {
    private Integer mId;

    private String mUsername;

    private String mPassword;

    private String mName;

    private String mType;

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