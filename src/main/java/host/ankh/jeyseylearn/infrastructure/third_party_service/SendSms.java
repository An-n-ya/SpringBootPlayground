package host.ankh.jeyseylearn.infrastructure.third_party_service;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20210111.models.SendSmsResponse;
import jakarta.inject.Named;

public class SendSms {
    // 因为我的模板只用两个参数：
    //     {1}为您的短信验证码，请于{2}分钟内登录。
    // 所以这里有两个入参，一个是短信验证码，一个是验证码ttl
    // 剩下的一个入参就是电话号码
    public static SendSmsResponse send(String code, String time, String telephone) {
        // TODO: 使用配置中心传入secretKey
        // 这里使用环境变量读取的方式，需要现在环境变量中先设置这两个值
        Credential cred = new Credential(System.getenv("SecretId"), System.getenv("SecretKey"));

        // 只支持三个区域： 北京 广州 南京
        SmsClient client = new SmsClient(cred, "ap-guangzhou");
        // 实例化一个请求对象
        SendSmsRequest req = new SendSmsRequest();

        // 短信应用ID
        String sdkAppId = "1400790975";
        req.setSmsSdkAppId(sdkAppId);

        // 短信签名内容
        String signName ="我的计算机学习记录个人网";
        req.setSignName(signName);

        // 模板ID
        String templateId = "1669702";
        req.setTemplateId(templateId);

        // 模板参数
        String[] templateParamSet = {code, time};
        req.setTemplateParamSet(templateParamSet);

        // 下发手机号
        String[] phoneNumberSet = {telephone};
        req.setPhoneNumberSet(phoneNumberSet);

        try {
            SendSmsResponse res = client.SendSms(req);
            return res;
        } catch (TencentCloudSDKException e) {
            throw new RuntimeException(e);
        }
    }

}