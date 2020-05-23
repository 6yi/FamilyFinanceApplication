package com.lzheng.familyfinance.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * item
 * @author 
 */
@Data
public class Item implements Serializable {
    private Integer iId;

    private String iName;

    private String iType;

    private Integer status;

    private static final long serialVersionUID = 1L;




    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", iId=").append(iId);
        sb.append(", iName=").append(iName);
        sb.append(", iType=").append(iType);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}