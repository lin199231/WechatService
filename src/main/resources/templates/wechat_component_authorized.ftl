<!DOCTYPE html>
<!-- saved from url=(0045)http://m.artgogo.com/aq/daily_omit_1183.shtml -->
<html lang="zh-cmn-hans">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>微信第三方平台授权DEMO</title>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $("#authorizedButton").click(function(){
                $.ajax({
                    url : '../wechat/component/authorizer/url?authorizerAppid=${authorizerAppid!""}',
                    dataType: 'json',
                    success : function(data,status){
                        console.log("Data: " + data + "\nStatus: " + status);
                        console.log(data);
                        window.open(data.url);
                    }
                });
            });
        });
    </script>
</head>

<body>
    <button type="button" id="authorizedButton">微信第三方授权</button>
</body>
</html>