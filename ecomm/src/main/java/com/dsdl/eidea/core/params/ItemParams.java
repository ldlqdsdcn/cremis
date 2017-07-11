package com.dsdl.eidea.core.params;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by 车东明 on 2017/5/27.
 */
@Getter
@Setter
public class ItemParams<T> implements Serializable {
    private QueryParams queryParams;
    private T itemPK;
}
