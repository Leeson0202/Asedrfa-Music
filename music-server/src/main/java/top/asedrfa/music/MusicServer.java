package top.asedrfa.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.asedrfa.music.dao")
public class MusicServer {

    public static void main(String[] args) {
        SpringApplication.run(MusicServer.class, args);
    }

}
