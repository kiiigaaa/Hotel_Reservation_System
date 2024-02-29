package com.example.booking_management_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class BookingManagementSystemApplication
{

	public static void main( String[] args )
	{
		SpringApplication.run( BookingManagementSystemApplication.class, args );
	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Bean
	public CorsFilter corsFilter()
	{
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins( Collections.singletonList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList(
				"Origin", "Content-Type", "Accept", "Authorization",
				"X-Requested-With", "Access-Control-Request-Method", "Access-Control-Request-Headers"
		));
		corsConfiguration.setExposedHeaders(Collections.singletonList("Authorization"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT","PATCH", "DELETE", "OPTIONS"));

		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
