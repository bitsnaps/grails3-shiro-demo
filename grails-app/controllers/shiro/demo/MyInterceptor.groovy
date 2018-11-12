package shiro.demo

import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject

class MyInterceptor {

  public MyInterceptor(){
    match controller: 'home', action: 'show'
  }

    boolean before() {
      Subject s = SecurityUtils.getSubject()
      if (!s.hasRole('ROLE_ADMIN')) {
        flash.message = 'only admin are allowed'
        redirect (uri: '/')
        return false
      }
        log.info ('admin are allowed')
      true
    }

    //boolean after() { true }

/*    void afterView() {
        // no-op
    }*/

}
