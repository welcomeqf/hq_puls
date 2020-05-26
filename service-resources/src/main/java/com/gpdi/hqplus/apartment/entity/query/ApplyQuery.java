package com.gpdi.hqplus.apartment.entity.query;

import com.gpdi.hqplus.apartment.entity.ApartmentApply;
import lombok.Data;

import java.util.List;

/**
 * @author: lianghb
 * @create: 2019-07-20 18:49
 **/
@Data
public class ApplyQuery extends ApartmentApply {
    private List<Long> ids;
}
