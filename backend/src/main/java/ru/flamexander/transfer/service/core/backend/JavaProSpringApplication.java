package ru.flamexander.transfer.service.core.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// -Dspring.profiles.active=dev

/*
	- Реализуйте сервис лимитов:
	-   При выполнении операции в мс лимитов отправляется запрос на списание лимита
	-   Если в процессе проведения перевода возникает ошибка, то необходимо отправить в мс лимитов запрос с откатом лимита назад
	-   Если при выполнении операции над лимитом пользователя в БД такого лимита не существует, то он создается дефолтным
	-   Размер дефолтного лимита должен быть задан через конфиг мс лимитов (10000 ставим)
	-   * По расписанию сделать сброс лимитов по всем пользователям до дефолтного значения
*/

@SpringBootApplication
public class JavaProSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaProSpringApplication.class, args);
	}
}
