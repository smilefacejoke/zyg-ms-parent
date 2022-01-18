package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000118697325";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQChFjiMY3hHnrFGOKAh1jjvEB4efglX2WBN9GdXFLIxbTzmxb9HMa5Xi+RJ6gfiJCkYuBjgEK/PfDYzv0zhJP8zyyVOlFMycDG2vYd3Hb3rO2x8OItIEbqzyGSUNhs7BgqhCMgFhZVklJHIXRcBT3J1r04ynyZWIK/9zMli+8xRZp0BjCoNJJ1yQLwmMEsgn4MMOPV3EcDklL2YkaoXdPr4kXckoNBp4ub8arACXRljbnPQEHedAIlMQyqL0aeUkg3akqYwmx910qnvfCLzxb2o6DuoR35gTSK7HeMkDGE0qf8CkW+cQaE2oIzmreq2D9KIA6wht+kLFJ0ZGpVKKdiVAgMBAAECggEAMWonnqlE1HOR3gCw3rBgdrVzB9eR9ijwqfk3Wjc3EoJ4FDLFIonS+XgoZ6QrFuwQz0xqFRIJXJS169nbM5Wt062VnC1T+D0CgxRQ4fF1uRtZhCG0WD9YDp6CfBS89bY8vHl9InSAB4Kr1dL4o6ZDbmaAjnbTw8fzNOAFRyAG5VEfk66AnjNe38+syf/qp81Ea3DfKag8BbHlLLcM6Vsrsc/MB9h1pGs0ZfmjePQEydq1OzMTItJXbRbyC5IRpxmdzK2TDi0VJVvFy/rtmBFOikhgYmNYb4ah+9O5CdEGQ7wM/u+fPnZ0ysItabN65RWtakNV/X+dlOR+e35p3PwgAQKBgQDkfe8gkdpBqZabKoHOcumKIjnuvy4aqcg2ivFEwiV+m7XwgwRHPbV/7b8B5pv57oZC07hkUrnGpSfuXi/+nuafG3PVj496U6gLwOukQ3i3hG3dxvs35TCOiQXdWgjB9wjkcRXCKE8I86Kxjr/r2bDjoPVdsbZ22bIPq6R3zZilAQKBgQC0euCFtBVSRPb7eTpLrrsDF5ETN1YuWtAOdN6EF8eg3ubCVPKl8JKMoeXudRnhcdt4Hik8ePcRthogIcMb2rv5nhJJzpRqHpbdpnTeNHDMVhpE4vt0y5ncL8ev14lgbZSqkyf2nsMvA6Q1cwQK+efqugUIcSgeF6/uTm4z9ObPlQKBgQC77VAWC5/lTaRPLqDgueaW9BuN37P9vhKruY9CrCPGhC8aB8gnB/wVoebtm0xCPgozQItyCBA/72puRQ1YcDziM+QLjC3HwLKAKEYnLk9FMShjtdq9WEWdldk6+2gkkBfCO7hXnmuSwz/Wpb/4LW2tap8kVpvqGJa5CMUWvASGAQKBgCaZIOt3WBgAJ9IgWxdUhypiH+S8MPI4+cYduHkHnOoKwc9N25chnDX3afVVlfNhJk7KJIeKV0yyZWP51IBgh1MbO04NoDAR2rIMp3HeRUFuKLAS2zRfVsiAb3Rr5DV7OhBA6IsV6Shk3vmPX435ZGAO6ISlrq7A4h9VJrx14+cBAoGBAI3WbpxjuVwWlgrW3OvX3XWvcGuY4HWOl8ULRMemoVT/uJQpJowrwE41cWFGUIh3Zh7mAhVZRgxYpxo9ligIKkLPchva8qgzc8LWd0yr522m0GpOZI0BkY6Vsoj74dkF9QZZo0ERpb4L9zY/4wu9AQ1j0FRtwIw9PN6oUZh0Rj4Y";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwp09Pmevd8D0QWQNdvb481e4/0cV3lrgT7/ENDJ/7W2JoK+MB+lVPzO48FHCVAzQlXR0tY65iAH9/UlXAUtpLX41FNhsyo3awM6zHpmkzdD2er138Vdtv+wEm8dzOoP+JLjYkN2pFK97k5rptc089V/TaoFpwzPOX3zRLq02mK3CG/bVBzXBR2jo0ceXCfl5VsNvrClvbH/7M0uznALOeGTcBUr7VfzO3Jx0Fx7dg2pqd/FJjo5p2pmch+nUtQjs+/L8bElZHj4t1lrNYsKtiLfgknYINw6yEKBpGqBJcpPXhabqbF5IAa+yogVHruWwbTy0Osjfdaf6kJsb3CJkxQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

