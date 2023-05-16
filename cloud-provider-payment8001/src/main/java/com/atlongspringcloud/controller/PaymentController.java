package com.atlongspringcloud.controller;

import com.atlong.springcloud.entities.CommonResult;
import com.atlong.springcloud.entities.Payment;
import com.atlongspringcloud.server.PaymentServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentServer paymentServer;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentServer.create(payment);
        log.info("*****插入结果：" + result);

        if (result > 0){
            return new CommonResult(200,"插入数据成功",result);
        }else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment paymentByID = paymentServer.getPaymentByID(id);
        log.info("*****插入结果：" + paymentByID);

        if (paymentByID != null){
            return new CommonResult(200,"查询数据成功",paymentByID);
        }else {
            return new CommonResult(444,"没有对应的记录，查询ID:" + id,null);
        }
    }
}
