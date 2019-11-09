package com.example.nuonuo.config;


import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class BeanValidatorConfig {
  private MessageSource messageSource;

  public BeanValidatorConfig(MessageSource messageSource) {
    this.messageSource = messageSource;
  }



//  @Bean
//  @Primary
//  public Validator validator(){
//    LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
//    validatorFactoryBean.setValidationMessageSource(messageSource);
//    return validatorFactoryBean;
//  }
}
