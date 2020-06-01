package com.lzheng.familyfinance.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * tips
 * @author 
 */
@Data
public class Tips implements Serializable {
    private Integer tId;

    private String tTitle;

    private String tBody;

    private Integer status;

    private static final long serialVersionUID = 1L;
}