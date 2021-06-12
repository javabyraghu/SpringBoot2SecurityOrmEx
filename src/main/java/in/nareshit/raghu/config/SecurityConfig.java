package in.nareshit.raghu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/user/register","/user/save","/user/login").permitAll()
		.antMatchers("/home").permitAll()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/emp").hasAnyAuthority("ADMIN","EMPLOYEE")
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/user/login")  //def: /login, GET
		.loginProcessingUrl("/login") 
		.defaultSuccessUrl("/profile",true)
		.failureUrl("/user/login?error")//def: /login?error

		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
		.logoutSuccessUrl("/user/login?logout") //def: /login?logout
		
		.and()
		.exceptionHandling()
		.accessDeniedPage("/denied")
		;
	}
}
