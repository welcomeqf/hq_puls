package com.gpdi.hqplus.resources.entity.query;

import com.gpdi.hqplus.resources.entity.ProductResource;
import lombok.Data;

/**
 * @author: lianghb
 * @create: 2019-07-06 14:23
 **/
@Data
public class ProductQuery extends ProductResource {
    private String[] fileNames;
    private String coverFile;
}
