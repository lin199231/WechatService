<!DOCTYPE html>
<html>
    <head>
        <title>未度ERP微信登录返回</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://res.wx.qq.com/connect/zh_CN/htmledition/style/impowerApp3696b4.css">
        <link href="http://images.weidu.xin/admin/201709211748468145878.png" rel="Shortcut Icon">
        <script src="https://res.wx.qq.com/connect/zh_CN/htmledition/js/jquery.min3696b4.js"></script>
    </head>
    <body>
        <div class="main impowerBox">
            <div class="loginPanel normalPanel">
                <div class="title">微信二维码登录返回</div>
                <div class="waiting panelContent">
                    <div class="wrp_code"><img class="qrcode lightBorder" src="http://images.weidu.xin/admin/201709211747524056981.png" /></div>
                    <div class="info">
                        <#if (isSuccess??)>
                            <div class="status status_succ js_status" id="wx_after_scan">
                                <i class="status_icon icon38_msg succ"></i>
                                <div class="status_txt">
                                    <h4>微信登录成功</h4>
                                    <p>${message}</p>
                                </div>
                            </div>
                        <#else>
                            <div class="status status_fail js_status" id="wx_after_cancel">
                                <i class="status_icon icon38_msg warn"></i>
                                <div class="status_txt">
                                    <h4>微信登录失败</h4>
                                    <p>${message}</p>
                                </div>
                            </div>
                        </#if>
                    </div>
                </div>
            </div>
        </div>
        <script>
            document.body.style.backgroundColor="#333333";
            $(document).ready(function(){ setTimeout("window.close();",3000); });
        </script>
    </body>
</html>
