package com.yipeng.baseservice.sms.api;

import com.github.pagehelper.PageInfo;
import com.yipeng.baseservice.sms.param.SmsSendParam;
import com.yipeng.baseservice.sms.param.SmsTemplate;
import com.yipeng.baseservice.sms.result.SmsRecord;
import com.yipeng.baseservice.sms.result.SmsSendResult;
import com.yipeng.framework.common.result.Result;

/**
 * 短信发送接口
 * @author: yibingzhou
 */
public interface SmsFacade {

    /**
     * 短信发送
     * @param param 短信信息
     * @return
     */
    Result<SmsSendResult> send(SmsSendParam param);

    /**
     * 短信发送模板设置
     * @param smsTemplate 短信发送模板
     * @return
     * */
    Result<Void> sendTemplate(SmsTemplate smsTemplate);

    /**
     * 短信发送记录
     * @return
     */
    PageInfo<SmsRecord> sendList();
}
