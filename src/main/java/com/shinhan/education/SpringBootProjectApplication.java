package com.shinhan.education;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//project 생성시 패키지의 하위에 있는 패키지는 자동으로 스캔됨
//다음의 세줄은 작성하지 않아도 됨
//@ComponentScan(basePackages= {"com.shinhan"})	//com.shinhan으로 시작하는 애들 다 스캔해줘
//@EntityScan("com.shinhan")	//entity 스캔해라
//@EnableJpaRepositories("com.shinhan")	//JpaRepository 활성화, SpringBoot자동임 //사용할 repository가 어디있는지 알려줘야 스캔함
public class SpringBootProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
	}

}
