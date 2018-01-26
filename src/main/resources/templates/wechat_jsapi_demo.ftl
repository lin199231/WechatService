<!DOCTYPE html>
<!-- saved from url=(0045)http://m.artgogo.com/aq/daily_omit_1183.shtml -->
<html lang="zh-cmn-hans">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>微信JSAPI DEMO</title>
        <script type="text/javascript" src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
        <script>
            jQuery(function(){
                //微信分享
                var weixin_url=window.location.href;
                var appId, timestamp, nonceStr, signature;
                jQuery.ajax({
                    type:'GET',
                    dataType:'json',
                    async:false,
                    url:'/erp/wechat/js/config?url='+weixin_url,
                    success:function(data){
                        appId = data.jsWechatConfig.appId;
                        nonceStr = data.jsWechatConfig.nonceStr;
                        signature = data.jsWechatConfig.signature;
                        timestamp = data.jsWechatConfig.timestamp;
                    }
                });

                alert(appId + "\n"
                        +nonceStr + "\n"
                        +signature + "\n"
                        +timestamp + "\n");

                wx.config({
                    debug: true,
                    appId: appId,
                    timestamp: timestamp,
                    nonceStr: nonceStr,
                    signature: signature,
                    jsApiList: [
                        // 所有要调用的 API 都要加到这个列表中
                        'onMenuShareAppMessage',
                        'onMenuShareTimeline'
                    ]
                });

                wx.ready(function () {
                    // 在这里调用 API
                    var wx_fx_title=$("title").html();
                    var desc='MK时代峻峰胜多负少的股份但是价格';
                    var link=window.location.href;
                    var imgUrl='http://images.weidu.xin/82c7dbcae4094736b6b0936e9f515a76.jpg';

                    //获取“分享给朋友”按钮点击状态及自定义分享内容接口
                    wx.onMenuShareAppMessage({
                        title: wx_fx_title, // 分享标题
                        desc: desc, // 分享描述
                        link: link, // 分享链接
                        imgUrl: imgUrl, // 分享图标
                        type: '', // 分享类型,music、video或link，不填默认为link
                        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                        success: function () {
                            // 用户确认分享后执行的回调函数
                            //alert('分享成功');
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数

                        },
                        fail:function(res){
                            alert(res);
                        }
                    });

                    //获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
                    wx.onMenuShareTimeline({
                        title: desc, // 分享标题
                        link: link, // 分享链接
                        imgUrl: imgUrl, // 分享图标
                        success: function () {
                            // 用户确认分享后执行的回调函数
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                        }
                    });
                });
                //微信分享end

            });
        </script>
        <!--微信结束-->
    </head>

    <body>
        <div class="slide">
            <p class="abstract">艺术家简介/资料</p>
            <div class="artist-details">
                <h3>洪茵</h3>
                <div><p><span style="color: rgb(51, 51, 51);"><span style="font-size: 14px;"><span style="font-family: 黑体;">七十年代末年生于古都西安 <br>毕业于上海大学美术学院建筑系 <br>目前生活于上海 <br>主要负责美国建筑设计事务所关于建筑室内艺术品的咨询和设计工作 <br></span></span></span></p><p><span style="color: rgb(51, 51, 51);"><span style="font-size: 14px;"><span style="font-family: 黑体;">艺术创作理念：《动物系列》通过对动物的拟人化表现，抓住了现代人群的诸多特点，表达了作者内心的对部分社会人群的体悟及调侃</span></span></span></p><p>2014年 作品参加 &lt;春天来了&gt;杭州三尚当代艺术馆群展 作品长期在画廊展出，多幅作品被国外人士收藏</p></div>
            </div>
        </div>
    </body>
</html>