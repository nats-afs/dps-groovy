package com.asforsoft.dps

import com.asforsoft.dps.model.Area
import com.asforsoft.dps.repository.AreaRepository
import com.github.javafaker.Faker
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class DpsApplication {

  static void main(String[] args) {
    SpringApplication.run DpsApplication, args
  }

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    new BCryptPasswordEncoder()
  }

  @Bean
  CommandLineRunner initData(AreaRepository areaRepository) {
    {
      args ->
        def faker = new Faker()
        20.times {
          areaRepository.
              save(
                  new Area(
                      name: faker.commerce().department(),
                      shortName: faker.commerce().promotionCode(),
                      description: faker.commerce().productName()
                  )
              )
        }
    }
  }
}
