function search_enter(e) {
    var evt = window.event || e;
    if (evt.keyCode == 13) {
        //回车事件
        showResult()
    }
}


Music_APP = function() {
    this.player = null;
    // 音乐列表
    this.music_list = [];
    // 目前播放位置
    this.music_index = 0;
    // 播放状态
    this.music_play_f = false;

    // 初始化
    this.init = function() {
        let audio = document.createElement("audio");
        var mm = new music_info("35678875", "呼吸决定", "http://music.163.com/song/media/outer/url?id=35678875.mp3",
            "http://p2.music.126.net/6y-UleORITEDbvrOLV0Q8A==/5639395138885805.jpg", "246268", null, null, null);

        audio.preload = "auto";
        audio.style.display = "none";
        $("#player").append(audio);
        player = audio;
    };

    // 暂停播放
    this.music_paused = function() {

    };
    // 继续、开始播放
    this.music_play = function() {

    };




}

// 音乐详细
music_info = function(id = null, name = null, src = null, picUrl = null, duration = null, singer_list = null, music_album = null, text = null) {
    this.id = id;
    this.name = name;
    this.src = src;
    this.picUrl = picUrl;
    this.duration = duration;
    this.singer_list = singer_list;
    this.music_album = music_album;
    this.text = text;
};

// 音乐歌手详细
music_singer = function(id = null, name = null, picUrl = null, url = null) {
    this.id = id;
    this.name = name;
    this.picUrl = picUrl;
    this.url = url;
};



// 专辑
music_album = function(id = null, name = null, picUrl = null, url = null) {
    this.id = id;
    this.name = name;
    this.picUrl = picUrl;
    this.url = url;
};