package hoonspring.hellospring6;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

    // DataSource 를 Bean으로 등록
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    /*
     * LocalContainerEntityManagerFactoryBean
     * : JPA의 EntityManagerFactory 를 SpringContainer의 Bean으로 등록하는 역할을 함.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSource());
        emf.setPackagesToScan("hoonspring.hellospring6");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
            // 익명클래스를 만들면서 동시에 인스턴스의 초기화 블록을 활용
            setDatabase(Database.H2);
            // 애플리케이션이 시작될 때 JPA가 Entity클래스를 기반으로 DDL명령을 자동으로 생성하고 DB에 실행하는 옵션
            setGenerateDdl(true);
            // 애플리케이션 실행 중에 JPA가 생성하는 SQL쿼리를 콘솔에 출력하는 옵션
            setShowSql(true);
        }});

        return emf;
    }

    // JPA관련 어노테이션(@PersistenceContext 등)을 처리하기위한 후처리기 등록
    @Bean
    public BeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    // TransactionManager 등록
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}
