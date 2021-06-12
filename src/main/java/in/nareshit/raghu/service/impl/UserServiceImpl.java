package in.nareshit.raghu.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.model.User;
import in.nareshit.raghu.repo.UserRepository;
import in.nareshit.raghu.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	

	public Integer saveUser(User user) {
		//read pwd entered in reg page
		String pwd = user.getUserPwd();
		
		//encode it
		String encPwd = encoder.encode(pwd);
		
		//set back to same object
		user.setUserPwd(encPwd);
		
		user = repo.save(user);
		return user.getId();
	}

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException
	{
		//fetch user object based on emailId(username)
		Optional<User> opt = repo.findByUserMail(username);

		//if user not exist 
		if(!opt.isPresent()) {
			throw new UsernameNotFoundException("Username not exist!");
		} else {
			//read model class user
			User user = opt.get();

			//read Roles(Set<String>) Convert to Set<GA>
			/*Set<String> roles = user.getUserRoles();

			Set<GrantedAuthority> authorities = new HashSet<>() ;
			for (String role : roles ) {
				authorities.add(new SimpleGrantedAuthority(role));
			}*/

			return new org.springframework.security.core.userdetails
					.User(
							username, 
							user.getUserPwd(), 
							user.getUserRoles()
							.stream()
							.map(role->new SimpleGrantedAuthority(role))
							.collect(Collectors.toSet())

							);
		}
	}



}
