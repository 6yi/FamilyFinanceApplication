package com.lzheng.familyfinance.domain;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * item
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@ApiModel("支出收入项目实体")
public class Item implements Serializable {
    @ApiModelProperty("ID")
    private Integer iId;

    @ApiModelProperty("项目名")
    private String iName;

    @ApiModelProperty("项目类型")
    private String iType;

    @ApiModelProperty("项目状态")
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