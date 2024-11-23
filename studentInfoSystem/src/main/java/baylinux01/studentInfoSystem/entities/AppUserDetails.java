package baylinux01.studentInfoSystem.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppUserDetails implements UserDetails{

	private AppUser appUser;
	
	
	
	public AppUserDetails(AppUser appUser) {
		super();
		this.appUser = appUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		String[] array=appUser.getRoles().split("-");
		List<GrantedAuthority> list=new ArrayList<GrantedAuthority>();
		for(int i=0;i<array.length;i++)
		{
			list.add(new SimpleGrantedAuthority(array[i]));
		}
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return appUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return appUser.getUsername();
	}

}
