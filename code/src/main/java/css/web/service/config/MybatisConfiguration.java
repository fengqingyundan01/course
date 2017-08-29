package css.web.service.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置MyBatis
 *
 * @author yangshuo
 *
 */
@Configuration
public class MybatisConfiguration {
	@Bean
	public MapperScannerConfigurer mapperScanner() {
		MapperScannerConfigurer scanner = new MapperScannerConfigurer();
		scanner.setBasePackage("ccs.web");
		scanner.setAnnotationClass(org.apache.ibatis.annotations.Mapper.class);
		return scanner;
	}
}
