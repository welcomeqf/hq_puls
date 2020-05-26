package com.gpdi.hqplus.officebuild.entity.params;

import com.gpdi.hqplus.officebuild.entity.OfficeVO;
import lombok.Data;

import java.util.List;

/**
 * @Description 写字楼系列参数vo
 * @Author wzr
 * @CreateDate 2019-07-10
 * @Time 19:59
 */
@Data
public class OfficeParamsVO extends OfficeVO {

    private List<Long> equipmentIds;
}
