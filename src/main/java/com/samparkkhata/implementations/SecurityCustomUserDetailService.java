package com.samparkkhata.implementations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.samparkkhata.repositories.UserRepository;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {

    // Using service is making circular dependency ?
    
    /*
     * private final UserService userService;
     * 
     * public SecurityCustomUserDetailService(UserService userService) {
     * this.userService = userService;
     * }
     */

    // REASON:-
    /*
     * Using 'UserRepository' directly in 'SecurityCustomUserDetailService' avoids the
     * circular dependency because 'UserRepository' is a lower-level component that
     * does not have any dependencies on 'SecurityCustomUserDetailService' or other
     * higher-level services.
     * 
     * 
     * When you use UserService in 'SecurityCustomUserDetailService', it creates a
     * circular dependency because:
     *      1.  'SecurityCustomUserDetailService' depends on UserService.
     * 
     *      2.  'UserServiceImpl' (which implements UserService) likely depends on 
     *          'SecurityCustomUserDetailService' (directly or indirectly) to load user
     *          details.
     * 
     * This circular reference between 'SecurityCustomUserDetailService' and
     * 'UserServiceImpl' causes the application to fail to start.
     * 
     * 
     * However, when you use 'UserRepository' directly in
     * 'SecurityCustomUserDetailService', it breaks the circular dependency because:
     *      1.  'SecurityCustomUserDetailService' depends on 'UserRepository'.
     *      2.   'UserServiceImpl' depends on 'UserRepository' (likely through constructor
     *             injection).
     *      3.      There is no direct or indirect dependency between
     *              'SecurityCustomUserDetailService' and 'UserServiceImpl'.
     * 
     * By using 'UserRepository' directly, 'SecurityCustomUserDetailService' can load
     * user details without relying on UserService, which in turn depends on
     * 'SecurityCustomUserDetailService'. This eliminates the circular reference and
     * allows the application to start successfully.
     * 
     * 
     * In general, it's a good practice to design your application with clear
     * boundaries between layers and avoid tight coupling between components. By
     * using lower-level repositories directly in higher-level services, you can
     * create a more modular and maintainable architecture that is less prone to
     * circular dependency issues.
     */


     
    private final UserRepository userRepository;

    public SecurityCustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // We have to load the user by their username (i.e. userEmail in our case)
        return userRepository
                .findByUserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by email id: " + username));
    }

}
