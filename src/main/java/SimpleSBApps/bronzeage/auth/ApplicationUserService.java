package SimpleSBApps.bronzeage.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private ApplicationUserDao applicationUserDao;

    @Autowired
    public ApplicationUserService(@Qualifier("temp") ApplicationUserDao applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return applicationUserDao.getUserByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("couldn't locate %s", s)));
    }
}
