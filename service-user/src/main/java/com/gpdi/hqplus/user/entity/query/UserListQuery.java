package com.gpdi.hqplus.user.entity.query;

import com.gpdi.hqplus.common.entity.BasePageQuery;
import lombok.Data;

/**
 * @author: lianghb
 * @create: 2019-07-01 22:31
 **/
@Data
public class UserListQuery extends BasePageQuery {
    private Params params;

    @Data
    public static class Params{
        private String phone;
        private String userName;
        private String roleCode;
        private String userType;
        private String deptCode;
    }
}
