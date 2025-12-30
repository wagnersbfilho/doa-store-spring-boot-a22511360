package pt.ippestg.doa.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(
        exclude = { DataSourceAutoConfiguration.class }
)
public class DoaStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoaStoreApplication.class, args);
	}

}
