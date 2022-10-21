import requests
import json
import time
import re


# 首先要有一个包含音乐，电影等等数据信息的网页
# 然后获取这个网页的信息
# 接下来利用正则表达式获取目标数据的id以及名字
# 之后利用id获取某个数据的具体网址
# 再去某个数据的网页上获取该数据的二进制信息
# 保存数据即可

#主函数用于获取每个榜单的页面信息
def main():
    pageUrl = "https://music.163.com/discover/toplist"
    head = {
        "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50"
    }

    page_response = requests.get(pageUrl, headers = head)
    page_html_data = re.findall('<p class="name"><a href="/discover/toplist\?id=(\d+)" class="s-fc0">(.*?)</a></p>', page_response.text)
    for pid, pname in page_html_data:
        print(pid, pname)
        everyPageUrl = f"https://music.163.com/discover/toplist?id={pid}" # 19723756
        GeteveryPageData(everyPageUrl)



#该函数用于在每个榜单页面上获取所有歌曲的信息
def GeteveryPageData(url):
    # print("GeteveryPageData")
    #url = 'https://music.163.com/discover/toplist?id=3778678'

    head = {
     "user-agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36 Edg/94.0.992.50"
    }
    response = requests.get(url, headers = head)
    # print(response.text)

    html_data = re.findall('<li><a href="/song\?id=(\d+)">(.*?)</a>', response.text)
    # print(html_data)

    #print("in for")
    #https://music.163.com/song/media/outer/url?id=1887190390.mp3
    for mid, mname in html_data:
        music_url = f'https://music.163.com/song/media/outer/url?id={mid}.mp3'
        #对音乐播放地址发送请求，获取音乐的二进制数据
        music_content = requests.get(music_url, headers = head).content
        #保存音乐的ID和NAME到文本文件
        # savePath = "D:\\JavaIDEA\\music\\music.txt"
        # with open(savePath, "a") as f:
        #     f.write(mid + " " + mname)
        #     f.write('\n')
        #     print("     " + mid, mname)

        #下载音乐到文件夹
        with open("D:\\JavaIDEA\\music\\" + mname + ".mp3", mode = "wb") as f:
            f.write(music_content)
            print("     " + mid, mname)


if __name__ == "__main__":
    main()