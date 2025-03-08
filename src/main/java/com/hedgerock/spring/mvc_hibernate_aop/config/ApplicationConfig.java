package com.hedgerock.spring.mvc_hibernate_aop.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com.hedgerock.spring.mvc_hibernate_aop")
@EnableWebMvc
@EnableTransactionManagement
public class ApplicationConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Bean
    public InternalResourceViewResolver viewResolver () {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        Dotenv dotenv = Dotenv.load();

        try {
            comboPooledDataSource.setDriverClass(dotenv.get("DB_DRIVER_CLASS"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load content from .env file: " + e.getMessage());
        }

        comboPooledDataSource.setJdbcUrl(dotenv.get("DB_URL"));
        comboPooledDataSource.setUser(dotenv.get("DB_USER"));
        comboPooledDataSource.setPassword(dotenv.get("DB_PASSWORD"));

        return comboPooledDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.hedgerock.spring.mvc_hibernate_aop");

        Dotenv dotenv = Dotenv.load();

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", dotenv.get("DB_DIALECT"));
        hibernateProperties.put("hibernate.show_sql", dotenv.get("DB_SHOW_SQL"));
        hibernateProperties.put("hibernate.ddl-auto", dotenv.get("DB_DDL_AUTO"));
        hibernateProperties.put("hibernate.envers.store_data_at_delete", dotenv.get("DB_STORE_DATA"));

        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();

        hibernateTransactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return hibernateTransactionManager;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry.addResourceHandler("/css/**").addResourceLocations("/css/");
        resourceHandlerRegistry.addResourceHandler("/js/**").addResourceLocations("/js/");
        resourceHandlerRegistry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
        resourceHandlerRegistry.addResourceHandler("/tmp/**").addResourceLocations("/tmp/");
        resourceHandlerRegistry.addResourceHandler("/favicon.ico").addResourceLocations("/assets/favicon/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authenticationInterceptor).addPathPatterns("/**");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

}
