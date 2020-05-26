package com.gpdi.hqplus.resources.feign.fallback;

import com.gpdi.hqplus.article.entity.vo.SettleFileVO;
import com.gpdi.hqplus.common.entity.vo.JsonResultVO;
import com.gpdi.hqplus.resources.feign.FileFeignClient;
import lombok.extern.slf4j.Slf4j;

/**
 * FileFeignClient 容错回调
 *
 * @author: lianghb
 * @create: 2019-05-23 10:31
 **/
@Slf4j
public class FileFeignClientFallback implements FileFeignClient {

    @Override
    public JsonResultVO listByResourceId(String resourceId) {
        log.error("FileFeignClientFallback.listByResourceId:get file resource fail");
        // throw new ApplicationException(ErrorCode.FEIGN_CONNECT_ERROR);
        return null;
    }

    @Override
    public JsonResultVO setByResourceId(Long resourceId, String[] fileNames) {
        log.error("FileFeignClientFallback.setByResourceId:set file resource fail");
        // throw new ApplicationException(ErrorCode.FEIGN_CONNECT_ERROR);
        return null;
    }

    @Override
    public JsonResultVO getFileNamesById(String resourceId) {
        log.error("FileFeignClientFallback.getFileNamesById:get fileNames fail");
        return null;
    }
}
