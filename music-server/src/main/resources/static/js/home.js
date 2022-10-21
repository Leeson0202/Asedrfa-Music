// 改变状态栏
function headInit() {

    let info = Tools.isLoad();
    console.log(info)

    let a = $("#head-my")

    if (info['code'] == 200) {
        // console.log(nName, info['data']['nName']);
        // 登录 --》 我的
        $("#head-my").text(info['data']['nName'])
    } else {
        $(".dropdown").hover(function() {
            $(".dropdown-content").css("display", "none");
        })
        $(".dropdown").click(function() {
            window.open("login.html", "_self");
        })
    }
    // 事件监听
    headAction();
}

// 顶部状态栏事件监控
function headAction() {
    // 搜索

    $("#search-coin").click(function() {
        player['iframe'].src = "search.html"
    });
    // 历史记录

    // 信息
    // 设置

    // 退出登录
    $("#head-my-unlogin").click(function() {
        $.post("user/deleteTLoad", {
            appId: user['appId'],
            msg: user['msg']
        }, function(resoult) {
            if (resoult.code == 200) {
                let date = new Date();
                date.setTime(date.getTime() - 10000);
                document.cookie = "appId" + "=v; expires=" + date.toGMTString();
                document.cookie = "msg" + "=v; expires=" + date.toGMTString();
                document.cookie = "nName" + "=v; expires=" + date.toGMTString();
                if (window.location.pathname == "/index" || window.location.pathname == "/index.html") {
                    window.open("login", "_self");
                }
            }
        });
    });
}


// 改变时间和进度条
function timeCal() {
    player['curTime'].innerText = Tools.formatTime(player['audio'].currentTime);

    if (player['pausetag'] != 0)
        window.setTimeout(timeCal, 100);
    // console.log(barContent.style.width)
    if (player['audio'].duration)
        barContent.style.width = progressBar.offsetWidth * player['audio'].currentTime / player['audio'].duration + "px";

}

// foot的监听事件
function footAction() {
    document.getElementsByTagName("title")[0].innerText = "呼吸决定"
    player['musicName'].innerText = "呼吸决定";
    // 上一首
    // 暂停
    $('#pause-btn').click(function() {
        // 判断是否有音乐
        if (player['audio'].src != "") {
            if (player['pausetag'] == 0) {
                // 没加载就--：--
                // player['allTime'].innerText = "--:--"
                // player['curTime'].innerText = "--:--"
                if (!player['audio'].duration) return;
                player['pausetag'] = 1
                $('#pause-btn').attr("src", "/img/暂停.svg")
                player['audio'].play();
                timeCal()
                player['allTime'].innerText = Tools.formatTime(player['audio'].duration);



            } else {
                player['pausetag'] = 0
                $('#pause-btn').attr("src", "/img/播放.svg")
                player['audio'].pause();
            }
        }
    });

    // 下一首

    // 图片
    // 其他
}

// 直接播放
function musicPlay(music) {
    console.log(music);
    // 1. 添加记录
    // 2. 改变player
    player['audio'].src = music.mUrl;
    player['musicName'].innerText = music['mName'];
    document.getElementsByTagName("title")[0].innerText = music['mName'];
    //监听audio是否加载完毕，如果加载完毕，则读取audio播放时间
    setTimeout(function() {
        player['audio'].addEventListener("canplay", function() {
            // console.log(Tools.formatTime(player['audio'].duration));
            player['allTime'].innerText = music['fullTime']
            $('#pause-btn').attr("src", "/img/暂停.svg")
            player['audio'].play();
            player['pausetag'] = 1
            timeCal()
            return
        });
    }, 200);
    // 没加载就--：--
    player['allTime'].innerText = "--:--"
    player['curTime'].innerText = "--:--"





}