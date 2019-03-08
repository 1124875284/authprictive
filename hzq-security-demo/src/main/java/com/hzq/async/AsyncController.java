package com.hzq.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;


/**
 * 异步处理Controller
 */
@RestController
public class AsyncController {

    private Logger logger=LoggerFactory.getLogger(getClass());
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferedResultHolder deferedResultHolder;

    /**
     * 同步处理
     * @return
     * @throws InterruptedException
     */
//    @RequestMapping("/order")
//    public String order() throws InterruptedException {
//        logger.info("主线程开始");
//        Thread.sleep(1000);
//        logger.info("主线程返回");
//        return "success";
//    }

    /**
     * 异步
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/getOrder")
    public DeferredResult<String> getorder() throws InterruptedException {
        logger.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result=new DeferredResult<>();
        deferedResultHolder.getMap().put(orderNumber,result);
//       Callable<String> result=new Callable<String>() {
//           @Override
//           public String call() throws Exception {
//               logger.info("副线程开始");
//               Thread.sleep(1000);
//               logger.info("副线程返回");
//               return "success";
//           }
//       };
        logger.info("主线程返回");
        return result;
    }

}
