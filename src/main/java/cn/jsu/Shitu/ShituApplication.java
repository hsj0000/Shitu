package cn.jsu.projectmanage;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication()
@EnableScheduling
public class ProjectManageApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("开启");
    }

//    @Bean
//    public TaskScheduler taskscheduler() {
//        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
//        //设置线程池大小
//        taskScheduler.setPoolSize(5);
//        return taskScheduler;
//    }

}
