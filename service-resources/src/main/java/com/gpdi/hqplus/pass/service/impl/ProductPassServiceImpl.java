package com.gpdi.hqplus.pass.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gpdi.hqplus.common.constants.*;
import com.gpdi.hqplus.common.entity.ProcessCallbackBO;
import com.gpdi.hqplus.common.entity.UserContext;
import com.gpdi.hqplus.common.exception.ApplicationException;
import com.gpdi.hqplus.common.manager.MQService;
import com.gpdi.hqplus.common.manager.RedisService;
import com.gpdi.hqplus.common.util.JsonUtil;
import com.gpdi.hqplus.common.util.QRCodeUtil;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.pass.constans.ProductPassAddressType;
import com.gpdi.hqplus.pass.constans.ProductPassConfig;
import com.gpdi.hqplus.pass.constans.ProductPassEnum;
import com.gpdi.hqplus.pass.entity.ProductPass;
import com.gpdi.hqplus.pass.mapper.ProductPassMapper;
import com.gpdi.hqplus.pass.query.ProductPassQuery;
import com.gpdi.hqplus.pass.service.IProductPassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gpdi.hqplus.pass.util.ProductPassEnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zake
 * @since 2019-07-05
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductPassServiceImpl extends ServiceImpl<ProductPassMapper, ProductPass> implements IProductPassService {

    @Autowired
    private RedisService redisService;

    private final String PRODUCT_PASS_TYPE = "PRODUCT_PASS_TYPE";

    @Autowired
    private IProductPassService passService;

    @Override
    public void submit(ProductPass pass) {
        baseMapper.insert(pass);
//        //启动流程
//        ProcessStartBO bo = new ProcessStartBO();
//        bo.setBusinessKey(pass.getId());
//        bo.setUserRedisKey(UserContext.get().getRedisKey());
//        bo.setCallbackQueueName(ProductPassRabbitMqQueue.PROCESS_START_PASS_REVIEW);
//        bo.setDefinitionKey(ProcessDefinitionEnum.PRODUCT_PASS_REVIEW.getKey());
//        bo.setName("物品放行");
//        String addressType = pass.getAddressType();
//        bo.setTaskType(pass.getAddressType());
//        bo.setSecurityContext(SecurityContextHolder.getContext());
//        bo.setProjectCode(UserContext.get().getProjectCode());
//        boolean sendObject = false;
//        if (ProductPassAddressType.PRODUCT_PASS_ADDRESS_APARTMENT.equals(addressType)) {
//            bo.setBusinessCode(BusinessCode.PROPERTY_PASS);
//            //公寓流程
//            sendObject = mqService.sendObject(RabbitMqQueue.PROCESS_START, bo);
//        } else if (ProductPassAddressType.PRODUCT_PASS_ADDRESS_OFFICE.equals(addressType)) {
//            bo.setBusinessCode(BusinessCode.PROPERTY_PASS_OFFICE);
//            //办公室流程
//            sendObject = mqService.sendObject(RabbitMqQueue.PROCESS_START_ADDRESS, bo);
//        }
//        if (!sendObject) {
//            log.error("send property maintenance apply 2 mq fail.");
//            throw new ApplicationException(ErrorCode.SERVICE_ERROR, "物品放行提交申请失败，请稍后再试");
//        }
    }

    @Override
    public Page list(Page page) {
        Long userID = UserContext.get().getId();
        log.info(userID+"========================="+userID);
        baseMapper.selectPage(page, new QueryWrapper<ProductPass>()
                .lambda()
                .eq(ProductPass::getUserId, userID)
                .orderByDesc(ProductPass::getCreateTime));

        return page;
    }


    @Override
    public Page listAll(ProductPassQuery query) {
        String[] review ={ProductPassConfig.STATUS_REVIEW,ProductPassConfig.ADDRESS_OFFICE_CUSTOMER};
        String[] finish ={ProductPassConfig.ADDRESS_APARTMENT_HOUSEKEEPER,ProductPassConfig.ADDRESS_OFFICE_SUPERVISOR};

        Page<ProductPass> page = new Page<>();
        page.setCurrent(query.getCurrent());
        page.setSize(query.getSize());

        LambdaQueryWrapper<ProductPass> queryWrapper = new QueryWrapper<ProductPass>().lambda()
                .orderByDesc(ProductPass::getId);

        // 高级条件查询
        if (StringUtil.isNotBlank(query.getAddress())) {
            queryWrapper.like(ProductPass::getAddress, query.getAddress());
        }
        if (StringUtil.isNotBlank(query.getPolicemenName())) {
            queryWrapper.eq(ProductPass::getPolicemenName, query.getPolicemenName());
        }
        if (StringUtil.isNotBlank(query.getPolicemenType())) {
            queryWrapper.like(ProductPass::getPolicemenType, query.getPolicemenType());
        }
        if (StringUtil.isNotBlank(query.getStatus())) {
            if (ProductPassConfig.STATUS_REVIEW.equals(query.getStatus())) {
               queryWrapper.in(ProductPass::getStatus,review);
            }else if (ProductPassConfig.ADDRESS_APARTMENT_HOUSEKEEPER.equals(query.getStatus())){
                queryWrapper.in(ProductPass::getStatus,finish);
            }else{
                queryWrapper.eq(ProductPass::getStatus, query.getStatus());
            }
        }

        if (query.getPassDateStart() != null) {
            queryWrapper.ge(ProductPass::getPassDate, query.getPassDateStart());
        }
        if (query.getPassDateEnd() != null) {
            queryWrapper.le(ProductPass::getPassDate, query.getPassDateEnd());
        }
        if (query.getCreateTimeStart() != null) {
            queryWrapper.ge(ProductPass::getCreateTime, query.getCreateTimeStart());
        }

        if (query.getCreateTimeEnd() != null) {
            queryWrapper.le(ProductPass::getCreateTime, query.getCreateTimeEnd());
        }

        if (query.getFinishTimeStart() != null) {
            queryWrapper.ge(ProductPass::getFinishTime, query.getFinishTimeStart());
        }

        if (query.getFinishTimeEnd() != null) {
            queryWrapper.le(ProductPass::getFinishTime, query.getFinishTimeEnd());
        }

        baseMapper.selectPage(page, queryWrapper);

        return page;
    }


    @Override
    public void update(ProcessCallbackBO callbackBO) throws Exception {
        Long businessKey = callbackBO.getBusinessKey();
        String redisKey = RedisConstants.FILE + businessKey;
        ProductPass productPass = baseMapper.selectById(businessKey);
        if (productPass == null) {
            return;
        }
        //status=======processPointCode
        String processPointCode = callbackBO.getProcessPointCode();
        if (StringUtil.isNotBlank(processPointCode)) {
            ProductPassEnum passEnum = ProductPassEnumUtil.getByStatus(processPointCode);
            if (passEnum == null) {
                return;
            }
            String status = passEnum.getStatus();
            if (status.equals(ProductPassEnum.ADDRESS_OFFICE_SUPERVISOR) ||
                    status.equals(ProductPassEnum.ADDRESS_APARTMENT_HOUSEKEEPER)) {
                //状态为客服主管审批完成和管家审批完成 生成二维码
                byte[] bytes = QRCodeUtil.createZxing(businessKey.toString());
                redisService.set(redisKey, bytes);
                productPass.setCodeSrc(redisKey);
            }
            productPass.setStatus(status);
        }

        String taskId = callbackBO.getTaskId();
        if (StringUtil.isNotBlank(taskId)) {
            productPass.setTaskId(taskId);
        }

        boolean update = productPass.updateById();
        if (!update) {
            log.error("update ProductPass fail");
        }
    }

    @Override
    public ProductPass handUpdate(String productPassId) throws Exception {
        ProductPass productPass = verify(productPassId);
        String status = productPass.getStatus();
        String addressType = productPass.getAddressType();
        if (addressType.equals(ProductPassAddressType.PRODUCT_PASS_ADDRESS_APARTMENT)) {
            //是公寓类型的流程审批
            switch (status) {
                case ProductPassConfig.STATUS_REVIEW:
                    //管家审批通过(生成二维码)
                    passService.houseKeeper(productPassId);
                    break;
                case ProductPassConfig.ADDRESS_APARTMENT_HOUSEKEEPER:
                    //保安同意放行
                    passService.policemenPass(productPassId);
                    break;
                default:
                    break;
            }
        } else if (addressType.equals(ProductPassAddressType.PRODUCT_PASS_ADDRESS_OFFICE)) {
            //写字楼流程审批
            switch (status) {
                case ProductPassConfig.STATUS_REVIEW:
                    //客服审批通过
                    passService.addressOfficeCustomer(productPassId);
                    break;
                case ProductPassConfig.ADDRESS_OFFICE_CUSTOMER:
                    //客服主管审批通过 生成二维码
                    passService.addressOfficeSupervisor(productPassId);
                    break;
                case ProductPassConfig.ADDRESS_OFFICE_SUPERVISOR:
                    //保安同意放行
                    passService.policemenPass(productPassId);
                    break;
                default:
                    break;
            }
        }
        boolean update = productPass.updateById();
        if (!update) {
            log.error("update ProductPass fail");
        }
        return productPass;
    }

    @Override
    public void handFail(String id) {
        ProductPass productPass = verify(id);
        String status = productPass.getStatus();
        String addressType = productPass.getAddressType();
        if (addressType.equals(ProductPassAddressType.PRODUCT_PASS_ADDRESS_APARTMENT)) {
            //是公寓类型的流程审批
            switch (status) {
                case ProductPassConfig.STATUS_REVIEW:
                    //管家审批不通过(生成二维码)
                    passService.houseKeeperFail(productPass);
                    break;
                case ProductPassConfig.ADDRESS_APARTMENT_HOUSEKEEPER:
                    //保安不同意放行
                    passService.policemenPassFail(productPass);
                    break;
                default:
                    break;
            }
        } else if (addressType.equals(ProductPassAddressType.PRODUCT_PASS_ADDRESS_OFFICE)) {
            //写字楼流程审批
            switch (status) {
                case ProductPassConfig.STATUS_REVIEW:
                    //客服审批不通过
                    passService.addressOfficeCustomerFail(productPass);
                    break;
                case ProductPassConfig.ADDRESS_OFFICE_CUSTOMER:
                    //客服主管审批不通过
                    passService.addressOfficeSupervisorFail(productPass);
                    break;
                case ProductPassConfig.STATUS_POLICEMEN:
                    //保安不同意放行
                    passService.policemenPassFail(productPass);
                    break;
                default:
                    break;
            }
        }
        boolean update = productPass.updateById();
        if (!update) {
            log.error("update ProductPass fail");
        }
    }

    @Override
    public void deleteSubmit(String id) {
        ProductPass productPass = baseMapper.selectById(id);
        String status = productPass.getStatus();
        if (!(status.equals(ProductPassConfig.STATUS_REVIEW) ||
                status.equals(ProductPassConfig.ADDRESS_OFFICE_CUSTOMER) ||
                status.equals(ProductPassConfig.ADDRESS_APARTMENT_HOUSEKEEPER) ||
                status.equals(ProductPassConfig.ADDRESS_OFFICE_SUPERVISOR))) {
            throw new RuntimeException("当前状态不能取消申请");
        }
        productPass.setStatus(ProductPassConfig.STATUS_CANCEL);
        productPass.setUpdateTime(LocalDateTime.now());
        boolean update = productPass.updateById();
        if (!update) {
            log.error("update ProductPass fail");
        }
    }

    @Override
    public void changeStatus() {
        baseMapper.changeStatus();
    }

    /**
     * 客服审批   权限控制 之后加上
     *
     * @return
     */
    @PreAuthorize("hasRole('address_office_customer')")
    @Override
    public ProductPass addressOfficeCustomer(String id) {
        ProductPass productPass = baseMapper.selectById(id);
        //客服审批通过
        productPass.setStatus(ProductPassConfig.ADDRESS_OFFICE_CUSTOMER);
        productPass.setUpdateTime(LocalDateTime.now());
        return productPass;
    }

    /**
     * 客服主管审批    权限控制 之后加上
     *
     * @return
     */
    @PreAuthorize("hasRole('address_office_supervisor')")
    @Override
    public ProductPass addressOfficeSupervisor(String id) throws Exception {
        ProductPass productPass = baseMapper.selectById(id);
        //客服主管审批通过 生成二维码
        productPass.setStatus(ProductPassConfig.ADDRESS_OFFICE_SUPERVISOR);
        createCode(id, productPass);
        return productPass;
    }


    /**
     * 保安审批   权限控制 之后加上
     *
     * @return
     */
    @PreAuthorize("hasRole('status_policemen')")
    @Override
    public ProductPass policemenPass(String id) {
        ProductPass productPass = baseMapper.selectById(id);
        //保安同意放行
        productPass.setStatus(ProductPassConfig.STATUS_POLICEMEN);
        productPass.setPolicemenName(UserContext.get().getUserName());
        productPass.setFinishTime(LocalDateTime.now());
        productPass.setUpdateTime(LocalDateTime.now());
        return productPass;
    }


    /**
     * 管家审批   权限控制 之后加上
     *
     * @return
     */
    @PreAuthorize("hasRole('address_apartment_housekeeper')")
    @Override
    public ProductPass houseKeeper(String id) throws Exception {
        ProductPass productPass = baseMapper.selectById(id);
        //管家审批通过(生成二维码)
        productPass.setStatus(ProductPassConfig.ADDRESS_APARTMENT_HOUSEKEEPER);
        createCode(id, productPass);
        return productPass;
    }

    /**
     * 管家审批失败   权限控制 之后加上
     *
     * @return
     */
    @PreAuthorize("hasRole('address_apartment_housekeeper')")
    @Override
    public ProductPass houseKeeperFail(ProductPass productPass) {
        passFail(productPass);
        return productPass;
    }

    /**
     * 保安不同意放行   权限控制 之后加上
     *
     * @return
     */
    @PreAuthorize("hasRole('status_policemen')")
    @Override
    public ProductPass policemenPassFail(ProductPass productPass) {
        //保安不同意放行
        String userName = UserContext.get().getUserName();
        productPass.setPolicemenName(userName);
        passFail(productPass);
        return productPass;
    }

    /**
     * 客服审批不通过   权限控制 之后加上
     *
     * @return
     */
    @PreAuthorize("hasRole('address_office_customer')")
    @Override
    public ProductPass addressOfficeCustomerFail(ProductPass productPass) {
        //客服审批不通过
        passFail(productPass);
        return productPass;
    }

    /**
     * 客服主管审批不通过   权限控制 之后加上
     *
     * @return
     */
    @PreAuthorize("hasRole('address_office_supervisor')")
    @Override
    public ProductPass addressOfficeSupervisorFail(ProductPass productPass) {
        passFail(productPass);
        return productPass;
    }

    /**
     * 审核不通过的公共类
     *
     * @param productPass
     * @return
     */
    private ProductPass passFail(ProductPass productPass) {
        productPass.setStatus(ProductPassConfig.STATUS_FAIL);
        productPass.setAuditTime(LocalDateTime.now());
        productPass.setUpdateTime(LocalDateTime.now());
        productPass.setFinishTime(LocalDateTime.now());
        return productPass;
    }

    /**
     * 参数校验
     *
     * @return
     */
    private ProductPass verify(String id) {
        ProductPass productPass = baseMapper.selectById(id);
        String addressType = productPass.getAddressType();
        if (productPass == null) {
            throw new RuntimeException("该物品放行记录为空");
        }
        if (StringUtil.isBlank(addressType)) {
            throw new RuntimeException("状态不能为空");
        }
        if (StringUtil.isBlank(addressType)) {
            throw new RuntimeException("地址类型不能为空");
        }
        return productPass;
    }

    /**
     * 生成二维码
     */
    private ProductPass createCode(String id, ProductPass productPass) throws Exception {
        String codeId = id + ".png";
        String redisKey = RedisConstants.FILE + codeId;
        HashMap map = new HashMap<>(16);
        map.put("id", id);
        map.put("type", PRODUCT_PASS_TYPE);
        byte[] bytes = QRCodeUtil.createZxing(JsonUtil.bean2JsonString(map));
        redisService.set(redisKey, bytes);
        productPass.setCodeSrc(codeId);
        productPass.setAuditTime(LocalDateTime.now());
        productPass.setUpdateTime(LocalDateTime.now());
        return productPass;
    }
}
