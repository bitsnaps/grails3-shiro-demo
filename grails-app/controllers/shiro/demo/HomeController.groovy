package shiro.demo

import grails.plugin.springsecurity.annotation.Secured
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.AuthorizationException
import org.apache.shiro.subject.Subject

@Secured(['IS_AUTHENTICATED_FULLY'])
class HomeController {

    def utilService

    def index() {
        render '''
          <p>Welcome everyone</p>
          <a href="admin">Admin</a><br>
          <a href="upper">To Upper</a><br>
          <a href="lower">To Lower</a><br>
          <a href="show">Intercepted</a><br>
          <a href="/logout">Logout</a>
        '''
    }

    def admin(){
        Subject s = SecurityUtils.getSubject()
        if (s.hasRole('ROLE_ADMIN')) {
            if (s.isPermitted('root')){
                render "<p>Permission authorized to: ${s.principal}</p>"
            }
            render '<p>Welcome Admin!</p><a href="index">Index</a>'
        } else if (s.hasRole('ROLE_USER')){
            render '<p>Sorry, you are not allowed to see this page!</p><a href="index">Index</a>'
        } else {
            redirect (view: '/')
        }
    }

    def upper(){
        // users with role USER_ROLE are allowed to access this service method
        render utilService.toUpper('hello')+'<br /><a href="index">Index</a>'
    }

    def lower(){
        try {
            // only users with ADMIN_ROLE can access this service method
            render utilService.toLower('HELLO')+'<br /><a href="index">Index</a>'
        } catch (AuthorizationException ae) {
            render '<p>Sorry! You are not allowed to use this service.</p><br /><a href="index">Index</a>'
        }
    }

    def show(){
      render 'show is intercepted action, only admin can have access'
    }


}
