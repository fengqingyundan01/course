
package css.web;

import java.util.Iterator;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.boot.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 应用的入口
 * 
 * @author yangshuo - 2017年8月29日
 * @since
 */
@ImportResource({ "classpath:application.xml" })
@EnableTransactionManagement
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "css.web", excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.REGEX, pattern = "css.web.CssTestApplication") })
public class CssApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder();
		builder.sources(CssApplication.class);
		removeLoggingListener(builder);
		builder.run(args);
	}

	private static void removeLoggingListener(SpringApplicationBuilder builder) {
		SpringApplication application = builder.application();
		Set<ApplicationListener<?>> listeners = application.getListeners();
		Iterator<ApplicationListener<?>> it = listeners.iterator();
		while (it.hasNext()) {
			ApplicationListener<?> listener = it.next();
			if (listener instanceof LoggingApplicationListener) {
				it.remove();
			}
		}
		application.setListeners(listeners);

	}
}
